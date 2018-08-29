package com.xzone.studyexecutorservice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by xl on 2018/5/24.
 */

public class MyPointView extends View {
    public MyPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPointView(Context context) {
        super(context);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        Log.i("x,y", getLeft() + " " + getTop() + " " + getRight() + " " + getBottom());
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int r = Math.min(width, height) / 2;
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, r, paint);
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        Log.i("onMeasureMyPointView", MeasureSpec.AT_MOST + " MeasureSpec.AT_MOST  " + MeasureSpec.EXACTLY + "MeasureSpec.EXACTLY");
        Log.i("onMeasureMyPointView", "heightSize:" + heightSize
                + ",\nheightMode:" + heightMode
                + ",\nwidthSize:" + widthSize
                + ",\nwidthMode:" + widthMode);
        int resultWidth = widthSize;
        int resultHeight = heightSize;

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                resultHeight = 400;
                break;
        }
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                resultWidth = 400;
                break;
        }
        setMeasuredDimension(resultWidth, resultHeight);
    }

    int x = 10;

    public void setPointRadius(int x) {
        this.x = x;
        invalidate();
    }

    public int getPointRadius() {
        return 50;
    }

    int mLastX;
    int mLastY;

    @Override
    public void computeScroll() {
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.i("djklasj", "rawX :" + rawX
                + " rawY :" + rawY
                + " x :" + x
                + " y :" + y
                +" getwidth: "+getWidth()
                +" getheight: "+getHeight()
        );
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = rawX - mLastX;
                int deltaY = rawY - mLastY;

                int traX = (int) (ViewHelper.getTranslationX(this) + deltaX);
                int traY = (int) (ViewHelper.getTranslationY(this) + deltaY);
                Log.e("djklasj", "deltaX :" + deltaX
                        + " deltaY :" + deltaY
                +" getTx: "+ViewHelper.getTranslationX(this)
                        +" getTy: "+ViewHelper.getTranslationY(this));
                ViewHelper.setTranslationX(this, traX );
                ViewHelper.setTranslationY(this, traY  );
invalidate();
postInvalidate();

                break;
        }
        mLastX = rawX;
        mLastY = rawY;
        return true;

    }
}