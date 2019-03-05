package rosh.ivan.contries.common

import android.content.Context
import rosh.ivan.countries.domain.abstraction.ResourceProvider
import javax.inject.Inject

class AndroidResourceProvider @Inject constructor(private val context: Context?) : ResourceProvider {
    override fun getString(stringResourceId: Int): String {
        return if (context != null) {
            context.resources.getString(stringResourceId)

        } else {
            MISSING_STRING_RESOURCE
        }
    }

    override fun getString(stringResourceId: Int, vararg formatArgs: Any): String {
        return if (context != null) {
            context.resources.getString(stringResourceId, *formatArgs)

        } else {
            MISSING_STRING_RESOURCE
        }
    }

    companion object {
        const val MISSING_STRING_RESOURCE = "Missing text resource"
    }
}
