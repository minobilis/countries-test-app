package maatayim.com.brainsway.common.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun trampoline(): Scheduler
}
