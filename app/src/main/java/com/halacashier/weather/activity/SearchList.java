package com.halacashier.weather.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.halacashier.weather.R;
import com.halacashier.weather.activity.adapter.CityListAdapter;
import com.halacashier.weather.databinding.ActivitySearchListBinding;
import com.halacashier.weather.model.currentweather.CurrentWeatherResponse;
import com.halacashier.weather.viewmodel.SearchListVM;
import java.util.ArrayList;
import java.util.List;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchList extends AppCompatActivity {
    ActivitySearchListBinding binding;
    CityListAdapter cityListAdapter;
    private SearchListVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_list);
        initViews();
    }

    private void initViews() {
        viewModel = new ViewModelProvider(this).get(SearchListVM.class);
        clickListeners();
    }

    private void clickListeners() {
        binding.etSearchLocation.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if(!binding.etSearchLocation.getText().toString().isEmpty()) {
                    hideKeyboard(v);
                    binding.progressCircular.setVisibility(View.VISIBLE);
                    binding.tvPlaceHolder.setVisibility(View.GONE);
                    viewModel.getCityWeather(binding.etSearchLocation.getText().toString());
                    getWeather();
                }
                return true;
            }
            return false;
        });

        binding.ivBack.setOnClickListener(view -> finish());
    }
    private void getWeather() {
            viewModel.data.observe(this, response -> {
                binding.progressCircular.setVisibility(View.GONE);

                if (response != null) {
                    List<CurrentWeatherResponse> currentWeatherResponses = new ArrayList<>();
                    currentWeatherResponses.add(response);
                    binding.setViewModel(viewModel);
                    cityListAdapter = null;
                    binding.rvCityList.invalidate();
                    cityListAdapter = new CityListAdapter(this, currentWeatherResponses);
                    binding.rvCityList.setAdapter(cityListAdapter);
                    binding.rvCityList.setVisibility(View.VISIBLE);
                    cityListAdapter.notifyDataSetChanged();
                    if (currentWeatherResponses.size() == 0) {
                        binding.tvPlaceHolder.setVisibility(View.VISIBLE);
                    }
                }
            });

    }
    public void hideKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}