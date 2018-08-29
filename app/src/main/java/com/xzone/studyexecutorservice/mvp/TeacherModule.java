package com.xzone.studyexecutorservice.mvp;

import com.xzone.studyexecutorservice.Student;
import com.xzone.studyexecutorservice.Teacher;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xl on 2018/8/7.
 */

@Module
public class TeacherModule {
    Teacher teacher;
    public TeacherModule( ) {
        teacher = new Teacher("a",10,new Student(10,"asdf"));

    }
    @Provides
    Teacher getTeacher() {
        return teacher;
    }
}
