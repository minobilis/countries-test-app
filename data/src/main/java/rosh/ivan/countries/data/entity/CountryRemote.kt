package rosh.ivan.countries.data.entity

import com.google.gson.annotations.SerializedName

data class CountryRemote(
    @SerializedName("alpha2Code") val alpha2Code: String? = null,
    @SerializedName("alpha3Code") val alpha3Code: String? = null,
    @SerializedName("alternativeSpellings") val alternativeSpellings: List<String?>? = null,
    @SerializedName("area") val area: Double? = null,
    @SerializedName("borders") val borders: List<String?>? = null,
    @SerializedName("callingCodes") val callingCodes: List<String?>? = null,
    @SerializedName("capital") val capital: String? = null,
    @SerializedName("cioc") val cioc: String? = null,
    @SerializedName("currencies") val currencies: List<CurrenciesItemRemote?>? = null,
    @SerializedName("demonym") val demonym: String? = null,
    @SerializedName("flag") val flag: String? = null,
    @SerializedName("gini") val gini: Double? = null,
    @SerializedName("languages") val languages: List<LanguagesItemRemote?>? = null,
    @SerializedName("coordinates") val coordinates: List<Int?>? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("nativeName") val nativeName: String? = null,
    @SerializedName("numericCode") val numericCode: String? = null,
    @SerializedName("population") val population: Int? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("regionalBlocs") val regionalBlocs: List<RegionalBlocsItemRemote?>? = null,
    @SerializedName("subregion") val subregion: String? = null,
    @SerializedName("timezones") val timezones: List<String?>? = null,
    @SerializedName("topLevelDomain") val topLevelDomain: List<String?>? = null,
    @SerializedName("translations") val translations: TranslationsRemote? = null
)
