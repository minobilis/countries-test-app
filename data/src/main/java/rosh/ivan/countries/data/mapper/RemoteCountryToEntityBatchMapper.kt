package rosh.ivan.countries.data.mapper

import rosh.ivan.countries.data.entity.CountryRemote
import rosh.ivan.countries.domain.entity.Country
import javax.inject.Inject
import io.reactivex.functions.Function
import rosh.ivan.countries.data.R
import rosh.ivan.countries.domain.abstraction.ResourceProvider

class RemoteCountryToEntityBatchMapper @Inject constructor() : Function<List<CountryRemote>, List<Country>> {

    override fun apply(remoteList: List<CountryRemote>): List<Country> {
        return remoteList.map {
            Country(
                id = it.numericCode ?: NOT_AVAILABLE,
                name = it.name ?: NOT_AVAILABLE,
                population = it.population?.toLong() ?: 0L,
                area = it.area ?: 0.0,
                borders = it.borders?.filterNotNull() ?: listOf(),
                capitalName = it.capital ?: NOT_AVAILABLE,
                currencies = it.currencies?.filterNotNull()?.map { it.code ?: NOT_AVAILABLE } ?: listOf(),
                flagUrl = it.flag ?: EMPTY_STRING,
                languages = it.languages?.filterNotNull()?.map { it.name ?: NOT_AVAILABLE } ?: listOf(),
                nativeName = it.nativeName ?: NOT_AVAILABLE,
                region = it.region ?: NOT_AVAILABLE,
                regionalBlocks = it.regionalBlocs?.filterNotNull()?.map { it.name ?: NOT_AVAILABLE } ?: listOf()
            )
        }
    }

    companion object {
        private const val NOT_AVAILABLE = "n/a"
        private const val EMPTY_STRING = ""
    }
}