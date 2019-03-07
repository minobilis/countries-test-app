package rosh.ivan.countries.domain.entity

/**
 * Created by Ivan on 3/5/19.
 */
data class Country(
    val id: String,
    val name: String,
    val population: Long,
    val nativeName: String,
    val flagUrl: String,
    val capitalName: String,
    val regionalBlocks: List<String>,
    val region: String,
    val area: Double,
    val borders: List<String>,
    val currencies: List<String>,
    val languages: List<String>
)