package rosh.ivan.contries.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.jakewharton.rxrelay2.Relay
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.rxkotlin.subscribeBy
import rosh.ivan.contries.common.ErrorHandler
import rosh.ivan.contries.common.ResourceProvider
import rosh.ivan.contries.common.dpToPx
import javax.inject.Inject

/**
 * Created by Ivan on 1/4/18.
 */

abstract class BaseFragment : DaggerFragment() {

    private val onNextStub: (Any) -> Unit = {}

    private val onErrorStub: (Throwable) -> Unit =
        { RxJavaPlugins.onError(OnErrorNotImplementedException(it)) }

    private val onCompleteStub: () -> Unit = {}

    @Inject
    lateinit var stopRelatedDisposables: CompositeDisposable

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var errorHandler: ErrorHandler

    @Inject
    lateinit var resourceProvider: ResourceProvider

    abstract val layoutResourceId: Int

    protected var baseActivity: BaseActivity? = null
        get() = activity as? BaseActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(layoutResourceId, container, false)

    fun hideKeyboard() = baseActivity?.hideKeyboard()

    fun showSuccess(message: String) {
        showToast(message)
    }

    fun showSuccess(stringResId: Int) {
        showSuccess(resourceProvider.getString(stringResId))
    }

    fun showSuccess(stringResId: Int, vararg formatArgs: Any) {
        showSuccess(resourceProvider.getString(stringResId, formatArgs))
    }

    fun showError(message: String) {
        showToast(message)
    }

    fun showError(stringResId: Int) {
        showError(resourceProvider.getString(stringResId))
    }

    fun showError(stringResId: Int, vararg formatArgs: Any) {
        showError(resourceProvider.getString(stringResId, formatArgs))
    }

    fun showError(throwable: Throwable) {
        showError(errorHandler.getMessage(throwable))
    }

    fun showToast(stringId: Int) {
        Toast.makeText(
            context,
            resourceProvider.getString(stringId),
            Toast.LENGTH_LONG
        )
            .show()
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG)
            .show()
    }

    override fun onStop() {
        super.onStop()
        stopRelatedDisposables.clear()
    }

    private fun Disposable.untilStopped() {
        stopRelatedDisposables.add(this)
    }

    fun <T : Any> Observable<T>.subscribeUntilStopped(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
    ) = subscribeBy(onNext = onNext, onError = onError, onComplete = onComplete)
        .untilStopped()

    fun <T : Any> Observable<T>.subscribeUntilStopped(
        onNext: (T) -> Unit
    ) = subscribeBy(onNext = onNext, onError = onErrorStub, onComplete = onCompleteStub)
        .untilStopped()

    fun TextView.bindTextChangesTo(relay: Relay<String>) {
        this.textChanges()
            .map { it.toString() }
            .subscribeUntilStopped { relay.accept(it) }
    }

    fun View.bindClicksTo(relay: Relay<Unit>) {
        this.clicks()
            .subscribeUntilStopped { relay.accept(it) }
    }

    fun printStackTrace(throwable: Throwable) {
        throwable.printStackTrace()
    }

    protected fun dpToPx(dip: Number): Int  {
        val cntx = context
        return if (cntx != null) {
            dpToPx(cntx, dip.toFloat())
        } else {
            0
        }
    }
}
