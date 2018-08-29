package com.xzone.studyexecutorservice;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by xl on 2018/8/29.
 */

public class ProgressResponseBody extends ResponseBody {
    String url;
    ResponseBody body;
    ProgressInterceptor.ProgressListener listener;
    BufferedSource bufferedSource;

    public ProgressResponseBody(String url, ResponseBody body) {
        this.url = url;
        this.body = body;
        listener = ProgressInterceptor.getListener(url);
    }

    @Override
    public MediaType contentType() {
        return body.contentType();
    }

    @Override
    public long contentLength() {
        return body.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(new ProgressSource(body.source()));
        }
        return bufferedSource;
    }

    private class ProgressSource extends ForwardingSource {
        long currentCount;

        public ProgressSource(Source delegate) {
            super(delegate);
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            long read = super.read(sink, byteCount);
            long all = body.contentLength();
            if (read != -1) {
                currentCount += read;
                int currentpercent = (int) (100f * currentCount / all);

                Log.d("tProgreerewrw", "download progress is " + currentpercent);
                if (listener != null) {
                    listener.onProgress(currentpercent);
                }
            }
            return read;
        }
    }
}
