package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by xl on 2018/7/10.
 */

public class StudyNotify extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_study);
        findViewById(R.id.btn_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
normalNotificaion();
            }
        });
    }

    private void normalNotificaion() {
        Notification.Builder builder = new Notification.Builder(StudyNotify.this);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intent}, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_arrow_drop_down_white_24dp);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow_drop_down_white_24dp));
        builder.setAutoCancel(true);
        builder.setContentTitle("普通通知");
builder.build();
    }
}
