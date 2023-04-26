package com.kd.appweather.beans;

import com.github.mikephil.charting.data.BarEntry;

public class ShiDu extends WeaBasic{
    public String wea_shidu;
    public BarEntry getEntry(int i){
        return new BarEntry(i,Float.parseFloat(wea_shidu));
    }
    @Override
    public String toString() {
        return "WenDu{" +
                "update='" + updatetime + '\'' +
                ", wea_wendu='" + wea_shidu + '\'' +
                '}';
    }
}
