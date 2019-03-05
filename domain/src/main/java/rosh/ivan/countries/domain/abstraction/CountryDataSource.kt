package rosh.ivan.countries.domain.abstraction

import io.reactivex.Flowable
import rosh.ivan.countries.domain.entity.Country

interface CountryDataSource {

    fun getAll(): Flowable<List<Country>> = Flowable.never()

    fun getById(id: String): Flowable<Country> = Flowable.never()
}