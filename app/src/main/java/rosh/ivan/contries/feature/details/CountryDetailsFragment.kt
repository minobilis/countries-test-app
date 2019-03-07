package rosh.ivan.contries.feature.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.details_fragment.*
import rosh.ivan.contries.R
import rosh.ivan.contries.base.BaseFragment


class CountryDetailsFragment : BaseFragment() {

    override val layoutResourceId: Int get() = R.layout.details_fragment

    private lateinit var name: String
    private lateinit var nativeName: String
    private lateinit var population: String
    private lateinit var flagUrl: String
    private lateinit var capitalName: String
    private lateinit var regionalBlocks: String
    private lateinit var region: String
    private lateinit var area: String
    private lateinit var borders: String
    private lateinit var currencies: String
    private lateinit var languages: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
            name = get(NAME_KEY) as String
            nativeName = get(NATIVE_NAME_KEY) as String
            population = get(POPULATION_KEY) as String
            flagUrl = get(FLAG_URL_KEY) as String
            capitalName = get(CAPITAL_NAME_KEY) as String
            regionalBlocks = get(REGIONAL_BLOCKS_KEY) as String
            region = get(REGION_KEY) as String
            area = get(AREA_KEY) as String
            borders = get(BORDERS_KEY) as String
            currencies = get(CURRENCIES_KEY) as String
            languages = get(LANGUAGES_KEY) as String
        }
    }

    override fun onStart() {
        super.onStart()

        activity?.setTitle(R.string.country_details_screen_title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        english_name_label.text = name
        local_name_label.text = nativeName
        population_label.text = population
        capital_label.text = capitalName
        regional_blocks_label.text = regionalBlocks
        region_label.text = region
        area_label.text = area
        borders_label.text = borders
        currencies_label.text = currencies
        languages_label.text = languages
    }

    companion object {
        fun newInstance(
            name: String,
            nativeName: String,
            population: String,
            flagUrl: String,
            capitalName: String,
            regionalBlocks: String,
            region: String,
            area: String,
            borders: String,
            currencies: String,
            languages: String
        ): CountryDetailsFragment {
            val fragment = CountryDetailsFragment()
            fragment.arguments = bundleOf(
                NAME_KEY to name,
                NATIVE_NAME_KEY to nativeName,
                POPULATION_KEY to population,
                FLAG_URL_KEY to flagUrl,
                CAPITAL_NAME_KEY to capitalName,
                REGIONAL_BLOCKS_KEY to regionalBlocks,
                REGION_KEY to region,
                AREA_KEY to area,
                BORDERS_KEY to borders,
                CURRENCIES_KEY to currencies,
                LANGUAGES_KEY to languages
            )
            return fragment
        }

        private const val NAME_KEY = "name_key"
        private const val NATIVE_NAME_KEY = "native_name_key"
        private const val POPULATION_KEY = "population_key"
        private const val FLAG_URL_KEY = "flag_url_key"
        private const val CAPITAL_NAME_KEY = "capital_name_key"
        private const val REGIONAL_BLOCKS_KEY = "regional_blocks_key"
        private const val REGION_KEY = "region_key"
        private const val AREA_KEY = "area_key"
        private const val BORDERS_KEY = "borders_key"
        private const val CURRENCIES_KEY = "currencies_key"
        private const val LANGUAGES_KEY = "languages_key"
    }
}
