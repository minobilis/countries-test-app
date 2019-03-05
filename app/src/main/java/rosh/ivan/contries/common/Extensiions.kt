package rosh.ivan.contries.common

import android.content.Context
import android.util.TypedValue
import io.reactivex.Observable
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.functions.Predicate
import io.reactivex.internal.functions.ObjectHelper
import io.reactivex.internal.operators.observable.ObservableFilter
import io.reactivex.plugins.RxJavaPlugins
import java.util.regex.Pattern

/**
 * Created by Ivan on 12/14/18.
 */

fun getScreenHeightPx(context: Context?): Int {
    return if (context != null) {
        val metrics = context.resources.displayMetrics
        metrics.heightPixels
    } else {
        0
    }
}

fun dpToPx(context: Context?, dipValue: Float): Int {
    return if (context != null) {
        val metrics = context.resources.displayMetrics
        Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics))
    } else {
        dipValue.toInt()
    }

}

fun dpToPx(context: Context?, dipValue: Int): Int {
    if (context == null) {
        return 0
    }

    val metrics = context.resources.displayMetrics
    return Math.round(
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dipValue.toFloat(),
            metrics
        )
    )
}