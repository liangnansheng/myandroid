package com.xzone.studyexecutorservice;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xl on 2018/7/14.
 */

public class TestLeafAct extends Activity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            leafLoadingView.setProgress(msg.what);

            if (msg.what == 100) {
                timer.cancel();
            }
        }
    };
    LeafLoadingView leafLoadingView;

    private void showAnimation() {

        ObjectAnimator rotation = ObjectAnimator.ofFloat(findViewById(R.id.fv), "rotation", 0, 360);
        rotation.setDuration(1000);
        rotation.start();
    }

    Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_leaf);
        SeekBar seekBar = findViewById(R.id.sb_leftmargin);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                leafLoadingView.setMLeftMargin(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        leafLoadingView = findViewById(R.id.leaf);

        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                runOnUiThread(new Runnable() {
                    public void run() {
                        showAnimation();
                    }
                });
            }
        };
        timer.schedule(task, 0, 1000);


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(100);
                        Message ms = Message.obtain();
                        ms.what = i;
                        handler.sendMessage(ms);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();


    }
}
