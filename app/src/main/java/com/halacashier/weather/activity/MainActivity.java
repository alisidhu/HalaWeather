package com.halacashier.weather.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.halacashier.weather.R;
import com.halacashier.weather.activity.adapter.WeatherListAdapter;
import com.halacashier.weather.databinding.ActivityMainBinding;
import com.halacashier.weather.model.common.WeatherItem;
import com.halacashier.weather.viewmodel.MainVM;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    WeatherListAdapter weatherListAdapter;
    private MainVM viewModel;
    ActivityMainBinding binding;
    ImageView ivCenter, ivList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        viewModel = new ViewModelProvider(this).get(MainVM.class);
        View bottomSheetView = findViewById(R.id.bottomNavigation);
        ivCenter = bottomSheetView.findViewById(R.id.ivCenterBtn);
        ivList = bottomSheetView.findViewById(R.id.ivList);
        clickListeners();
        weatherObserver();

    }

    private void clickListeners() {
        ivCenter.setOnClickListener(view -> gotToSearchList());
        ivList.setOnClickListener(view -> gotToSearchList());
    }

    private void weatherObserver() {
        viewModel.data.observe(this, response -> {
            if (response != null) {
                binding.setViewModel(viewModel);
                weatherListAdapter = null;
                List<WeatherItem> weatherItems = new ArrayList<>();
                for (int index = 0; index < 6; index++) {
                    weatherItems.addAll(response.getWeather());
                }
                weatherListAdapter = new WeatherListAdapter(this, weatherItems);
                binding.rvWeatherList.setAdapter(weatherListAdapter);
            }
        });
    }

    public void gotToSearchList() {
        Intent intent = new Intent(this, SearchList.class);
        startActivity(intent);
    }
}