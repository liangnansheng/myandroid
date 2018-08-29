package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by xl on 2018/5/31.
 */

public class A extends Activity {
    Go a, b, c;

    @Inject
    public A() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        Log.i("test", "onCreate  A" + getIntent().getStringExtra("mynames") + "  taskId " + getTaskId());
        a = new Go();
        Log.i("sdfasf", a + "\n" + b + "\n" + c);

        Go b = new Go();
        Log.i("sdfasf", a + "\n" + b + "\n" + c);
//System.arraycopy();
        c = a;
        a = b;
        Log.i("sdfasf", a + "\n" + b + "\n" + c);
        MyHandler myHandler = new MyHandler(this);
        myHandler.sendMessageDelayed(Message.obtain(), 3000);
//        Executor;
        Response r;
//        r.body()
//        MultipartBody.Part a = MultipartBody.Part.createFormData()
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("abc");
            }
        }).flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                return null;
            }
        }).subscribe();
        ResponseBody body;

        Gson gson = new Gson();
        Object abc = gson.fromJson("abc", new TypeToken<String>() {
        }.getType());

//        OkHttpClient a;
//        a.newCall();
//        body.charStream();
//        Gson gson ;
//        gson.newJsonReader();
//        Call<String> call = new Retrofit.Builder().build().create(Customer.class).getCall();
//        FileDownloader.getImpl().create(url)
//                .setPath(path)
//                .setListener(new FileDownloadListener() {
//                    @Override
//                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//                    }
//
//                    @Override
//                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
//                    }
//
//                    @Override
//                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//                    }
//
//                    @Override
//                    protected void blockComplete(BaseDownloadTask task) {
//                    }
//
//                    @Override
//                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
//                    }
//
//                    @Override
//                    protected void completed(BaseDownloadTask task) {
//                    }
//
//                    @Override
//                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//                    }
//
//                    @Override
//                    protected void error(BaseDownloadTask task, Throwable e) {
//                    }
//
//                    @Override
//                    protected void warn(BaseDownloadTask task) {
//                    }
//                }).start();
    }

    static class MyHandler extends Handler {
        WeakReference<Activity> wk;

        MyHandler(Activity activity) {
            wk = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            System.gc();
            System.runFinalization();
            Activity o = wk.get();
            Log.i("nihao", o + "");
            if (o != null) {

            }
        }
    }

    class Go {
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    public void onClick(View v) {
        startActivity(new Intent(A.this, B.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("test", "onNewIntent  A" + getIntent().getStringExtra("mynam      es"));
        Log.i("test", "onNewIntent  A" + intent.getStringExtra("mynames"));


    }
}
