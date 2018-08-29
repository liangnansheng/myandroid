package com.xzone.studyexecutorservice.mvp;

import com.xzone.studyexecutorservice.StudyDragger2Act;

import dagger.Component;

/**
 * Created by xl on 2018/8/7.
 */
@Component(modules = {StudyDragger2Module.class, TeacherModule.class})
public interface StudyDraggerComponent {
    void inject(StudyDragger2Act activity);
}
