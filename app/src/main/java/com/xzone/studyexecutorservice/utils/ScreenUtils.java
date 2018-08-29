package com.xzone.studyexecutorservice.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;

/**
 * Created by xl on 2018/4/14.
 */

public class ScreenUtils {
    public static DisplayMetrics getDisplayMetrics(Window window){
        DisplayMetrics dm = new DisplayMetrics();

       window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
