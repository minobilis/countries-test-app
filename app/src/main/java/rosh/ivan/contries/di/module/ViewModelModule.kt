@file:Suppress("UNCHECKED_CAST")

package rosh.ivan.contries.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import rosh.ivan.contries.di.factory.CountriesViewModelFactory
import rosh.ivan.contries.feature.list.CountriesViewModel
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Singleton
    @Binds
    internal abstract fun bindViewModelFactory(factory: CountriesViewModelFactory): ViewModelProvider.Factory

    //ViewModels
    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    internal abstract fun CountriesViewModel(viewModel: CountriesViewModel): ViewModel
}