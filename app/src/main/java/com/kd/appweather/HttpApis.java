package com.kd.appweather;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface HttpApis {
    @GET("getnewzdzhourdata/58752")
    Call<ResponseBody> getElements();

    @GET("get7dayybdata/58752")
    Call<ResponseBody> getSevenWea();

    @GET("getdayybdata/58752")
    Call<ResponseBody> getWeather();

    @GET("cloud/{index}.jpg")
    Call<ResponseBody> getYunTu(@Path("index") int index);
    @GET("rad/{index}.gif")
    Call<ResponseBody> getLeiDa(@Path("index") int index);

    @GET("getzdz12hourdata/58752/wea_wendu")
    Call<ResponseBody> getWd();
    @GET("getzdz12hourdata/58752/wea_shidu")
    Call<ResponseBody> getSd();
    @GET("getzdz12hourdata/58752/wea_yuliang")
    Call<ResponseBody> getYl();
    @GET("getzdz12hourdata/58752/wea_fengsu")
    Call<ResponseBody> getFs();

}
