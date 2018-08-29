package com.xzone.studyexecutorservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xl on 2018/8/29.
 */

public class ProgressInterceptor implements Interceptor {
    static Map<String, ProgressListener> map = new HashMap<>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Response newResponse = response.newBuilder().body(new ProgressResponseBody(request.url().toString(), response.body())).build();
        return newResponse;
    }

    public static void add(String url, ProgressListener listener) {
        map.put(url, listener);
    }

    public static void remove(String url) {
        map.remove(url);
    }

    public static ProgressListener getListener(String url) {
        return map.get(url);
    }

    public interface ProgressListener {
        void onProgress(int percent);
    }
}
