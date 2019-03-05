package rosh.ivan.contries.di.module

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import rosh.ivan.contries.feature.main.MainActivity


/**
 * Created by Ivan on 12/11/17.
 */

@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {

    @ContributesAndroidInjector(modules = [AndroidModule::class])
    abstract fun mainActivityInjector(): MainActivity
}
