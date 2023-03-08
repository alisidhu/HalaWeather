package com.halacashier.weather.repository;

import androidx.lifecycle.MutableLiveData;

import com.halacashier.weather.model.currentweather.CurrentWeatherResponse;
import com.halacashier.weather.networking.ApiRequest;
import com.halacashier.weather.networking.RetrofitRequest;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepo {
   @Inject
   ApiRequest apiRequest;
    public MainRepo(){
        this.apiRequest = RetrofitRequest.provideRetrofitInstance(RetrofitRequest.provideOkHttp());
    }

    public MutableLiveData<CurrentWeatherResponse> getWeather(){
        final MutableLiveData<CurrentWeatherResponse> data = new MutableLiveData<>();

        apiRequest.getCurrentWeather("","10.99","10.99","58ea430537a7db93beef322e4e2081aa").enqueue(new Callback<CurrentWeatherResponse>() {

            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {

                if(response.isSuccessful() && response.body()!=null){
                    data.setValue(response.body());
                    System.out.print(data);
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
            }
        });
        return data;
    }
}
