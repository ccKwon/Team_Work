package com.example.set_a_goal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.litepal.LitePal;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.set_a_goal.util.MyLocationListener;
import com.example.set_a_goal.db.City;
import com.example.set_a_goal.util.WeatherUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView city;
    private Button mBtnaddgoal;
    private Button mBtncalendar;
    private Button mBtnweather;
    private LocationClient locationClient = null;
    private MyLocationListener myLocationListener = new MyLocationListener();
    private List<City> cityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        locationClient = new LocationClient((getApplicationContext()));
//        声明LocationClient类
        locationClient.registerLocationListener(myLocationListener);
//        注册监听函数

        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
//      如果开发者需要获得当前点的地址信息，此处必须为true

        locationClient.setLocOption(option);
//      mLocationClient为第二步初始化过的LocationClient对象
//      需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//      更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        locationClient.start();

        BDLocation location = new BDLocation();
//        myLocationListener.onReceiveLocation(location);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        cityList = (List<City>) LitePal.find(City.class, 1);
//        WeatherUtil.Getweather();
//        City city1 = LitePal.find(City.class, 1);

        mBtncalendar = findViewById(R.id.calendar);
        mBtnaddgoal=findViewById(R.id.btn_addgoal);
        mBtnweather = findViewById(R.id.weather);
        mBtnaddgoal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,AddContent.class);
                startActivity(intent);
            }
        });

        mBtncalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarView.class);
                startActivity(intent);
            }
        });

        mBtnweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

    }




}
