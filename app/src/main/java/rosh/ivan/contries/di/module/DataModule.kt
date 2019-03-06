package rosh.ivan.contries.di.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rosh.ivan.contries.common.AndroidResourceProvider
import rosh.ivan.contries.feature.list.CountriesInput
import rosh.ivan.contries.feature.list.CountriesViewState
import rosh.ivan.countries.data.extra.Constants
import rosh.ivan.countries.data.remote.RemoteCountryDataSource
import rosh.ivan.countries.data.rest.RestClient
import rosh.ivan.countries.domain.abstraction.CountryDataSource
import rosh.ivan.countries.domain.abstraction.ResourceProvider
import rosh.ivan.countries.domain.entity.Country

@Module
abstract class DataModule {

    @Reusable
    @Binds
    abstract fun countryDataSource(remoteCountryDataSource: RemoteCountryDataSource): CountryDataSource

    @Reusable
    @Binds
    abstract fun resourceProvider(androidResourceProvider: AndroidResourceProvider): ResourceProvider

    @Module
    companion object {

        @Reusable
        @Provides
        @JvmStatic
        internal fun rxAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

        @Reusable
        @Provides
        @JvmStatic
        internal fun gsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

        @Reusable
        @Provides
        @JvmStatic
        internal fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

        @Reusable
        @Provides
        @JvmStatic
        internal fun retrofit(
            okHttpClient: OkHttpClient,
            rxAdapterFactory: CallAdapter.Factory,
            gsonAdapterFactory: Converter.Factory
        ): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.REST_COUNTRIES_BASE_URL)
            .addCallAdapterFactory(rxAdapterFactory)
            .addConverterFactory(gsonAdapterFactory)
            .build()

        @Reusable
        @Provides
        @JvmStatic
        internal fun restService(retrofit: Retrofit): RestClient = retrofit.create<RestClient>(RestClient::class.java)

        @JvmStatic
        @Provides
        internal fun providePublishCountriesViewState(): PublishRelay<CountriesViewState> =
                PublishRelay.create()

        @JvmStatic
        @Provides
        internal fun provideBehaviorCountriesViewState(): BehaviorRelay<CountriesViewState> =
                BehaviorRelay.create()

        @JvmStatic
        @Provides
        internal fun provideCountryPublishRelay(): PublishRelay<Country> =
                PublishRelay.create()

        @JvmStatic
        @Provides
        internal fun provideUnitPublishRelay(): PublishRelay<Unit> =
                PublishRelay.create()
    }
}
