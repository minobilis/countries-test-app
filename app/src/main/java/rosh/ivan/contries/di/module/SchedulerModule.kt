package rosh.ivan.contries.di.module

import dagger.Binds
import dagger.Module
import dagger.Reusable
import rosh.ivan.contries.common.scheduler.AndroidSchedulerProvider
import rosh.ivan.contries.common.scheduler.SchedulerProvider

/**
 * Created by Ivan on 12/11/17.
 */

@Module
abstract class SchedulerModule {

    @Reusable
    @Binds
    abstract fun schedulerProvider(androidSchedulerProvider: AndroidSchedulerProvider): SchedulerProvider
}
