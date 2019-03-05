package rosh.ivan.contries.di.module

import dagger.Binds
import dagger.Module
import dagger.Reusable
import maatayim.com.brainsway.common.scheduler.AndroidSchedulerProvider
import maatayim.com.brainsway.common.scheduler.SchedulerProvider

/**
 * Created by Ivan on 12/11/17.
 */

@Module
abstract class SchedulerModule {

    @Reusable
    @Binds
    abstract fun schedulerProvider(androidSchedulerProvider: AndroidSchedulerProvider): SchedulerProvider}
