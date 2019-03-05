package rosh.ivan.countries.domain.usecase

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import rosh.ivan.countries.domain.abstraction.CountryDataSource
import rosh.ivan.countries.domain.base.BaseNoRequestFlowableUseCase
import rosh.ivan.countries.domain.entity.Country
import javax.inject.Inject

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
class GetAllCountriesUseCase @Inject constructor(
    private val countryDataSource: CountryDataSource,
    compositeDisposable: CompositeDisposable
) :
    BaseNoRequestFlowableUseCase<List<Country>>(compositeDisposable) {
    override fun asFlowable(): Flowable<List<Country>> {
        return countryDataSource.getAll()
    }
}