package com.xzone.studyexecutorservice;

import android.util.Log;

/**
 * Created by xl on 2018/5/31.
 */

public class StudySynchronizeRunnable implements Runnable {
    int count = 0;

    @Override
    public void run() {
//        synchronized (this) {
//            for (int i = 0; i < 5; i++) {
//                try {
//                    Thread.sleep(100);
//                    Log.i("TESTTHREAD", Thread.currentThread().getName() + " " + i);
//                } catch (InterruptedException e) {
//
//                }
//            }
//        }
        add();
        print();
    }

    public synchronized void add() {
        for (int i = 0; i < 5; i++) {
            try {
                count++;
                Thread.sleep(100);
                Log.i("TESTTHREAD", Thread.currentThread().getName() + "  " + count);
            } catch (InterruptedException e) {

            }
        }
    }
    public       void print() {
        for (int i = 0; i < 5; i++) {
            try {
                count++;
                Thread.sleep(100);
                Log.i("TESTTHREAD", Thread.currentThread().getName() + "print " + count);
            } catch (InterruptedException e) {

            }
        }
    }
}
