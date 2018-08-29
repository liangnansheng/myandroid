package com.xzone.studyexecutorservice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xzone.studyexecutorservice.utils.ScreenUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by xl on 2018/7/14.
 */

public class LeafLoadingView extends View {
    public static final String TAG = "LeafLoadingView";
    int TOTAL_PROGRESS = 100;
    int mProgressWidth;
    int mProgress = 0;
    int mCurrentProgressPosition = 0;
    int mTotalWidth;
    int mTotalHeight;

    int mLeftMargin = 0;
    int mRightMargin = 0;
    int mArcRadius;
    RectF mArcRectF;
    Paint mWhitePaint;
    Paint mOrangePaint;

    RectF mWhiteRectF;
    RectF mOrangeRectF;
    private int mArcRightLocation;
    private Rect mOuterSrcRect;
    private Rect mOuterDestRect;
    private long mLeafFloatTime = 3000;
    private int locationY;
    private float mMiddleAmplitude = 13;
    private float mAmplitudeDisparity = 5;
    private Bitmap mLeafBitmap;
    private Paint mBitmapPaint;
    private long mLeafRotateTime = 2000;
    private int mLeafWidth;
    private int mLeafHeight;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void setMLeftMargin(int leftMargin) {
        mLeftMargin = ScreenUtils.dip2px(getContext(), leftMargin);
        postInvalidate();
    }

    public int getLocationY(Leaf leaf) {
        //T＝ 2 * Math.PI/w;
        float w = (float) (2 * Math.PI / (float) mProgressWidth);
        float a = mMiddleAmplitude;
        switch (leaf.type) {
            case LITTLE:
                // 小振幅 ＝ 中等振幅 － 振幅差
                a = mMiddleAmplitude - mAmplitudeDisparity;
                break;
            case MIDDLE:
                a = mMiddleAmplitude;
                break;
            case BIG:
                // 小振幅 ＝ 中等振幅 + 振幅差
                a = mMiddleAmplitude + mAmplitudeDisparity;
                break;
            default:
                break;
        }
        //y = A(wx+Q）+h;

        int y = (int) (a * Math.sin(w * leaf.x) + 1 / 2 * mArcRadius);
        Log.i("getLocationY", y + "   ");
        return y;
    }

    class Leaf {
        int x, y;
        StartType type;
        int rotateAngle;
        int rotateDirection;
        long startTime;
    }

    enum StartType {
        LITTLE, MIDDLE, BIG;
    }

    // 叶子飘动一个周期所花的时间
    private static final long LEAF_FLOAT_TIME = 3000;

    private class LeafFatcory {
        private static final int MAX_LEAFS = 6;
        Random random = new Random();
        int time;

        public Leaf generateLeaf() {
            Leaf leaf = new Leaf();
            switch (random.nextInt(3)) {
                case 0:
                    leaf.type = StartType.LITTLE;
                    break;
                case 1:
                    leaf.type = StartType.MIDDLE;
                    break;
                case 2:
                    leaf.type = StartType.BIG;
                    break;
            }
            leaf.rotateAngle = random.nextInt(360);
            leaf.rotateDirection = random.nextInt(2);
            time += new Random().nextInt((int) LEAF_FLOAT_TIME);
            leaf.startTime = System.currentTimeMillis() + time;
            return leaf;
        }

        // 根据最大叶子数产生叶子信息
        public List<Leaf> generateLeafs() {
            return generateLeafs(MAX_LEAFS);
        }

        // 根据传入的叶子数量产生叶子信息
        public List<Leaf> generateLeafs(int leafSize) {
            List<Leaf> leafs = new LinkedList<Leaf>();
            for (int i = 0; i < leafSize; i++) {
                leafs.add(generateLeaf());
            }
            return leafs;
        }


    }

    LeafFatcory mLeafFactory;
    List<Leaf> mLeafInfos;

    public LeafLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        mLeftMargin = ScreenUtils.dip2px(context, 0);
        mRightMargin = ScreenUtils.dip2px(context, 14);
        mLeafFactory = new LeafFatcory();
        mLeafInfos = mLeafFactory.generateLeafs();
    }

    Bitmap mOuterBitmap;

    private void initPaint() {
        mWhitePaint = new Paint();
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setColor(Color.WHITE);
        mOrangePaint = new Paint();
        mOrangePaint.setAntiAlias(true);
        mOrangePaint.setColor(0xffffa800);

        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setDither(true);
        mBitmapPaint.setFilterBitmap(true);
    }

    private void initBitmap() {
        mOuterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.leaf_kuang);
        mLeafBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.leaf);
        mLeafWidth = mLeafBitmap.getWidth();
        mLeafHeight = mLeafBitmap.getHeight();
    }

    private void init() {
        initPaint();
        initBitmap();
    }

    private void getLeafLocation(Leaf leaf, long currentTime) {
        long intervalTime = currentTime - leaf.startTime;
        mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
        if (intervalTime < 0) {
            return;
        } else if (intervalTime > mLeafFloatTime) {
            leaf.startTime = System.currentTimeMillis() + new Random().nextInt((int) mLeafFloatTime);
        }
        float fraction = (float) intervalTime / mLeafFloatTime;
        leaf.x = (int) (mProgressWidth - mProgressWidth * fraction);
        leaf.y = getLocationY(leaf);
        Log.i("location", leaf.x + "     " + leaf.y + "   " + intervalTime + "fraction" + fraction + "mLeafFloatTime: " + mLeafFloatTime);
    }

    private void drawLeafs(Canvas canvas) {
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < mLeafInfos.size(); i++) {
            Leaf leaf = mLeafInfos.get(i);
            if (currentTime > leaf.startTime && leaf.startTime != 0) {
                getLeafLocation(leaf, currentTime);
                canvas.save();
                // 通过Matrix控制叶子旋转
                Matrix matrix = new Matrix();
                float transX = mLeftMargin + leaf.x;
                float transY = (float) (1.0 / 3 * getHeight() + leaf.y);
                Log.i(TAG, "left.x = " + leaf.x + "--leaf.y=" + leaf.y);
                matrix.postTranslate(transX, transY);
//                matrix.postScale(0.8f, 0.8f);
//                 通过时间关联旋转角度，则可以直接通过修改LEAF_ROTATE_TIME调节叶子旋转快慢
                float rotateFraction = ((currentTime - leaf.startTime) % mLeafRotateTime)
                        / (float) mLeafRotateTime;
                int angle = (int) (rotateFraction * 360);
                // 根据叶子旋转方向确定叶子旋转角度
                int rotate = leaf.rotateDirection == 0 ? angle + leaf.rotateAngle : -angle
                        + leaf.rotateAngle;
                matrix.postRotate(rotate, transX
                        + mLeafWidth / 2, transY + mLeafHeight / 2);
                canvas.drawBitmap(mLeafBitmap, matrix, mBitmapPaint);
                canvas.restore();
//                                canvas.drawBitmap(mLeafBitmap,matrix, mBitmapPaint);

            } else {
                continue;
            }

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        mCurrentProgressPosition = mProgressWidth * mProgress / TOTAL_PROGRESS;
        Log.i(TAG, "progress:" + mProgress
                + " \nmCurrentProgressPosition: "
                + mCurrentProgressPosition
                + " \nmArcRadius: " + mArcRadius
                + " \nmArcRightLocation: " + mArcRightLocation);

        if (mCurrentProgressPosition < mArcRadius) {
            //画白色圆弧
            canvas.drawArc(mArcRectF, 90, 180, false, mWhitePaint);
            //画白色矩形
            mWhiteRectF.left = mArcRightLocation;
            canvas.drawRect(mWhiteRectF, mWhitePaint);
            int v = (int) Math.toDegrees(Math.acos((mArcRadius - mCurrentProgressPosition) / (float) mArcRadius));
            drawLeafs(canvas);

            //画橘色圆弧
            canvas.drawArc(mArcRectF, 180 - v, 2 * v, false, mOrangePaint);
        } else {
            //画白色矩形
            mWhiteRectF.left = mCurrentProgressPosition;
            canvas.drawRect(mWhiteRectF, mWhitePaint);
            drawLeafs(canvas);
            //画橘色圆弧
            canvas.drawArc(mArcRectF, 90, 180, false, mOrangePaint);
            //画橘色矩形
            mOrangeRectF.left = mArcRightLocation;
            mOrangeRectF.top = mLeftMargin;

            if (mCurrentProgressPosition < mArcRightLocation) {
                mCurrentProgressPosition = mArcRightLocation;
            }
            mOrangeRectF.right = mCurrentProgressPosition;
            Log.e(TAG, "mOrangeRectF:" + mOrangeRectF.left + "   " + mOrangeRectF.right);
            canvas.drawRect(mOrangeRectF, mOrangePaint);

        }
        canvas.drawBitmap(mOuterBitmap, mOuterSrcRect, mOuterDestRect, mWhitePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTotalWidth = getMeasuredWidth();
        mTotalHeight = getMeasuredHeight();
        mProgressWidth = mTotalWidth - mLeftMargin - mRightMargin;
        mArcRadius = (mTotalHeight - (2 * mLeftMargin)) / 2;
        mArcRectF = new RectF(mLeftMargin, mLeftMargin, mLeftMargin + 2 * mArcRadius, mTotalHeight - mLeftMargin);
        mWhiteRectF = new RectF(mLeftMargin + mCurrentProgressPosition, mLeftMargin, mTotalWidth - mRightMargin, mTotalHeight - mLeftMargin);
        mOrangeRectF = new RectF(mLeftMargin + mArcRadius, mLeftMargin, mLeftMargin + mCurrentProgressPosition, mTotalHeight - mLeftMargin);
        mArcRightLocation = mLeftMargin + mArcRadius;
        mOuterSrcRect = new Rect(0, 0, mOuterBitmap.getWidth(), mOuterBitmap.getHeight());
        mOuterDestRect = new Rect(0, 0, mTotalWidth, mTotalHeight);
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }
}
