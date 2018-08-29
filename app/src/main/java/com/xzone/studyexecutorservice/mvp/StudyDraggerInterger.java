package com.xzone.studyexecutorservice.mvp;

import com.xzone.studyexecutorservice.A;

import javax.inject.Inject;

/**
 * Created by xl on 2018/8/7.
 */

public class StudyDraggerInterger {
    public int m;

//    @Inject
//    public StudyDraggerInterger(Integer a) {
//        m = a;
//    }

    @Inject
    public StudyDraggerInterger(A c) {
        m = 10;
    }
}
