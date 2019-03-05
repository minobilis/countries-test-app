package rosh.ivan.contries.base

import androidx.lifecycle.ViewModel
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

open class BaseViewModel @Inject constructor(private val compositeDisposable: CompositeDisposable) :
    ViewModel() {

    private val onNextStub: (Any) -> Unit = {}
    private val onErrorStub: (Throwable) -> Unit =
        { RxJavaPlugins.onError(OnErrorNotImplementedException(it)) }
    private val onCompleteStub: () -> Unit = {}

    protected fun isInitialized(): Boolean = compositeDisposable.size() > 0

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun Disposable.untilDestroyed() {
        compositeDisposable.add(this)
    }

    /**
     * Overloaded subscribe function that allows passing named parameters and automatically clears
     * viewModel's compositeDisposable onCleared() call
     */
    fun <T : Any> Observable<T>.subscribeUntilDestroyed(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub

    ) = subscribeBy(onNext = onNext, onError = onError, onComplete = onComplete)
        .untilDestroyed()


    /**
     * Overloaded subscribe function that allows passing named parameters and automatically clears
     * viewModel's compositeDisposable onCleared() call
     */
    fun <T : Any> Flowable<T>.subscribeUntilDestroyed(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
    ) = subscribeBy(onNext = onNext, onError = onError, onComplete = onComplete)
        .untilDestroyed()

    /**
     * Overloaded subscribe function that allows passing named parameters and automatically clears
     * viewModel's compositeDisposable onCleared() call
     */
    fun <T : Any> Single<T>.subscribeUntilDestroyed(
        onSuccess: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub
    ) = subscribeBy(onSuccess = onSuccess, onError = onError)
        .untilDestroyed()

    /**
     * Overloaded subscribe function that allows passing named parameters and automatically clears
     * viewModel's compositeDisposable onCleared() call
     */
    fun <T : Any> Maybe<T>.subscribeUntilDestroyed(
        onSuccess: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
    ) = subscribeBy(onSuccess = onSuccess, onError = onError, onComplete = onComplete)
        .untilDestroyed()

    /**
     * Overloaded subscribe function that allows passing named parameters and automatically clears
     * viewModel's compositeDisposable onCleared() call
     */
    fun Completable.subscribeUntilDestroyed(
        onComplete: () -> Unit = onCompleteStub,
        onError: (Throwable) -> Unit = onErrorStub
    ) = subscribeBy(onComplete = onComplete, onError = onError)
        .untilDestroyed()
}
