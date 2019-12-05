package com.example.set_a_goal.db;

//import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

public class City extends LitePalSupport {

    private int id;
//  记录城市名称
    private String cityName;
//  记录城市代码
    private String cityCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }


}
