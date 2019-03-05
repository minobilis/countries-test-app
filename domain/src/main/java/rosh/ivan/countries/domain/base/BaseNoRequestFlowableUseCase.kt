package rosh.ivan.countries.domain.base

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

abstract class BaseNoRequestFlowableUseCase<Response>(
    private var compositeDisposable: CompositeDisposable) {

    abstract fun asFlowable(): Flowable<Response>

    fun execute(
        executionScheduler: Scheduler? = getDefaultScheduler(),
        responseScheduler: Scheduler? = getDefaultScheduler(),
        onNext: Consumer<Response>,
        onError: Consumer<in Throwable>,
        onComplete: Action
    ) {

        compositeDisposable.add(
            asFlowable()
                .subscribeOn(executionScheduler ?: getDefaultScheduler())
                .observeOn(responseScheduler ?: getDefaultScheduler())
                .subscribe(
                    onNext,
                    onError,
                    onComplete
                )
        )
    }

    fun execute(
        executionScheduler: Scheduler?,
        responseScheduler: Scheduler?,
        resourceSubscriber: ResourceSubscriber<Response>
    ) {

        compositeDisposable.add(
            asFlowable()
                .subscribeOn(executionScheduler ?: getDefaultScheduler())
                .observeOn(responseScheduler ?: getDefaultScheduler())
                .subscribeWith(resourceSubscriber)
        )
    }

    private fun getDefaultScheduler() = Schedulers.trampoline()

    fun dispose() {
        compositeDisposable.clear()
    }
}
