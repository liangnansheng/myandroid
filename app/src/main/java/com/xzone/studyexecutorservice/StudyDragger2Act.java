package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xzone.studyexecutorservice.mvp.DaggerStudyDraggerComponent;
import com.xzone.studyexecutorservice.mvp.StudyDragger2Module;
import com.xzone.studyexecutorservice.mvp.StudyDragger2Presenter;
import com.xzone.studyexecutorservice.mvp.StudyDragger2View;
import com.xzone.studyexecutorservice.mvp.StudyDraggerInterger;
import com.xzone.studyexecutorservice.mvp.TeacherModule;

import javax.inject.Inject;

/**
 * Created by xl on 2018/8/7.
 */

public class StudyDragger2Act extends Activity implements StudyDragger2View {
    @Inject
    StudyDragger2Presenter presenter;
    @Inject
    StudyDraggerInterger mStudyDraggerInteger;
    @Inject
    Teacher mTeacher;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_dragger_layout);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadData();
            }
        });
        DaggerStudyDraggerComponent.builder().
                studyDragger2Module(new StudyDragger2Module(this,new A())).
                teacherModule(new TeacherModule()).build().inject(this);

//
    }

    @Override
    public void upDateView( ) {
        ((TextView)findViewById(R.id.tv)).setText("hahfasdf    "+ mTeacher.name );
    }
}
