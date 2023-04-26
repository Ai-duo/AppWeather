package com.kd.appweather;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.kd.appweather.beans.MarqueeView;

public class DataBindingSets {

    @BindingAdapter("setBackImage")
    public static void setBackImage(LinearLayout view, Integer index) {
        if (index == null) return;
        if (index == 0) {
            view.setBackgroundResource(R.drawable.bk1);
        } else if (index == 1) {
            view.setBackgroundResource(R.drawable.bk2);
        } else if (index == 2) {
            view.setBackgroundResource(R.drawable.bk3);
        }
    }

    @BindingAdapter("setFl")
    public static void setFl(TextView view, String fs) {
        if(TextUtils.isEmpty(fs)){
            view.setText("");
        }else{
            String s = fs.replace("m/s","");
            float f = Float.parseFloat(s);
            String ss = "";
            if(f<0.3){

            }else if(f<=1.5){
                ss = "1级";
            }else if(f<=3.3){
                ss = "2级";
            }else if(f<=5.4){
                ss = "3级";
            }else if(f<=7.9){
                ss = "4级";
            }else if(f<=10.7){
                ss = "5级";
            }else if(f<=13.8){
                ss = "6级";
            }else if(f<=17.1){
                ss = "7级";
            }else if(f<=20.7){
                ss = "8级";
            }else if(f<=24.4){
                ss = "9级";
            }else if(f<=28.4){
                ss = "10级";
            }else if(f<=32.6){
                ss = "11级";
            }else {
                ss = "12级";
            }
            view.setText(ss);
        }
    }

    @BindingAdapter("setText")
    public static void setText(MarqueeView view, String text) {
        if (TextUtils.isEmpty(text)) return;
        view.setContent(text);
    }

    @BindingAdapter("setBar")
    public static void setBar(BarChart view, String text) {

    }





    @BindingAdapter("setImage")
    public static void setText(ImageView view, String text) {

        if (TextUtils.isEmpty(text)) return;
        switch (text) {
            case "雨":
            case "小雨":
                view.setImageResource(R.drawable.xiaoyu);
                break;
            case "中雨":
                view.setImageResource(R.drawable.zhongyu);
                break;
            case "大雨":
                view.setImageResource(R.drawable.dayu);
                break;
            case "暴雨":
                view.setImageResource(R.drawable.baoyu);
                break;
            case "特大暴雨":
                view.setImageResource(R.drawable.tedabaoyu);
                break;
            case "雷阵雨":
                view.setImageResource(R.drawable.leizhenyu);
                break;
            case "冰雨":
                view.setImageResource(R.drawable.bingyu);
                break;
            case "小雪":
            case "雪":
                view.setImageResource(R.drawable.xiaoxue);
                break;
            case "中雪":
                view.setImageResource(R.drawable.zhongxue);
                break;
            case "大雪":
                view.setImageResource(R.drawable.daxue);
                break;
            case "雨夹雪":
                view.setImageResource(R.drawable.yujiaxue);
                break;
            case "阴有阵雨":
                view.setImageResource(R.drawable.yinyouzhenyu);
                break;
            case "阵雨":
                view.setImageResource(R.drawable.zhenyu);
                break;
            case "阵雪":
                view.setImageResource(R.drawable.zhenxue);
                break;
            case "雾":
                view.setImageResource(R.drawable.wu);
                break;
            case "沙尘暴":
                view.setImageResource(R.drawable.shachenbao);
                break;
            case "扬沙":
                view.setImageResource(R.drawable.yangsha);
                break;
            case "阴天":
            case "阴":
                view.setImageResource(R.drawable.yintian);
                break;
            case "月亮":
                view.setImageResource(R.drawable.yueliang);
                break;
            case "强沙尘暴":
                view.setImageResource(R.drawable.qiangshachenbao);
                break;
            case "晴":
            case "晴天":
                view.setImageResource(R.drawable.qing);
                break;
            case "多云":
            case "少云":
                view.setImageResource(R.drawable.duoyun);
                break;
            default:
                if (text.contains("到")) {
                    String[] s = text.split("到");
                    setText(view, s[1]);
                } else if (text.contains("转")) {
                    String[] s = text.split("转");
                    setText(view, s[1]);
                }


        }
    }
    @BindingAdapter("setWeainfo")
    public static void setWea(MarqueeView view,String info){
        view.setContent(info);
    }
     @BindingAdapter("isShow")

    public static void isShow(TextView view,boolean show){
        if(show){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
     }
}
