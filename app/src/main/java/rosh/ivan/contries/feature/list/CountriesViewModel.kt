package rosh.ivan.contries.feature.list

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import rosh.ivan.contries.base.BaseViewModel
import rosh.ivan.contries.common.scheduler.SchedulerProvider
import rosh.ivan.contries.feature.list.CountriesViewState.DATA
import rosh.ivan.contries.feature.list.CountriesViewState.LOADING
import rosh.ivan.contries.feature.list.CountriesViewState.NAVIGATE
import rosh.ivan.contries.feature.list.CountriesViewState.ERROR
import rosh.ivan.countries.domain.usecase.GetAllCountriesUseCase
import javax.inject.Inject

@Suppress("MemberVisibilityCanBePrivate")
class CountriesViewModel @Inject constructor(
        compositeDisposable: CompositeDisposable,
        val input: CountriesInput,
        val commands: PublishRelay<CountriesViewState>,
        val viewStates: BehaviorRelay<CountriesViewState>,
        private val schedulerProvider: SchedulerProvider,
        private val getAllCountriesUseCase: GetAllCountriesUseCase
) : BaseViewModel(compositeDisposable) {

    fun init() {
        if (isInitialized()) return

        getAllCountriesUseCase.asFlowable()
                .doOnSubscribe { viewStates.accept(LOADING) }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnError { viewStates.accept(ERROR(it)) }
                .repeatWhen { input.refreshRequests.toFlowable(BackpressureStrategy.LATEST) }
                .retryWhen { input.refreshRequests.toFlowable(BackpressureStrategy.LATEST) }
                .subscribeUntilDestroyed(
                        { viewStates.accept(DATA(it)) },
                        { viewStates.accept(ERROR(it)) }
                )

        input.listItemClicks
                .subscribeUntilDestroyed(
                        { commands.accept(NAVIGATE(it)) },
                        { viewStates.accept(ERROR(it)) }
                )
    }
}
