package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

/**
 * Created by xl on 2018/5/31.
 */

public class B extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b);
        Log.i("test","onCreate  B"+"  taskId "+getTaskId());

    }
    public void onClick(View v){
        startActivity(new Intent(B.this,C.class));
    }
}
