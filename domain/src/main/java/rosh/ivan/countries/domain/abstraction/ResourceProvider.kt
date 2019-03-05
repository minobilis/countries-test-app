package rosh.ivan.countries.domain.abstraction

/**
 * Created by Ivan on 3/5/19.
 */
interface ResourceProvider {

    fun getString(stringResourceId: Int): String

    fun getString(stringResourceId: Int, vararg formatArgs: Any): String
}