package com.example.set_a_goal;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.set_a_goal.db.City;
import com.example.set_a_goal.gson.JsonRootBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import interfaces.heweather.com.interfacesmodule.bean.Code;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.air.now.AirNow;
import interfaces.heweather.com.interfacesmodule.bean.air.now.AirNowCity;
import interfaces.heweather.com.interfacesmodule.bean.basic.Basic;
import interfaces.heweather.com.interfacesmodule.bean.basic.Update;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.Forecast;
import interfaces.heweather.com.interfacesmodule.bean.weather.forecast.ForecastBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.Lifestyle;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.LifestyleBase;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.NowBase;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;

public class WeatherActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;

    public SwipeRefreshLayout swipeRefresh;

    private ScrollView weatherLayout;

    private Button navButton;

    private TextView titleCity;

    private TextView titleUpdateTime;

    private TextView degreeText;

    private TextView weatherInfoText;

    private TextView aqiText;

    private TextView pm25Text;

    private TextView comfortText;

    private TextView sportText;

    private TextView qltyText;

    private TextView wind_dirText;

    private TextView wind_scText;

    private TextView wind_spdText;

    private ImageView bingPicImg;

    private String mWeatherId;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        // 初始化各控件
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        aqiText = (TextView) findViewById(R.id.aqi_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        qltyText = findViewById(R.id.Qlty);
        comfortText = (TextView) findViewById(R.id.comfort_text);
        sportText = (TextView) findViewById(R.id.sport_text);
        wind_dirText = findViewById(R.id.Wind_dir);
        wind_scText = findViewById(R.id.Wind_sc);
        wind_spdText = findViewById(R.id.Wind_spd);
//        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
//        navButton = (Button) findViewById(R.id.nav_button);




        HeConfig.init("HE1912051710431137", "893a4a753a794876b0f4523c0ecd9c1a");
        HeConfig.switchToFreeServerNode();
        City city = LitePal.find(City.class, 1);
        String cityLocation = city.getCityName();

        HeWeather.getWeatherNow(WeatherActivity.this, cityLocation, Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherNowBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            @Override
            public void onSuccess(Now dataObject) {
//                Log.i("TAG", " Weather Now onSuccess: " + new Gson().toJson(dataObject));
                //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因
                if ( Code.OK.getCode().equalsIgnoreCase(dataObject.getStatus()) ){
                    //此时返回数据
                    NowBase now = dataObject.getNow();
                    Basic basic = dataObject.getBasic();
                    Update update = dataObject.getUpdate();
                    degreeText.setText(now.getTmp() + "℃");
                    titleCity.setText(basic.getLocation());
                    titleUpdateTime.setText(update.getLoc());
                    weatherInfoText.setText(now.getCond_txt());

                    wind_dirText.setText(now.getWind_dir());
                    wind_scText.setText(now.getWind_sc());
                    wind_spdText.setText(now.getWind_spd());
                } else {
                    //在此查看返回数据失败的原因
                    String status = dataObject.getStatus();
                    Code code = Code.toEnum(status);
                    Log.i("TAG", "failed code: " + code);
                }

            }
        });

        HeWeather.getWeatherForecast(WeatherActivity.this, cityLocation, Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherForecastBeanListener() {
            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            @Override
            public void onSuccess(Forecast forecast) {
//                Log.i("TAG", " Weather Now onSuccess: " + new Gson().toJson(forecast));
//                Log.i("TAG", " Weather Now onSuccess: " + new Gson().toJson(forecast.getDaily_forecast()));
                if ( Code.OK.getCode().equalsIgnoreCase(forecast.getStatus()) ){
                    //此时返回数据
                    String forecast11 = new Gson().toJson(forecast.getDaily_forecast());
                    String maxtem = null;
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(forecast11);
                        maxtem = jsonArray.getString(1);
                        Map map = new Gson().fromJson(maxtem, Map.class);
                        System.out.println(map.get("tmp_max"));
                        TextView date_text = findViewById(R.id.date_text);
                        TextView info_text = findViewById(R.id.info_text);
                        TextView max_text = findViewById(R.id.max_text);
                        TextView min_text = findViewById(R.id.min_text);

                        date_text.setText((String) map.get("date"));
                        info_text.setText((String)map.get("cond_txt_d"));
                        max_text.setText("最高温度：" + map.get("tmp_max") + "℃");
                        min_text.setText("最低温度：" + map.get("tmp_min") + "℃");
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                } else {
                    //在此查看返回数据失败的原因
                    String status = forecast.getStatus();
                    Code code = Code.toEnum(status);
                    Log.i("TAG", "failed code: " + code);
                }

            }
        });

        HeWeather.getWeatherLifeStyle(WeatherActivity.this, cityLocation, Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherLifeStyleBeanListener(){
            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            @Override
            public void onSuccess(Lifestyle lifestyle) {
                Log.i("TAG", " Weather Now onSuccess: " + new Gson().toJson(lifestyle));
                    String lifestyle11 = new Gson().toJson(lifestyle.getLifestyle());
                    String lifestyle_info = null;
                try {

                    JSONArray jsonArray = new JSONArray(lifestyle11);
                    lifestyle_info = jsonArray.getString(1);
                    Map map = new Gson().fromJson(lifestyle_info, Map.class);
                    comfortText.setText((String)map.get("brf"));
                    sportText.setText((String)map.get("txt"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        HeWeather.getAirNow(WeatherActivity.this, cityLocation, Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultAirNowBeansListener() {
            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            @Override
            public void onSuccess(AirNow airNow) {
                AirNowCity airNowCity = airNow.getAir_now_city();
                aqiText.setText(airNowCity.getAqi());
                pm25Text.setText(airNowCity.getPm25());
                qltyText.setText(airNowCity.getQlty());
            }
        });

    }

}
