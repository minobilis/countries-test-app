package rosh.ivan.contries.common

import android.content.Context
import javax.inject.Inject

class ResourceProvider @Inject constructor(private val context: Context?) {

    fun getString(stringResourceId: Int): String {
        return if (context != null) {
            context.resources.getString(stringResourceId)

        } else {
            MISSING_STRING_RESOURCE
        }
    }

    fun getString(stringResourceId: Int, vararg formatArgs: Any): String {
        return if (context != null) {
            context.resources.getString(stringResourceId, *formatArgs)

        } else {
            MISSING_STRING_RESOURCE
        }
    }

    companion object {
        val MISSING_STRING_RESOURCE = "Missing text resource"
    }
}
