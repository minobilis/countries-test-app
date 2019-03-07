package rosh.ivan.contries.feature.list.adapter

import rosh.ivan.countries.domain.entity.Country

interface Callback {

    fun onCountryClick(country: Country)
}