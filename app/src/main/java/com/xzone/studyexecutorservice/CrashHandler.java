package com.xzone.studyexecutorservice;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by xl on 2018/6/29.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.i("adfad","12321321");
        new Thread(new Runnable() {
            @Override
            public void run() {
                        Toast.makeText(MyApp.mContext,"准备挂掉",Toast.LENGTH_SHORT).show();

            }
        }).start();

    }
}
