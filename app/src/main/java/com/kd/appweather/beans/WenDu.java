package com.kd.appweather.beans;


import com.github.mikephil.charting.data.BarEntry;

public class WenDu extends WeaBasic{
    public String wea_wendu;
    public BarEntry getEntry(int i){
        return new BarEntry(i,Float.parseFloat(wea_wendu));
    }
    @Override
    public String toString() {
        return "WenDu{" +
                "update='" + updatetime + '\'' +
                ", wea_wendu='" + wea_wendu + '\'' +
                '}';
    }
}
