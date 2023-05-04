package com.kd.appweather;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface HttpApis {
    @GET("getnewzdzhourdata/P7723")
    Call<ResponseBody> getElements();

    @GET("get7dayybdata/54901")
    Call<ResponseBody> getSevenWea();

    @GET("getdayybdata/58549")
    Call<ResponseBody> getWeather();

    @GET("cloud/{index}.jpg")
    Call<ResponseBody> getYunTu(@Path("index") int index);
    @GET("rad/{index}.gif")
    Call<ResponseBody> getLeiDa(@Path("index") int index);

    @GET("getzdz12hourdata/58549/wea_wendu")
    Call<ResponseBody> getWd();
    @GET("getzdz12hourdata/58549/wea_shidu")
    Call<ResponseBody> getSd();
    @GET("getzdz12hourdata/58549/wea_yuliang")
    Call<ResponseBody> getYl();
    @GET("getzdz12hourdata/58549/wea_fengsu")
    Call<ResponseBody> getFs();
    @GET("getdayybdata/{id}")
    Call<ResponseBody> getWeather(@Path("id") String id);

    @GET("getqxo2data/57962")
    Call<ResponseBody> getOxy();
}
