package com.halacashier.weather.networking;

import com.halacashier.weather.model.currentweather.CurrentWeatherResponse;
import com.halacashier.weather.model.daysweather.MultipleDaysWeatherResponse;
import com.halacashier.weather.model.fivedayweather.FiveDayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    /**
     * Get current weather of city
     *
     * @param q     String name of city
     * @param lat String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of {@link CurrentWeatherResponse}
     */
    @GET("weather")
    Call<CurrentWeatherResponse> getCurrentWeather(
            @Query("q") String q,
            @Query("lat") String lat,
            @Query("lon") String lang,
            @Query("appid") String appId
    );

    @GET("weather")
    Call<CurrentWeatherResponse> getCityWeather(
            @Query("q") String q,
            @Query("appid") String appId
    );

    /**
     * Get five days weather forecast.
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of {@link FiveDayResponse}
     */
    @GET("forecast")
    Call<FiveDayResponse> getFiveDaysWeather(
            @Query("q") String q,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("appid") String appId
    );

    /**
     * Get multiple days weather
     *
     * @param q     String name of city
     * @param units String units of response
     * @param lang  String language of response
     * @param appId String api key
     * @return instance of {@link MultipleDaysWeatherResponse}
     */
    @GET("forecast/daily")
    Call<MultipleDaysWeatherResponse> getMultipleDaysWeather(
            @Query("q") String q,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("cnt") int dayCount,
            @Query("appid") String appId
    );
}
