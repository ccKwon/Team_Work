package com.example.set_a_goal.util;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.example.set_a_goal.db.City;
import com.example.set_a_goal.db.City;
import com.example.set_a_goal.db.Province;

public class MyLocationListener extends BDAbstractLocationListener {

    @Override
    public void onReceiveLocation(BDLocation location) {
        City city = new City();
        city.setCityName(location.getCity());
        city.setCityCode(location.getCityCode());
        city.save();

        Province province = new Province();
        province.setProvinceCode(location.getProvince());
    }




}
