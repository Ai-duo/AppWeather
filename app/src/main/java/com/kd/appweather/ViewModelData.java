package com.kd.appweather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kd.appweather.beans.Elements;
import com.kd.appweather.beans.FengSu;
import com.kd.appweather.beans.Lunar;
import com.kd.appweather.beans.ShiDu;
import com.kd.appweather.beans.WenDu;
import com.kd.appweather.beans.YuLiang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ViewModelData extends ViewModel {
     private MutableLiveData<String> time = new MutableLiveData<>();
    private MutableLiveData<String> wea = new MutableLiveData<>();
    private MutableLiveData<SevenWea> seven = new MutableLiveData<>();
    private MutableLiveData<List<WenDu>> wd1 = new MutableLiveData<>();
    private MutableLiveData<List<ShiDu>> sd1 = new MutableLiveData<>();
    private MutableLiveData<List<FengSu>> fs1 = new MutableLiveData<>();
    private MutableLiveData<List<YuLiang>> yl1 = new MutableLiveData<>();
    private MutableLiveData<Elements> element = new MutableLiveData<>();
    private MutableLiveData<Boolean> dyl = new MutableLiveData<>();

    public MutableLiveData<String> getWea() {
        return wea;
    }

    public MutableLiveData<SevenWea> getSeven() {
        return seven;
    }

    public MutableLiveData<List<WenDu>> getWd1() {
        return wd1;
    }

    public MutableLiveData<List<ShiDu>> getSd1() {
        return sd1;
    }

    public MutableLiveData<List<FengSu>> getFs1() {
        return fs1;
    }

    public MutableLiveData<List<YuLiang>> getYl1() {
        return yl1;
    }

    public MutableLiveData<Elements> getElement() {
        return element;
    }

    public MutableLiveData<Boolean> getDyl(){
        return dyl;
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }
    public static boolean getNetState(Context context){
        boolean flag = true;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo  net = manager.getActiveNetworkInfo();
        if(net!=null&&net.isAvailable()){
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }
    public TimerTask dates;
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年M月dd日 HH:mm");
    private Timer timer,netTimer,timer1;
    public void initTask() {
        dates = new TimerTask() {
            @Override
            public void run() {
                Lunar lunar = new Lunar(Calendar.getInstance());
                time.postValue(dateFormat.format(new Date()) + "\n" + lunar.getAllDate());
            }
        };
        timer = new Timer();
        timer.schedule(dates, 0, 40 * 1000);
    }
    public MutableLiveData<String> getTimeLiveData(){
        return time;
    }
    public void getNetInfo(){
        netTimer = new Timer();
        netTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                seven.postValue(DataModel.getInstance().getSevenWea());
                wea.postValue(DataModel.getInstance().getWea());
                element.postValue(DataModel.getInstance().getSixELements());
                wd1.postValue(DataModel.getInstance().get12Wd());
                sd1.postValue(DataModel.getInstance().get12Shd());
                fs1.postValue(DataModel.getInstance().get12Fs());
                yl1.postValue(DataModel.getInstance().get12Yl());
            }
        }, 0, 5 * 60 * 1000);

        timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                for(int i=0;i<6;i++){
                    DataModel.getInstance().getYunTu(i);
                    DataModel.getInstance().getLeiDaTu(i);
                }
                dyl.postValue(true);
            }
        }, 0, 5 * 60 * 1000);
    }

}
