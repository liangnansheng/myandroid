package com.xzone.studyexecutorservice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xl on 2018/8/24.
 */

public class CanvasDrawView extends View {
    Paint paint;
    Paint linePaint;
    private Bitmap bitmap;

    public CanvasDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.leaf);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

//        canvas.drawArc(0, 0, 200, 200, 0, 120, false, paint);
//
////        canvas.drawLine(100,100,500,100,linePaint);
//        canvas.save();
//
//        canvas.translate(0, 200);
//        canvas.drawArc(0, 0, 200, 200, 0, 120, true, paint);
//
//        canvas.drawLine(105, 100, 500, 100, linePaint);
//        linePaint.setColor(Color.RED);
//
//        canvas.restore();
//        canvas.drawCircle(300, 300, 60, paint);
//        canvas.drawLine(100, 100, 500, 100, linePaint);
//
//        matrix.postRotate(45);
        Matrix matrix = new Matrix();
        canvas.drawBitmap(bitmap, matrix, paint);
        matrix.postRotate(180);
        matrix.setTranslate(bitmap.getWidth(),bitmap.getHeight());
        canvas.drawBitmap(bitmap,matrix,paint);
//
//        Rect src = new Rect(20, 20, 40, 40);//取bitmap上src区域的部分图像
//        Rect dst = new Rect(100, 100, 200, 200);//绘制的最终区域，一定填满
//
//        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.leaf), src, dst, paint);
//
//        canvas.drawColor(Color.RED, PorterDuff.Mode.ADD);


//        canvas.drawRect(new RectF(80, 80, 180, 180), linePaint);
//        canvas.save();
////        canvas.rotate(45);
//        canvas.rotate(45, 300, 300);
//        canvas.drawRect(new RectF(80, 80, 180, 180), linePaint);
//        canvas.restore();


//        canvas.drawRect(new RectF(0, 0, 180, 180), linePaint);
//        canvas.save();
//        canvas.skew(1, 0);//画布X轴倾斜45度
//        canvas.translate(300, 0);//画布X轴倾斜45度
//
//        linePaint.setColor(Color.RED);
//        canvas.drawRect(new RectF(0, 0, 180, 180), linePaint);
//        canvas.restore();


    }
}
