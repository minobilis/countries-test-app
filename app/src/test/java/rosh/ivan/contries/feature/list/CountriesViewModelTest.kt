package rosh.ivan.contries.feature.list

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import rosh.ivan.contries.common.scheduler.TestSchedulerProvider
import rosh.ivan.countries.domain.entity.Country
import rosh.ivan.countries.domain.usecase.GetAllCountriesUseCase

/**
 * Created by Ivan on 3/7/19.
 */
class CountriesViewModelTest {

    val schedulerProvider = TestSchedulerProvider()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val commnads: BehaviorRelay<CountriesViewState> = BehaviorRelay.create()

    val viewStates: PublishRelay<CountriesViewState> = PublishRelay.create()

    val input = CountriesInput(
        PublishRelay.create(),
        PublishRelay.create()
    )

    @MockK
    lateinit var getAllCountriesUseCase: GetAllCountriesUseCase

    @InjectMockKs
    lateinit var viewModel: CountriesViewModel

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `init - data state`() {

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

        every { getAllCountriesUseCase.asFlowable() } returns Flowable.just(countries)

        viewModel.init()

        viewModel.viewStates
            .test()
            .assertValueAt(0) { it is CountriesViewState.DATA && it.countries == countries}
            .assertNoErrors()
    }
}