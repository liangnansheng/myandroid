package com.xzone.studyexecutorservice.databindingstudy;

import android.util.Log;
import android.view.View;

import com.xzone.studyexecutorservice.User;

/**
 * Created by xl on 2018/6/27.
 */

public class MyHandlers {
    User user;
    public MyHandlers(User user) {
        this.user = user;
    }

    public void onClickFriend(View v){
        Log.i("fjsdfljaslf","abdf");
        user.setFirstName("Yang12311312");

    }
}
