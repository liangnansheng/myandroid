package com.xzone.studyexecutorservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by xl on 2018/6/1.
 */

public class LocalService extends Service {
    ScheduledExecutorService mPool;
    int count;

    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    PassData mPassData;

    public void setmPassData(PassData mPassData) {
        this.mPassData = mPassData;
    }

    public interface PassData {
        void onPassData(int num);
    }

    IBinder binder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPool = Executors.newScheduledThreadPool(1);
        mPool.scheduleAtFixedRate(timerTask, 0, 1000, TimeUnit.MILLISECONDS);
        return binder;
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if(mPassData!=null){
                count++;
                if (count == 1000) {
                    count = 0;
                }
                mPassData.onPassData(count);
            }

        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"service start",Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (mPool != null) {
            mPool.shutdown();
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "LocalService Destory", Toast.LENGTH_SHORT).show();
    }


}
