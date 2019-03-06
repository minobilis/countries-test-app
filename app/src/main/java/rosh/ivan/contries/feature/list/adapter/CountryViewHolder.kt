package rosh.ivan.contries.feature.list.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rosh.ivan.contries.R
import rosh.ivan.contries.common.NumberFormatter
import rosh.ivan.countries.domain.entity.Country


/**
 * Created by Ivan on 1/31/19.
 */
internal class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var nameLabel: TextView = itemView.findViewById(R.id.english_name_label)

    private var nativeNameLabel: TextView = itemView.findViewById(R.id.local_name_label)

    private var populationLabel: TextView = itemView.findViewById(R.id.population_label)

    fun bindTo(country: Country, clickListener: (Country) -> Unit) {

        val context = itemView.context

        nameLabel.text = country.name

        nativeNameLabel.text = country.name

        populationLabel.text = NumberFormatter.format(country.population)

        itemView.setOnClickListener { clickListener(country) }
    }
}
