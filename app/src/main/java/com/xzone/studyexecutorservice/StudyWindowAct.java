package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by xl on 2018/6/20.
 */

public class StudyWindowAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button floatingButton = new Button(this);
        floatingButton.setText("button");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0,
                PixelFormat.TRANSPARENT
        );
        // flag 设置 Window 属性
        layoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        // type 设置 Window 类别（层级）
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        layoutParams.gravity = Gravity.CENTER;
        WindowManager windowManager = getWindowManager();
        windowManager.addView(floatingButton, layoutParams);
        Toast toast = new Toast(this);
        Message ms = Message.obtain();
        Message mss = new Message() ;
        floatingButton.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        Handler handler = new Handler(Looper.getMainLooper(),new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
        Handler handler1 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
        WeakReference<String> weakReference = new WeakReference<String>("1");
        String s = weakReference.get();
        toast.show();
        Toast.makeText(this,R.string.app_name,Toast.LENGTH_LONG).show();
//        Dialog dialog;
//        dialog.setContentView();
//        dialog.show();
//        dialog.dismiss();
//        WindowManagerim
//        PhoneW
//        WindowManager
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i("detach","detach");
    }
}
