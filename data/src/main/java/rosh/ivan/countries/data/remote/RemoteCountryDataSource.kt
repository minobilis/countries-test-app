package rosh.ivan.countries.data.remote

import io.reactivex.Flowable
import rosh.ivan.countries.data.mapper.RemoteCountryToEntityBatchMapper
import rosh.ivan.countries.data.rest.RestClient
import rosh.ivan.countries.domain.abstraction.CountryDataSource
import rosh.ivan.countries.domain.entity.Country
import javax.inject.Inject

/**
 * Created by Ivan on 3/5/19.
 */
class RemoteCountryDataSource @Inject constructor(
    private val restClient: RestClient,
    private val batchMapper: RemoteCountryToEntityBatchMapper
) : CountryDataSource {

    override fun getAll(): Flowable<List<Country>> =
        restClient.allCountries.toFlowable()
            .map(batchMapper)
}