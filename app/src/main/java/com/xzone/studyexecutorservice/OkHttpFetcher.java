package com.xzone.studyexecutorservice;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by xl on 2018/8/29.
 */

public class OkHttpFetcher implements DataFetcher<InputStream> {
    private GlideUrl url;
    private OkHttpClient client;
    private volatile boolean isCancelled;
    InputStream inputStream;
    ResponseBody responseBody;

    public OkHttpFetcher(GlideUrl url, OkHttpClient client) {
        this.url = url;
        this.client = client;
    }

    @Override
    public InputStream loadData(Priority priority) throws Exception {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url.toURL());
        Map<String, String> headers = url.getHeaders();
        for (Map.Entry<String, String> entry : url.getHeaders().entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        requestBuilder.addHeader("httplib", "Okhttp");
        Request request = requestBuilder.build();
        if (isCancelled) {
            return null;
        }
        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();

        if (!response.isSuccessful() || responseBody == null) {
            throw new IOException("Request failed with code: " + response.code());
        }
        inputStream = responseBody.byteStream();
        return ContentLengthInputStream.obtain(inputStream, responseBody.contentLength());
    }

    @Override
    public void cleanup() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                // Ignore
            }
        }
        if (responseBody != null) {
            responseBody.close();
        }
    }

    @Override
    public String getId() {
        return url.getCacheKey();
    }

    @Override
    public void cancel() {
        isCancelled = true;
    }
}
