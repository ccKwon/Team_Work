package com.example.set_a_goal.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Weather {
    public String status;
    public Basic basic;

    public AQI aqi;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;


    @SerializedName("hourly_forecast")
    public List<Hourly> hourlyList;
}
