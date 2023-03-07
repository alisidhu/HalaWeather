package com.halacashier.weather.viewmodel;

import androidx.lifecycle.ViewModel;

import com.halacashier.weather.repository.MainRepo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainVM extends ViewModel {

    MainRepo mainRepo;

    @Inject
    MainVM(MainRepo mainRepo) {
        this.mainRepo = mainRepo;
        mainRepo.getWeather();
    }
}
