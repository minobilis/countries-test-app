package rosh.ivan.countries.data.mapper

import rosh.ivan.countries.data.entity.CountryRemote
import rosh.ivan.countries.domain.entity.Country
import javax.inject.Inject
import io.reactivex.functions.Function
import rosh.ivan.countries.data.R
import rosh.ivan.countries.domain.abstraction.ResourceProvider

class RemoteCountryToEntityBatchMapper @Inject constructor(
        private val resourceProvider: ResourceProvider
) : Function<List<CountryRemote>, List<Country>> {

    override fun apply(remoteList: List<CountryRemote>): List<Country> {
        return remoteList.map {
            Country(
                    id = it.numericCode?: "unknown",
                    name = it.name
                            ?: resourceProvider.getString(R.string.error_missing_country_name),
                    population = it.population?.toLong() ?: 0L
            )
        }
    }
}