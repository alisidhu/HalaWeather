package com.halacashier.weather.constants;

public class Constants {
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String IMAGE_URL = "https://openweathermap.org/img/wn/";
    public static final String UNITS = "metric";
    public static final String API_KEY = "";


    public static String getImageUrl(String icon) {
        return IMAGE_URL + icon + "@2x.png";
    }

}
