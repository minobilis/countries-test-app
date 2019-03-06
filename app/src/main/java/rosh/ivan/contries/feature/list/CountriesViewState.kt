@file:Suppress("ClassName")

package rosh.ivan.contries.feature.list

import rosh.ivan.countries.domain.entity.Country


sealed class CountriesViewState {
    object LOADING : CountriesViewState()
    data class DATA(val countries: List<Country>?) : CountriesViewState()
    data class NAVIGATE(val clinic: Country) : CountriesViewState()
    data class ERROR(val error: Throwable) : CountriesViewState()
}