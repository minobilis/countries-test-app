package rosh.ivan.countries.data.rest;

import io.reactivex.Single;
import retrofit2.http.GET;
import rosh.ivan.countries.data.entity.CountryRemote;

import java.util.List;

public interface RestClient {

    @GET("all?fields=name;nativeName;population;flag;capital;currencies;languages;regionalBlocs;region;area;borders")
    Single<List<CountryRemote>> getAllCountries();
}