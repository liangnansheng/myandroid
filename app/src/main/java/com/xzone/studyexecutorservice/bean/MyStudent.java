package com.xzone.studyexecutorservice.bean;

import com.xzone.studyexecutorservice.CheckStore;
import com.xzone.studyexecutorservice.utils.TargetA;

/**
 * Created by xl on 2018/8/3.
 */

public class MyStudent {
    @CheckStore("{}")
    private int score;
    int id;
    String name;

    public MyStudent(int score, int id, String name) {
        this.score = score;
        this.id = id;
        this.name = name;
    }

    @TargetA
   public void  gofkjasdl(){

    }
}
