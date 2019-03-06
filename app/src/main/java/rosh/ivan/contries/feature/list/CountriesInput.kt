package rosh.ivan.contries.feature.list

import com.jakewharton.rxrelay2.PublishRelay
import rosh.ivan.countries.domain.entity.Country
import javax.inject.Inject

class CountriesInput @Inject constructor(
        val listItemClicks: PublishRelay<Country>,
        val refreshRequests: PublishRelay<Unit>
)
