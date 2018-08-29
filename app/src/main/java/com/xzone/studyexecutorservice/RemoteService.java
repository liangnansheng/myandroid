package com.xzone.studyexecutorservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by xl on 2018/6/12.
 */

public class RemoteService extends Service {
    //    private List<Book> mBooks = new ArrayList<>();
    CopyOnWriteArrayList<Book> mBooks = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListeners = new CopyOnWriteArrayList<>();
    Binder stub = new BookManager.Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            Log.i("addBook", Thread.currentThread().getName() + "   " + "addbook");
            mBooks.add(book);
        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            return mBooks;
        }

        @Override
        public void registListener(IOnNewBookArrivedListener listener) throws RemoteException {
            Log.i("registListener" ,listener+"");
            if (!mListeners.contains(listener)) {
                mListeners.add(listener);
            }
        }

        @Override
        public void unRegistListener(com.xzone.studyexecutorservice.IOnNewBookArrivedListener listener) throws RemoteException {
            if (mListeners.contains(listener)) {
                mListeners.remove(listener);
            }
        }
    };


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(RemoteService.this, "收到信息了", Toast.LENGTH_SHORT).show();
            Messenger mesengerReply = msg.replyTo;
            Message obtain = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putString("reply", "嗯你的消息我已经收到");
            obtain.setData(bundle);
            try {
                mesengerReply.send(obtain);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
    Messenger messenger = new Messenger(handler);


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        return messenger.getBinder();
        return stub;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBooks.add(new Book("语文",21.5));
        mBooks.add(new Book("数学",43.0));
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                    Book book = new Book("new book"+mBooks.size(),new Random().nextDouble());
                    mBooks.add(book);
                    for(IOnNewBookArrivedListener listener :mListeners){
                        try {
                            if(listener!=null)
                                listener.onNewBookArrived(book);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

    }
}
