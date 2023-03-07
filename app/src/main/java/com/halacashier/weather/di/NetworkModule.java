package com.halacashier.weather.di;

import com.halacashier.weather.networking.ApiRequest;
import com.halacashier.weather.networking.ApiRequestImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(ActivityComponent.class)
public abstract class NetworkModule {
    @Binds
    public abstract ApiRequest bindApiRequest(ApiRequestImpl apiRequestImpl);
}
