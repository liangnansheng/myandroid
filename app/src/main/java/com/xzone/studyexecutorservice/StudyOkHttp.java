package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xl on 2018/8/9.
 */

public class StudyOkHttp extends Activity {
    TextView textView;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText(msg.obj + "");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_okhttp);
        textView = findViewById(R.id.tv);

        OkHttpClient ok =
                new OkHttpClient.Builder()
//                                    .addInterceptor(new LogIntercept())
                        .build();

        Request request = new Request.Builder().url("http://www.163.com")
                .build();
        ok.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message msg = Message.obtain();
                msg.obj = string;
                handler.sendMessage(msg);
            }
        });


    }
}
