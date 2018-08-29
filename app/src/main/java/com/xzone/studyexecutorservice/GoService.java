package com.xzone.studyexecutorservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by xl on 2018/6/14.
 */

public class GoService extends Service {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.e("dfasfasfas1232",Thread.currentThread().getName()+"    ");

            super.handleMessage(msg);
        }
    };

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            Log.i("dfasfasfas1232",Thread.currentThread().getName()+"    ");
            long endTime = System.currentTimeMillis() + 5*1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (Exception e) {
                    }
                }
            }
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread("liangnasheng", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler  = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message msg = Message.obtain();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);
        Message msg1 = Message.obtain();
        msg1.arg1 = startId;
        handler.sendMessage(msg1);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"service die",Toast.LENGTH_SHORT).show();
    }
}
