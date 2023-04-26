package com.kd.appweather.beans;

public class WeaBasic {
    public String updatetime;
     public String getHour(){
        String[] time = updatetime.split(" ");
        String hours = time[1].substring(0,time[1].indexOf(":"));
        return hours;
    }
}
