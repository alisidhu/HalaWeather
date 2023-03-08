package com.halacashier.weather.di;

import com.halacashier.weather.networking.ApiRequest;
import com.halacashier.weather.networking.RetrofitRequest;
import com.halacashier.weather.repository.MainRepo;
import com.halacashier.weather.repository.SearchListRepo;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class HalaCashierModule {

    @Provides
    public MainRepo provideMainRepo(){
        return new MainRepo();
    }
    @Provides
    public SearchListRepo provideSearchListRepo(){
        return new SearchListRepo();
    }

}
