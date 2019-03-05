package rosh.ivan.contries.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import rosh.ivan.contries.common.ErrorHandler
import rosh.ivan.contries.common.AndroidResourceProvider
import rosh.ivan.countries.domain.abstraction.ResourceProvider
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    /**
     * Should be implemented in activities extending base activities in the project.
     *
     * @return generated layout id that will be used in setContentView(int id), for example R.layout.main_activity
     */
    protected abstract val layoutResource: Int

    @Inject
    lateinit var errorHandler: ErrorHandler

    @Inject
    lateinit var resourceProvider: ResourceProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
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

    fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showSuccess(stringResId: Int) {
        showSuccess(resourceProvider.getString(stringResId))
    }

    fun showSuccess(stringResId: Int, vararg formatArgs: Any) {
        showSuccess(resourceProvider.getString(stringResId, formatArgs))
    }

    fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
