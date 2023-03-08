package com.halacashier.weather.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.halacashier.weather.model.currentweather.CurrentWeatherResponse;
import com.halacashier.weather.repository.MainRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainVM extends ViewModel {

    MainRepo mainRepo;
  public  MutableLiveData<CurrentWeatherResponse> data = new MutableLiveData<>();

    @Inject
    MainVM(MainRepo mainRepo) {
        this.mainRepo = mainRepo;
        data =  mainRepo.getWeather();
    }
}
