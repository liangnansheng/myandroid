package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xl on 2018/6/19.
 */

public class OkHttpAcitivty extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_okhttp);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get();
            }
        });
    }

    private void get(){
        //创建Okhttpclient对象
        OkHttpClient client = new OkHttpClient().newBuilder().build();
//        new OkHttpClient.Builder().cache(new Cache());
//        OkHttpClientMa
//        RequestBody.create(MediaType.parse())
        Request.Builder builder   = new Request.Builder();
        MultipartBody.Builder builder1 = new MultipartBody.Builder();
//        new Cach

//        getCacheDir()
        builder.build();
        //创建一个请求
        Request request = new Request.Builder().url("https://www.baidu.com").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                response.body().string();
                Log.i("onResponse",Thread.currentThread().getName()+"  \n   "+response.toString());
//                ((TextView)findViewById(R.id.tv)).setText(response.toString());
            }
        });
    }
    private void post(){
        OkHttpClient client = new OkHttpClient();

//        RequestBody requestBody = RequestBody.create()
        FormBody.Builder builder = new  FormBody.Builder();
        builder.add("a",1+"");
        FormBody formBody = builder.build();
//        RequestBody.create(MediaType.parse())
        Request request = new Request.Builder().url("").post(formBody).build();


//        FormBody
    }
}
