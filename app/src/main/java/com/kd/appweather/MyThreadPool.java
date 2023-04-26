package com.kd.appweather;

import java.util.concurrent.ThreadPoolExecutor;

public class MyThreadPool {

    private static MyThreadPool instance;
    private ThreadPoolExecutor pool;
    private MyThreadPool (){
       // pool  = new ThreadPoolExecutor();
    }
    public static MyThreadPool getInstance(){
        if(instance==null){
            synchronized (MyThreadPool.class){
                if(instance==null){
                    instance = new MyThreadPool();
                }
            }
        }
        return instance;
    }
    public synchronized void  addRunnbale(Runnable runnable){
        pool.execute(runnable);
    }
}
