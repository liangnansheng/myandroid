package com.xzone.studyexecutorservice.mvp;

import android.util.Log;

import com.xzone.studyexecutorservice.A;

import javax.inject.Inject;

/**
 * Created by xl on 2018/8/7.
 */

public class StudyDragger2Presenter {

    StudyDragger2View mView;
A b;
    public void loadData() {
        //得到shuju
        Log.i("adfjsklasd", "getData()");
//        Target.SIZE_ORIGINAL
        mView.upDateView();
    }

    @Inject
    StudyDragger2Presenter(StudyDragger2View view,A a) {
        mView = view;this.b = a;
    }
}
