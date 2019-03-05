package rosh.ivan.contries.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import maatayim.com.brainsway.di.module.*
import rosh.ivan.contries.common.App
import rosh.ivan.contries.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ContextModule::class,
        SchedulerModule::class,
        RxModule::class,
        AppModule::class,
        DataModule::class,
        ViewModelModule::class]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): ApplicationComponent
    }
}
