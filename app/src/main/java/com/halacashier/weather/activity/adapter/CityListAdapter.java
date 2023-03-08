package com.halacashier.weather.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.halacashier.weather.R;
import com.halacashier.weather.constants.Constants;
import com.halacashier.weather.model.common.WeatherItem;
import com.halacashier.weather.model.currentweather.CurrentWeatherResponse;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private Context context;
    List<CurrentWeatherResponse> weatherItems;

    public CityListAdapter(Context context, List<CurrentWeatherResponse> weatherItems) {
        this.context = context;
        this.weatherItems = weatherItems;
    }

    @NonNull
    @Override
    public CityListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_city_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityListAdapter.ViewHolder viewHolder, int i) {
        CurrentWeatherResponse weatherItem = weatherItems.get(i);
        viewHolder.tvWeatherValue.setText(weatherItem.getMain().getHumidity());
        viewHolder.tvCityName.setText(weatherItem.getName());
        viewHolder.tvWeatherStatus.setText(weatherItem.getWeather().get(0).getMain());
        Glide.with(context)
                .load(Constants.getImageUrl(weatherItem.getWeather().get(0).getIcon()))
                .into(viewHolder.ivWeatherIcon);
    }

    @Override
    public int getItemCount() {
        return weatherItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivWeatherIcon;
        private final TextView tvWeatherValue;
        private  TextView tvCityName,tvWeatherStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivWeatherIcon = (ImageView) itemView.findViewById(R.id.ivWeatherIcon);
            tvWeatherValue = (TextView) itemView.findViewById(R.id.tvWeatherValue);
            tvCityName = (TextView) itemView.findViewById(R.id.tvCityName);
            tvWeatherStatus = (TextView) itemView.findViewById(R.id.tvWeatherStatus);
        }
    }
}