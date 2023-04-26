package com.kd.appweather.beans;


import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

public class FengSu extends WeaBasic{
    public String wea_fengsu;
    public Entry getEntry(int i){
        return new Entry(i,Float.parseFloat(wea_fengsu));
    }
    @Override
    public String toString() {
        return "WenDu{" +
                "update='" + updatetime + '\'' +
                ", wea_wendu='" + wea_fengsu + '\'' +
                '}';
    }
}
