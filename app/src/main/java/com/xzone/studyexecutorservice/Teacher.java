package com.xzone.studyexecutorservice;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * Created by xl on 2018/6/5.
 */

public class Teacher implements Serializable {
    String name;
    int age;
    Student stu;

    @Inject
    public Teacher(String name, int age, Student student) {
        this.name = name;
        this.age = age;
        stu = student;
    }
}
