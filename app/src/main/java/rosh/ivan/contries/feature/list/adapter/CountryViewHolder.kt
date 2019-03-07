package rosh.ivan.contries.feature.list.adapter

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import rosh.ivan.contries.R
import rosh.ivan.contries.common.NumberFormatter
import rosh.ivan.contries.common.svg.GlideApp
import rosh.ivan.contries.common.svg.SvgSoftwareLayerSetter
import rosh.ivan.countries.domain.entity.Country


/**
 * Created by Ivan on 1/31/19.
 */
internal class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var nameLabel: TextView = itemView.findViewById(R.id.english_name_label)

    private var nativeNameLabel: TextView = itemView.findViewById(R.id.local_name_label)

    private var populationLabel: TextView = itemView.findViewById(R.id.population_label)

    private var flagImage: ImageView = itemView.findViewById(R.id.flag_image)

    private var requestBuilder: RequestBuilder<PictureDrawable>? = null

    fun bindTo(country: Country, clickListener: (Country) -> Unit) {

        val context = itemView.context

        nameLabel.text = country.name

        nativeNameLabel.text = country.name

        populationLabel.text = NumberFormatter.format(country.population)

        itemView.setOnClickListener { clickListener(country) }

        requestBuilder = GlideApp.with(context)
            .`as`(PictureDrawable::class.java)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(SvgSoftwareLayerSetter())

        if (country.flagUrl.isNotEmpty()) {
            val uri = Uri.parse(country.flagUrl)
            requestBuilder?.load(uri)?.into(flagImage)
        }
    }
}
