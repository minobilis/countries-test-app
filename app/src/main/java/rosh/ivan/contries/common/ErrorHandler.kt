package rosh.ivan.contries.common

import rosh.ivan.contries.R
import rosh.ivan.countries.domain.abstraction.ResourceProvider
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandler @Inject
constructor(private val resourceProvider: ResourceProvider) {

    fun getMessage(throwable: Throwable): String {
        return if (throwable is UnknownHostException) {
            resourceProvider.getString(R.string.error_no_internet)

        } else {
            throwable.message ?: resourceProvider.getString(R.string.error_unknown)
        }
    }
}
