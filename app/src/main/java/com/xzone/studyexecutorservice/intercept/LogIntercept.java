package com.xzone.studyexecutorservice.intercept;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xl on 2018/8/9.
 */

public class LogIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = request.newBuilder().url("http://www.baidu.com").build();
        Log.i("afas",request+"  "+newRequest);
        return  chain.proceed(newRequest);
    }
}
