package rosh.ivan.contries.feature.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import rosh.ivan.contries.R
import rosh.ivan.countries.domain.entity.Country

internal class CountriesAdapter(private val clickListener: (Country) -> Unit) : ListAdapter<Country, CountryViewHolder>(
        DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.country_view_holder, parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindTo(getItem(position), clickListener)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Country> = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldCountry: Country, newCountry: Country): Boolean {
                return oldCountry.id === newCountry.id
            }

            override fun areContentsTheSame(oldCountry: Country, newCountry: Country): Boolean {
                return oldCountry == newCountry
            }
        }
    }
}