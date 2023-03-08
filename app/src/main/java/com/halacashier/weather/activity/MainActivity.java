package com.halacashier.weather.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.halacashier.weather.R;
import com.halacashier.weather.activity.adapter.WeatherListAdapter;
import com.halacashier.weather.databinding.ActivityMainBinding;
import com.halacashier.weather.model.currentweather.Main;
import com.halacashier.weather.viewmodel.MainVM;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    BottomSheetBehavior bottomSheetBehavior;
    WeatherListAdapter weatherListAdapter;
    RecyclerView recyclerView;
    private MainVM viewModel;
    ConstraintLayout clBottomSheet;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        clBottomSheet = findViewById(R.id.clBottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(clBottomSheet);
        viewModel = new ViewModelProvider(this).get(MainVM.class);
        View constraintLayout = binding.coordinatorLayout.getRootView();
        recyclerView = constraintLayout.findViewById(R.id.rvWeatherList);

        bottomSheetItemDetail();
        getWeather();
    }

    /**
     * Call back and  initial state of bottom sheet
     */
    void bottomSheetItemDetail() {
        //initial state of bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN: {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        break;
                    }

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }

        });
    }

    private void getWeather() {
        viewModel.data.observe(this, response -> {
            if (response != null) {
                binding.setViewModel(viewModel);
                weatherListAdapter = null;
                weatherListAdapter = new WeatherListAdapter(this, response.getWeather());

                recyclerView.setAdapter(weatherListAdapter);
            }
        });
    }

}