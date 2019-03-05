package rosh.ivan.contries.di.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import rosh.ivan.contries.common.AndroidResourceProvider
import rosh.ivan.countries.data.extra.Constants
import rosh.ivan.countries.data.remote.RemoteCountryDataSource
import rosh.ivan.countries.data.rest.RestClient
import rosh.ivan.countries.domain.abstraction.CountryDataSource
import rosh.ivan.countries.domain.abstraction.ResourceProvider

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
        internal fun rxAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

        @Reusable
        @Provides
        @JvmStatic
        internal fun gsonConverterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

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
    }
}
