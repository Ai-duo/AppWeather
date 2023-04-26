package com.kd.appweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.kd.appweather.beans.Elements;
import com.kd.appweather.beans.FengSu;
import com.kd.appweather.beans.ShiDu;
import com.kd.appweather.beans.WenDu;
import com.kd.appweather.beans.YuLiang;
import com.kd.appweather.databinding.ActivityMainBinding;
import com.kd.appweather.fragments.FragmentFifth;
import com.kd.appweather.fragments.FragmentFirst;
import com.kd.appweather.fragments.FragmentFour;
import com.kd.appweather.fragments.FragmentSecond;
import com.kd.appweather.fragments.FragmentSix;
import com.kd.appweather.fragments.FragmentThird;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ViewModelData vmd;
    ActivityMainBinding mainBinding;
    FragmentSecond second;
    FragmentFirst first;
    FragmentFour four;
    FragmentFifth fifth;
    FragmentSix six;
    FragmentThird third;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        vmd = ViewModelProviders.of(this).get(ViewModelData.class);
        initObserver();
        vmd.initTask();
        initFragment();
        vmd.getNetInfo();
    }
    private Timer timer;
    int index = 0;
    private void initFragment(){
        first = new FragmentFirst();
        second = new FragmentSecond();
        third = new FragmentThird();
        four = new FragmentFour();
        fifth = new FragmentFifth();
        six = new FragmentSix();
        timer  = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(index==6000)index = 0;
                final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (index%6){
                    case 0:
                        transaction.replace(R.id.contenter,first).commit();
                        mainBinding.setBackIndex(0);
                        mainBinding.setUpdate("");
                        mainBinding.setTitle("天气实况"+update);
                        mainBinding.setIsshow(true);
                        break;
                    case 1:
                        transaction.replace(R.id.contenter,second).commit();
                        mainBinding.setBackIndex(1);
                        mainBinding.setTitle("");
                        mainBinding.setIsshow(false);
                        break;
                    case 2:
                        transaction.replace(R.id.contenter,third).commit();
                        break;
                    case 3:
                        mainBinding.setBackIndex(2);
                        transaction.replace(R.id.contenter,four).commit();
                        mainBinding.setTitle("七天预报");
                        mainBinding.setIsshow(true);
                        break;
                    case 4:
                        transaction.replace(R.id.contenter,fifth).commit();
                        mainBinding.setTitle("");
                        mainBinding.setUpdate("雷达图");
                        mainBinding.setIsshow(false);
                        break;
                    case 5:
                        transaction.replace(R.id.contenter,six).commit();
                        mainBinding.setUpdate("云图");
                        break;
                }
               index++;

            }
        }, 0, 15 * 1000);

    }
    String update = "";
    public void initObserver(){
        vmd.getTimeLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mainBinding.setDate(s);
            }
        });
        vmd.getElement().observe(this, new Observer<Elements>() {
            @Override
            public void onChanged(Elements s) {
                update = s.updatetime;
              first.update(s);
              second.updateSd(s.wea_shidu);
              second.updateWd(s.wea_wendu);
              third.updateYl(s.wea_yuliang);
              third.updateFs(s.wea_fengsu);
            }
        });

        vmd.getWea().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i("TAG","天气："+s);
                mainBinding.setWea(s);
            }
        });
        vmd.getSeven().observe(this, new Observer<SevenWea>() {
            @Override
            public void onChanged(SevenWea s) {
                Log.i("TAG","天气："+s);

                four.updateInfo(s);
            }
        });
        vmd.getWd1().observe(this, new Observer<List<WenDu>>() {
            @Override
            public void onChanged(List<WenDu> s) {
                second.upteWds(s);
            }
        });
        vmd.getSd1().observe(this, new Observer<List<ShiDu>>() {
            @Override
            public void onChanged(List<ShiDu> s) {
                second.upteSds(s);
            }
        });
        vmd.getFs1().observe(this, new Observer<List<FengSu>>() {
            @Override
            public void onChanged(List<FengSu> fengSus) {
                third.updateFs(fengSus);
            }
        });
        vmd.getYl1().observe(this, new Observer<List<YuLiang>>() {
            @Override
            public void onChanged(List<YuLiang> yuLiangs) {
                third.updateYl(yuLiangs);
            }
        });
        vmd.getDyl().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                fifth.update();
                six.update();
            }
        });
    }
}