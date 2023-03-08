package com.halacashier.weather.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.halacashier.weather.base.interfaces.ResultData;
import com.halacashier.weather.model.currentweather.CurrentWeatherResponse;
import com.halacashier.weather.repository.SearchListRepo;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SearchListVM extends ViewModel {

    SearchListRepo searchListRepo;
    public MutableLiveData<CurrentWeatherResponse> data;

    @Inject
    SearchListVM(SearchListRepo searchListRepo) {
        this.searchListRepo = searchListRepo;
    }


    public void getCityWeather(String city){
        MutableLiveData<CurrentWeatherResponse> weatherResult = searchListRepo.getWeather(city);
        if (weatherResult!=null) {
            data = weatherResult;
            System.out.print(data);
        }
    }
}
