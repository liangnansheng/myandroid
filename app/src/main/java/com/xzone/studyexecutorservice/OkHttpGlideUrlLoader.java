package com.xzone.studyexecutorservice;

import android.content.Context;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by xl on 2018/8/29.
 */

public class OkHttpGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {
    private final ModelCache<GlideUrl, GlideUrl> modelCache;

    public OkHttpGlideUrlLoader(ModelCache<GlideUrl, GlideUrl> modelCache, OkHttpClient client) {
        this.modelCache = modelCache;
        this.client = client;
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private OkHttpClient okHttpClient;

        public Factory(OkHttpClient client) {
            this.okHttpClient = client;
        }

        public Factory() {
        }

        private final ModelCache<GlideUrl, GlideUrl> modelCache = new ModelCache<GlideUrl, GlideUrl>(500);

        @Override
        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new OkHttpGlideUrlLoader(modelCache,getOkHttpClient());

        }

        @Override
        public void teardown() {

        }


        private OkHttpClient getOkHttpClient() {
            if (okHttpClient == null) {
                okHttpClient = new OkHttpClient();
            }
            return okHttpClient;
        }
    }

    private OkHttpClient client;

    @Override
    public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
        GlideUrl url = model;
        if (modelCache != null) {
            url = modelCache.get(model, 0, 0);
            if (url == null) {
                modelCache.put(model, 0, 0, model);
                url = model;
            }
        }
        return new OkHttpFetcher(url, client);
    }


}
