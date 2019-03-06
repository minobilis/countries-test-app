package rosh.ivan.contries.common

import android.content.Context
import android.util.TypedValue
import java.util.*


/**
 * Created by Ivan on 12/14/18.
 */

fun dpToPx(context: Context?, dipValue: Float): Int {
    return if (context != null) {
        val metrics = context.resources.displayMetrics
        Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics))
    } else {
        dipValue.toInt()
    }

}

object NumberFormatter {
    private val suffixes = TreeMap<Long, String>()
    init {
        suffixes.put(1_000L, "k")
        suffixes.put(1_000_000L, "M")
        suffixes.put(1_000_000_000L, "G")
        suffixes.put(1_000_000_000_000L, "T")
        suffixes.put(1_000_000_000_000_000L, "P")
        suffixes.put(1_000_000_000_000_000_000L, "E")
    }

    fun format(value: Long): String {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == java.lang.Long.MIN_VALUE) return format(java.lang.Long.MIN_VALUE + 1)
        if (value < 0) return "-" + format(-value)
        if (value < 1000) return java.lang.Long.toString(value) //deal with easy case

        val e = suffixes.floorEntry(value)
        val divideBy = e.key
        val suffix = e.value

        val truncated = value / (divideBy!! / 10) //the number part of the output times 10
        val hasDecimal = truncated < 100 && truncated / 10.0 != (truncated / 10).toDouble()
        return if (hasDecimal) "${truncated / 10.0} $suffix" else "${truncated / 10} $suffix"
    }
}