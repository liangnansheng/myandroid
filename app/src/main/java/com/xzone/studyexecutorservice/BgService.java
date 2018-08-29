package com.xzone.studyexecutorservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by xl on 2018/6/14.
 */

public class BgService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *

     * @param name Used to name the worker thread, important only for debugging.
     */
    public BgService(String name
    ) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
