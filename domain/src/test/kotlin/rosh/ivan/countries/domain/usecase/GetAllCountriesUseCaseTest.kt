package rosh.ivan.countries.domain.usecase

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import rosh.ivan.countries.domain.abstraction.CountryDataSource
import rosh.ivan.countries.domain.entity.Country

/**
 * Created by Ivan on 3/7/19.
 */
class GetAllCountriesUseCaseTest {

    @MockK
    lateinit var compositeDisposable: CompositeDisposable

    @MockK
    lateinit var countryDataSource: CountryDataSource

    @InjectMockKs
    lateinit var getAllCountriesUseCase: GetAllCountriesUseCase

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `get assessment - valid value`() {
        val countries = mutableListOf(
            Country(
                "id",
                "name",
                10_000L,
                "native",
                "flag",
                "capital",
                listOf(),
                "region",
                1000.0,
                listOf(),
                listOf(),
                listOf()
            )
        )
        every { countryDataSource.getAll() } returns Flowable.just(countries)

        getAllCountriesUseCase.asFlowable()
            .test()
            .assertNoErrors()
            .assertValue{ it == countries }
    }
}