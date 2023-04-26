package com.kd.appweather;

import android.text.TextUtils;

/*{"town_site_item":"K2159","town_name":"海曙","wea_logo":"宁波海曙12月01日15时发布的七天预报","DATE":"20211201","TIME":"1500",
"wea_txt1":"1日 :晴到少云;2～11℃","wea_txt2":"2日 :晴到少云;1～14℃","wea_txt3":"3日 :晴到少云;3～14℃","wea_txt4":"4日 :多云;5～16℃","wea_txt5":"5日 :多云到晴;7～18℃","wea_txt6":"6日 :多云;7～14℃","wea_txt7":"7日 :晴到多云;4～12℃"}*/
public class SevenWea {

    public String town_site_item;
    public String town_name;
    public String wea_logo;
    public String DATE;
    public String TIME;
    public String txt1_date;
    public String txt2_date;
    public String txt3_date;
    public String txt4_date;
    public String txt5_date;
    public String txt6_date;
    public String txt7_date;


    public int txt1_max;
    public int txt1_min;
    public String txt1;
    public int txt2_max;
    public int txt2_min;
    public String txt2;
    public int txt3_max;
    public int txt3_min;
    public String txt3;
    public int txt4_max;
    public int txt4_min;
    public String txt4;
    public int txt5_max;
    public int txt5_min;
    public String txt5;
    public int txt6_max;
    public int txt6_min;
    public String txt6;
    public int txt7_max;
    public int txt7_min;
    public String txt7;
    public String txt1_week;
    public String txt2_week;
    public String txt3_week;
    public String txt4_week;
    public String txt5_week;
    public String txt6_week;
    public String txt7_week;


    public String wea_txt1;
    public String wea_txt2;
    public String wea_txt3;
    public String wea_txt4;
    public String wea_txt5;
    public String wea_txt6;
    public String wea_txt7;

    public void build() {
        String[] ss1 = toArray(wea_txt1);
        txt1_date = ss1[0].replace(" :","");
        txt1 = ss1[1];
        txt1_week = ss1[1];
        txt1_min = (int)Float.parseFloat(ss1[2]);
        txt1_max = (int)Float.parseFloat(ss1[3]);

        String[] ss2 = toArray(wea_txt2);
        txt2_date = ss2[0].replace(" :","");;
        txt2 = ss2[1];
        txt2_week = ss2[1];
        txt2_min = (int)Float.parseFloat(ss2[2]);
        txt2_max = (int)Float.parseFloat(ss2[3]);

        String[] ss3 = toArray(wea_txt3);
        txt3_date = ss3[0].replace(" :","");;
        txt3 = ss3[1];
        txt3_week = ss3[1];
        txt3_min = (int)Float.parseFloat(ss3[2]);
        txt3_max = (int)Float.parseFloat(ss3[3]);

        String[] ss4 = toArray(wea_txt4);
        txt4_date = ss4[0].replace(" :","");;
        txt4 = ss4[1];
        txt4_week = ss4[1];
        txt4_min = (int)Float.parseFloat(ss4[2]);
        txt4_max = (int)Float.parseFloat(ss4[3]);

        String[] ss5 = toArray(wea_txt5);
        txt5_date = ss5[0].replace(" :","");;
        txt5 = ss5[1];
        txt5_week = ss5[1];
        txt5_min = (int)Float.parseFloat(ss5[2]);
        txt5_max = (int)Float.parseFloat(ss5[3]);

        String[] ss6 = toArray(wea_txt6);
        txt6_date = ss6[0].replace(" :","");;
        txt6 = ss6[1];
        txt6_week = ss6[1];
        txt6_min = (int)Float.parseFloat(ss6[2]);
        txt6_max = (int)Float.parseFloat(ss6[3]);

        String[] ss7 = toArray(wea_txt7);
        txt7_date = ss7[0].replace(" :","");;
        txt7 = ss7[1];
        txt7_week = ss7[1];
        txt7_min = (int)Float.parseFloat(ss7[2]);
        txt7_max = (int)Float.parseFloat(ss7[3]);


    }

    public String chang(String s) {
        if (TextUtils.isEmpty(s)) return "";
        return s.replace(" :", ";").replace("℃", "").replace("～",";");
    }

    public String[] toArray(String s) {
        String s1 = chang(s);
        String[] ss = s1.split(";");
        return ss;
    }

}
