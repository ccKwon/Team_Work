package com.example.set_a_goal.db;

import org.litepal.crud.LitePalSupport;

public class Province extends LitePalSupport {

    private int id;
//    记录省份名称
    private String provinceName;
//  记录省的代号
    private String provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
