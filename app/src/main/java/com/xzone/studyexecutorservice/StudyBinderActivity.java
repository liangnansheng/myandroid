package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xl on 2018/6/19.
 */

public class StudyBinderActivity extends Activity {
    boolean isBind;
    LocalService.LocalBinder localBinder;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ((TextView) findViewById(R.id.tv)).setText(msg.getData().getString("reply") + "");
        }
    };
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("MyService", "onServiceConnected   " + name);
//            binder = (MyService.MyBinder) service;
            localBinder = (LocalService.LocalBinder) service;

            localBinder.getService().setmPassData(new LocalService.PassData() {
                @Override
                public void onPassData(int num) {
                    Log.i("onPassData", num + "  T" + Thread.currentThread().getName());
                    Message obtain = Message.obtain();
                    obtain.what = num;
                    handler.sendMessage(obtain);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MyService", "onServiceDisconnected   " + name);
            localBinder = null;
        }

    };
    IOnNewBookArrivedListener listener = new IOnNewBookArrivedListener() {
        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            Log.i("listener", book + "");

        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    };

    IOnNewBookArrivedListener listenerBinder = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book book) throws RemoteException {

            Log.i("listenerBinder", book + " "+ Thread.currentThread().getName());
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.btn_start_service:
                    Intent intent = new Intent(StudyBinderActivity.this, LocalService.class);
                    startService(intent);
                    break;
                case R.id.btn_stop_service:
                    intent = new Intent(StudyBinderActivity.this, LocalService.class);
                    stopService(intent);
                    break;
                case R.id.btn_bind_service:
                    intent = new Intent(StudyBinderActivity.this, LocalService.class);
                    isBind = true;
                    bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                    break;
                case R.id.btn_unbind_service:
                    if (isBind) {
                        unbindService(serviceConnection);
                        isBind = false;
                    }
                    break;
                case R.id.go_service:
                    intent = new Intent(StudyBinderActivity.this, GoService.class);
                    startService(intent);
                    break;
                case R.id.bind_remote_service:
                    intent = new Intent(StudyBinderActivity.this, RemoteService.class);
                    bindService(intent, new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                            //Messenger心事信使
//                            Message msg = Message.obtain();
//                            Messenger messenger = new Messenger(service);
//                            Messenger messengerGet= new Messenger(handler);
//                            msg.replyTo = messengerGet;
//                            try {
//                                messenger.send(msg);
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
                            BookManager bookManager = BookManager.Stub.asInterface(service);
                            try {
                                service.linkToDeath(new IBinder.DeathRecipient() {
                                    @Override
                                    public void binderDied() {

                                    }
                                },0);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            try {
                                bookManager.addBook(new Book("ab", 10.2));
                                Log.i("onServiceConnected", Thread.currentThread().getName() + "   " + bookManager.getBooks() + "");
                                bookManager.registListener(listener);
                                bookManager.registListener(listenerBinder);

                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName name) {

                        }
                    }, BIND_AUTO_CREATE);
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_study_bind);
        findViewById(R.id.go_service).setOnClickListener(onClickListener);
        findViewById(R.id.btn_start_service).setOnClickListener(onClickListener);
        findViewById(R.id.btn_bind_service).setOnClickListener(onClickListener);
        findViewById(R.id.bind_remote_service).setOnClickListener(onClickListener);
        findViewById(R.id.btn_unbind_service).setOnClickListener(onClickListener);
        findViewById(R.id.btn_stop_service).setOnClickListener(onClickListener);


    }
}
