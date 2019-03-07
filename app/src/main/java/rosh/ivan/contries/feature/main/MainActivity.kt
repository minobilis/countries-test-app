package rosh.ivan.contries.feature.main

import android.os.Bundle
import rosh.ivan.contries.R
import rosh.ivan.contries.base.BaseActivity
import rosh.ivan.contries.common.NumberFormatter
import rosh.ivan.contries.feature.details.CountryDetailsFragment
import rosh.ivan.contries.feature.list.adapter.Callback
import rosh.ivan.contries.feature.list.CountriesFragment
import rosh.ivan.countries.domain.entity.Country

class MainActivity(override val layoutResource: Int = R.layout.activity_main) : BaseActivity(),
    Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listFragment = CountriesFragment.newInstance()

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, listFragment, listFragment.javaClass.toString())
                .commit()
    }

    override fun onCountryClick(country: Country) {
        val detailsFragment = CountryDetailsFragment.newInstance(
            country.name,
            country.nativeName,
            NumberFormatter.format(country.population),
            country.flagUrl,
            country.capitalName,
            country.regionalBlocks.joinToString(separator = ", ", limit = 10),
            country.region,
            country.area.toString(),
            country.borders.joinToString(separator = ", ", limit = 10),
            country.currencies.joinToString(separator = ", ", limit = 10),
            country.languages.joinToString(separator = ", ", limit = 10)
        )

        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.container, detailsFragment, detailsFragment.javaClass.toString())
                .addToBackStack(detailsFragment.javaClass.toString())
                .commit()
    }
}
