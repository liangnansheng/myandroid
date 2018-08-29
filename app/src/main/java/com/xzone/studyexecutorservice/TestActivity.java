package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xzone.studyexecutorservice.utils.ScreenUtils;

import java.util.ArrayList;

/**
 * Created by xl on 2018/5/24.
 */

public class TestActivity extends Activity {
    CustomViewPager customVp;

    public static void setFitsSystemWindows(Activity activity, boolean value) {
        ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(value);
        }
    }
    private void createList(ViewGroup layout) {
        ListView lv = layout.findViewById(R.id.lv);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add(i + "  name");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_list_item, R.id.tv_name, datas);
        lv.setAdapter(adapter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asdfdas);
        //设置 paddingTop
        LayoutInflater inflater = LayoutInflater.from(this);
        int widthPixels = ScreenUtils.getDisplayMetrics(getWindow()).widthPixels;
        int heightPixels = ScreenUtils.getDisplayMetrics(getWindow()).heightPixels;
        customVp = findViewById(R.id.customVp);
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_layout, customVp, false);
            TextView tv = layout.findViewById(R.id.tv);
            layout.getLayoutParams().width = widthPixels;
            layout.getLayoutParams().height = heightPixels;
            tv.setText(i + "");
            createList(layout);
            customVp.addView(layout);
        }

//

//        setStatusBar();


    }

//    private void setStatusBar() {
//        ViewGroup rootView = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
//        rootView.setPadding(0, 60, 0, 0);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            //5.0 以上直接设置状态栏颜色
//            if (findViewById(R.id.iv).getVisibility() == View.VISIBLE) {
//                getWindow().setStatusBarColor(Color.parseColor("#ffffff"));
//
//            } else {
//                getWindow().setStatusBarColor(Color.parseColor("#152215"));
//
//            }
//        } else {
//            //根布局添加占位状态栏
//            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
//            View statusBarView = new View(this);
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    60);
//            statusBarView.setBackgroundColor(Color.RED);
//            decorView.addView(statusBarView, lp);
//        }
//    }
}

