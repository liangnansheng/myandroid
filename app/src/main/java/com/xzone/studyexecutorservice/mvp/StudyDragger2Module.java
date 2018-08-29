package com.xzone.studyexecutorservice.mvp;

import com.xzone.studyexecutorservice.A;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xl on 2018/8/7.
 */
@Module
public class StudyDragger2Module {

    StudyDragger2View mView;

    A a;

    public StudyDragger2Module(StudyDragger2View view, A a) {
        mView = view;
        this.a = a;
    }



    @Provides
    StudyDragger2View getView() {
        return mView;
    }

    @Provides
    Integer getNum() {
        if (a != null) {
            return 10;
        }
        return 5;
    }


}
