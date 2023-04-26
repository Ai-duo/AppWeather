package com.kd.appweather.beans;


import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

public class YuLiang extends WeaBasic{
    public String wea_yuliang;
    public Entry getEntry(int i){
        return new Entry(i,Float.parseFloat(wea_yuliang));
    }
    @Override
    public String toString() {
        return "WenDu{" +
                "update='" + updatetime + '\'' +
                ", wea_wendu='" + wea_yuliang + '\'' +
                '}';
    }
}
