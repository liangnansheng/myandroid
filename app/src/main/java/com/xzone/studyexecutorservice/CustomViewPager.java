package com.xzone.studyexecutorservice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xl on 2018/7/13.
 */

public class CustomViewPager extends ViewGroup {
    Scroller mScroller;
    VelocityTracker velocityTracker;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        velocityTracker = VelocityTracker.obtain();
    }

    private int mLastX;
    private int mLastY;
    private int mLastInterceptX;
    private int mLastInterceptY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
//                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - mLastInterceptX) > Math.abs(y - mLastInterceptY)) {
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;
        mLastInterceptX = x;
        mLastInterceptY = y;
        return intercept;
    }

    int childIndex = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        velocityTracker.addMovement(event);
        int width = getWidth();
        int scrollX = getScrollX();
        int x = (int) event.getX();
        int y = (int) event.getY();
        int deltaX = x - mLastX;
        Log.i("fasdfdas", "width: " + width + " scrollX: " + scrollX + "  deltaX: " + deltaX + " mLastX: " + mLastX);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int distance = scrollX - childIndex * mChildWidth;
                if (Math.abs(distance) > mChildWidth / 2) {
                    if (distance > 0) {
                        childIndex++;
                    } else {
                        childIndex--;
                    }
                } else {
                    velocityTracker.computeCurrentVelocity(1000);
                    float xVelocity = velocityTracker.getXVelocity();
                    if (Math.abs(xVelocity) > 50) {
                        if (xVelocity > 0) {
                            childIndex--;
                        } else {
                            childIndex++;
                        }
                    }
                }
                childIndex = childIndex < 0 ? 0 : childIndex > getChildCount() - 1 ? getChildCount() - 1 : childIndex;
                Log.i("childIndex", childIndex + "");

                smoothScrool(mChildWidth * childIndex, 0);
                velocityTracker.clear();
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private void smoothScrool(int x, int y) {
        mScroller.startScroll(getScrollX(), getScrollY(), x - getScrollX(), y - getScrollY(), 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int childLeft = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            mChildWidth = child.getMeasuredWidth();
            if (child.getVisibility() != View.GONE) {
                child.layout(childLeft, 0, childLeft + child.getMeasuredWidth(), child.getMeasuredHeight());
                childLeft += child.getMeasuredWidth();
            }
        }
    }

    private int mChildWidth;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        View childAt = getChildAt(0);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(childAt.getMeasuredWidth() * getChildCount(), childAt.getMeasuredHeight());
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(childAt.getMeasuredWidth() * getChildCount(), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, childAt.getMeasuredHeight());
        }
    }
}
