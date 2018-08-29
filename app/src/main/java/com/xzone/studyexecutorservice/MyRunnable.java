package com.xzone.studyexecutorservice;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xl on 2018/5/18.
 */

public class MyRunnable implements Runnable {
    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    @Override
    public void run() {
        Log.i("kfjlasjfas", "thread:" + Thread.currentThread().getName() + ",time:" + format.format(new Date()) + ",num:" + num + "start");
        try {//使线程睡眠，模拟线程阻塞情况
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("kfjlasjfas", "thread:" + Thread.currentThread().getName() + ",time:" + format.format(new Date()) + ",num:" + num + "end");
        Handler handler = new Handler();
        Looper.loop();
    }

    Integer num;

    public MyRunnable(int num) {
        this.num = num;
    }
}
