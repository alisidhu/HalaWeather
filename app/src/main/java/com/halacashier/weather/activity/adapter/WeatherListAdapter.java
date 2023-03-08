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

import java.util.ArrayList;
import java.util.List;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {

    private Context context;
    List<WeatherItem> weatherItems;

    public WeatherListAdapter(Context context, List<WeatherItem> weatherItems) {
        this.context = context;
        this.weatherItems = weatherItems;
    }

    @NonNull
    @Override
    public WeatherListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bs_item_weather_capsule, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherListAdapter.ViewHolder viewHolder, int i) {
        WeatherItem weatherItem = weatherItems.get(i);
        viewHolder.tvWeatherTime.setText(weatherItem.getMain());
        viewHolder.tvWeatherTemp.setText(weatherItem.getMain());
        Glide.with(context)
                .load(Constants.getImageUrl(weatherItem.getIcon()))
                .into(viewHolder.ivWeatherIcon);
    }

    @Override
    public int getItemCount() {
        return weatherItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivWeatherIcon;
        private final TextView tvWeatherTime;
        private final TextView tvWeatherTemp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivWeatherIcon = (ImageView) itemView.findViewById(R.id.ivWeatherIcon);
            tvWeatherTime = (TextView) itemView.findViewById(R.id.tvWeatherTime);
            tvWeatherTemp = (TextView) itemView.findViewById(R.id.tvWeatherTemp);
        }
    }
}