package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.ViewTarget;


/**
 * Created by xl on 2018/8/25.
 */

public class GlideAct extends Activity {

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_glide);
        final ImageView iv = findViewById(R.id.iv);
        ImageView iv2 = findViewById(R.id.iv2);
        final String url = "http://guolin.tech/book.png";
        final String gifurl = "http://guolin.tech/test.gif";


        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("加载中");
        ProgressInterceptor.add(url, new ProgressInterceptor.ProgressListener() {
            @Override
            public void onProgress(int percent) {
                progressDialog.setProgress(percent);
            }
        });
        Glide.with(this)
                .load(gifurl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(new GlideDrawableImageViewTarget(iv){
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        progressDialog.dismiss();
                        ProgressInterceptor.remove(url);
                    }

                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        progressDialog.show();
                    }
                });
        ViewTarget<TextView, Bitmap> target = new ViewTarget<TextView, Bitmap>(new TextView(this)) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                getView();
            }
        };
        Glide.with(this)
                .load(R.drawable.bg)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(iv2);
    }

    class MyCircleTransform extends BitmapTransformation {
        public MyCircleTransform(BitmapPool bitmapPool) {
            super(bitmapPool);
        }

        public MyCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            int diameter = Math.min(toTransform.getWidth(), toTransform.getHeight());

            Log.i("transform", toTransform.getWidth() + ":   " + toTransform.getHeight());
            Log.i("transform", outWidth + ":   " + outHeight);

            final Bitmap toReuse = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
            final Bitmap result;
//            if (toReuse != null) {
//                result = toReuse;
//            } else {
            result = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888);
//            }

            int dx = (toTransform.getWidth() - diameter) / 2;
            int dy = (toTransform.getHeight() - diameter) / 2;
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP,
                    BitmapShader.TileMode.CLAMP);
//            if (dx != 0 || dy != 0) {
//                Matrix matrix = new Matrix();
//                matrix.setTranslate(-dx, -dy);
//                shader.setLocalMatrix(matrix);
//            }
            paint.setShader(shader);
            paint.setAntiAlias(true);
            float radius = diameter / 2f;

            canvas.drawColor(Color.YELLOW);
            paint.setStyle(Paint.Style.FILL);

            canvas.drawCircle(radius, radius, radius - 10, paint);
            Paint paint1 = new Paint();
            paint1.setColor(Color.RED);
            paint1.setStyle(Paint.Style.STROKE);
            paint1.setStrokeWidth(10);

            canvas.drawCircle(radius, radius, radius - 10, paint1);

            if (toReuse != null && !pool.put(toReuse)) {
                toReuse.recycle();
            }
            return result;
        }

        @Override
        public String getId() {
            return "com.orangetree.studyexecutorservice.mycircletransfrm";
        }
    }
}
