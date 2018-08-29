package com.xzone.studyexecutorservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;
import com.xzone.studyexecutorservice.bean.MyStudent;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {
    int i = 0;

    private void getWidthHeight() {
        int measuredState = tvHello.getMeasuredState();
        Log.i("getWidthHeight", "width=" + measuredState + "View.MeasureSpec.AT_MOST " + View.MeasureSpec.AT_MOST + "View.MeasureSpec.EXACTLY " + View.MeasureSpec.EXACTLY);

        int widthMeasuerSpec = View.MeasureSpec.makeMeasureSpec(2300, View.MeasureSpec.UNSPECIFIED);
        int heightMeasuerSpec = View.MeasureSpec.makeMeasureSpec(2300, View.MeasureSpec.UNSPECIFIED);
        tvHello.measure(widthMeasuerSpec, heightMeasuerSpec);
        int width = tvHello.getWidth();
        int height = tvHello.getHeight();
        Log.i("getWidthHeight", "width=" + width);
        Log.i("getWidthHeight", "height=" + height);
        int measuerWidth = tvHello.getMeasuredWidth();
        int measureHeight = tvHello.getMeasuredHeight();
        Log.i("getWidthHeight", "measuerWidth=" + measuerWidth);
        Log.i("getWidthHeight", "measureHeight=" + measureHeight);
    }

    private static final String TAG = "MainActivity";
    // 在多线程间共享的对象上使用wait
    private String[] shareObj = {"true"};
    TextView tv;
    TextView tvHello;
    Button btna;
    Button btnb;
    Button btnc;
    View viewSoftRefrence;
    Button btnd, btnGetMeasureWidth, btngetWidth, btnParcelable;
    MyPointView mpv;
    MyService.MyBinder binder;
    LocalService.LocalBinder localBinder;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("MyService", "onServiceConnected   " + name);
//            binder = (MyService.MyBinder) service;
            localBinder = (LocalService.LocalBinder) service;

            localBinder.getService().setmPassData(new LocalService.PassData() {
                @Override
                public void onPassData(int num) {
                    Log.i("abdd", num + "  T" + Thread.currentThread().getName());
                    Message obtain = Message.obtain();
                    obtain.what = num;
                    handler.sendMessage(obtain);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MyService", "onServiceDisconnected   " + name);
            localBinder = null;
        }

    };
    Intent intent;
    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.btnGetMeasureWidth:
                    Toast.makeText(MainActivity.this, tvHello.getMeasuredWidth() + "", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnGetWidth:
                    Toast.makeText(MainActivity.this, tvHello.getWidth() + "", Toast.LENGTH_SHORT).show();

                    break;
            }
        }
    };

    private boolean isBind;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        try {
            URL url = new URL("A");
            FileDownloader.getImpl().create("a").start();
            BaseDownloadTask dfsas = FileDownloader.getImpl().create("dfsas");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {

        }
    }

    class MyRunnablea implements Runnable {

        @Override
        public void run() {

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExecutorService executorService = Executors.newCachedThreadPool();
        findViewById(R.id.btn_study_glide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GlideAct.class));
            }
        });
        findViewById(R.id.btn_study_reflection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyReflectAct.class));
            }
        });
        findViewById(R.id.btn_study_canvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyCanvasActivity.class));
            }
        });
        findViewById(R.id.btn_study_X).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyGenericityActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_goto_learn_okhttp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyOkHttp.class));
            }
        });
        findViewById(R.id.tv_to_dragger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyDragger2Act.class));
            }
        });
        MyStudent myStudent = new MyStudent(101, 0, "a");
        Class<? extends MyStudent> aClass1 = myStudent.getClass();

        try {
            Field score = aClass1.getDeclaredField("score");
            score.setAccessible(true);
            int o = (int) score.get(myStudent);
            CheckStore annotation = score.getAnnotation(CheckStore.class);
            int high = annotation.high();
            int low = annotation.low();
            String warn = annotation.warn();
            if (o > high || o < low) {
                Log.i("error", "true" + warn);
            } else {
                Log.i("error", "false");
            }
        } catch (NoSuchFieldException e) {
            Log.i("asfs", e.toString());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.i("asfs", e.toString());
            e.printStackTrace();
        }
        User a = new User() {

        };

        User b = new User("1231", "231");
        MyRunnable myRunnable = new MyRunnable();
        MyRunnablea myRunnablea = new MyRunnablea();
        Log.i("adfasd", myRunnable.getClass().getName() + "     " + myRunnablea.getClass().getName());
        TypeToken<Integer> stringTypeToken = new TypeToken<Integer>() {
        };
        try {
            Class<?> aClass = Class.forName("com.xzone.studyexecutorservice.A");
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                Log.i("sdf", method + "");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        findViewById(R.id.btn_nofication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyNotify.class));
            }
        });

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, TestLeafAct.class));
            }
        });
        intent = new Intent(MainActivity.this, LocalService.class);
        viewSoftRefrence = findViewById(R.id.softrefrence);
        findViewById(R.id.btn_goto_study_okhttp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OkHttpAcitivty.class));
            }
        });
        findViewById(R.id.btn_goto_study_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyWindowAct.class));
            }
        });
        viewSoftRefrence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studySoftRefrence();
            }

            private void studySoftRefrence() {
                String a = "goto";
                SoftReference<String> stringSoftReference = new SoftReference<String>(a);
                String s = stringSoftReference.get();
                Log.i("test", s);
            }

        });
        tvHello = findViewById(R.id.tv_hello);
        btnParcelable = findViewById(R.id.parcelable);
        btnParcelable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                Student student = new Student(18, "zhangsanfeng");
                intent.putExtra("object", student);
                startActivity(intent);
            }
        });
        tv = findViewById(R.id.tv);
        btna = findViewById(R.id.btna);
        btnb = findViewById(R.id.btnb);
        btnc = findViewById(R.id.btnc);
        btnGetMeasureWidth = findViewById(R.id.btnGetMeasureWidth);
        btngetWidth = findViewById(R.id.btnGetWidth);
        btnGetMeasureWidth.setOnClickListener(listener);
        btngetWidth.setOnClickListener(listener);
//        BitmapFactory.decodestr
//        Basef
        tvHello.post(new Runnable() {
            @Override
            public void run() {

                getWidthHeight();

            }
        });
//        final ViewTreeObserver viewTreeObserver = btnGetMeasureWidth.getViewTreeObserver();
//        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                getWidthHeight();
//                Log.i("dfasd", "" + viewTreeObserver + " \n" + tvHello.getViewTreeObserver());
////                tvHello.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBind) {
                    unbindService(serviceConnection);
                    isBind = false;
                }
            }
        });
        btnd = findViewById(R.id.btnd);
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startService(intent);
                isBind = true;
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.btn_goto_study_bind).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyBinderActivity.class));
            }
        });
        mpv = findViewById(R.id.mpv);
        mpv.post(new Runnable() {
            @Override
            public void run() {
                Log.i("2131312123dasfdas", mpv.getMeasuredWidth() + "    " + mpv.getMeasuredHeight());
                int w = View.MeasureSpec.makeMeasureSpec(10000, View.MeasureSpec.EXACTLY);
                int h = View.MeasureSpec.makeMeasureSpec(999, View.MeasureSpec.EXACTLY);

                mpv.measure(w, h);
                Log.i("2131312123dasfdas", mpv.getMeasuredWidth() + "    " + mpv.getMeasuredHeight());

            }
        });
        new Handler().post(new Runnable() {
            @Override
            public void run() {
            }
        });
        tvHello.post(new Runnable() {
            @Override
            public void run() {
                getWidthHeight();

            }
        });

        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
//                intent.setType("video/*"); //选择视频 （mp4 3gp 是android支持的视频格式）
//                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setClass(MainActivity.this, A.class);
                startActivityForResult(intent, 1);


            }
        });


        ThreadWait threadWait1 =  new ThreadWait("wait thread1");
        ThreadWait threadWait2 =  new ThreadWait("wait thread2");
        ThreadWait threadWait3 =  new ThreadWait("wait thread3");
        ThreadNotify threadNotify = new ThreadNotify("notify thread");
        threadNotify.start();
        threadWait1.start();
        threadWait2.start();
        threadWait3.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    class ThreadWait extends Thread {

        public ThreadWait(String name) {
            super(name);
        }

        public void run() {
            synchronized (shareObj) {
                while ("true".equals(shareObj[0])) {
                    Log.i("gotoseeethread", "线程" + this.getName() + "开始等待");
                    long startTime = System.currentTimeMillis();
                    try {
                        shareObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long endTime = System.currentTimeMillis();

                    Log.i("gotoseeethread", "线程" + this.getName()
                            + "等待时间为：" + (endTime - startTime));

                }
            }
            Log.i("gotoseeethread", "线程" + getName() + "等待结束");
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvHello.setText(msg.what + "");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind) {
            unbindService(serviceConnection);
            isBind = false;
        }
    }

    class ThreadNotify extends Thread {

        public ThreadNotify(String name) {
            super(name);
        }


        public void run() {
            try {
                // 给等待线程等待时间
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (shareObj) {
                Log.i("gotoseeethread", "线程" + this.getName() + "开始准备通知");

                shareObj[0] = "false";
                shareObj.notifyAll();
                Log.i("gotoseeethread", "线程" + this.getName() + "通知结束");

            }
            Log.i("gotoseeethread", "线程" + this.getName() + "运行结束");

        }
    }
}
