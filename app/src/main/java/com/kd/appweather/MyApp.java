package com.kd.appweather;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }
    Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
            throwable.printStackTrace();restart();
        }
    };

    private void restart() {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        PendingIntent pintent = PendingIntent.getActivity(this,0,intent,0);
        AlarmManager manager  = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.set(AlarmManager.RTC,System.currentTimeMillis()+1000,pintent);
        System.exit(0);
    }
    public static MyApp instance;
    public static Application getApp(){
        return instance;
    }

}
