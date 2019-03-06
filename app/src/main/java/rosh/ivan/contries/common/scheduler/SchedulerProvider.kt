package rosh.ivan.contries.common.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun trampoline(): Scheduler
}
