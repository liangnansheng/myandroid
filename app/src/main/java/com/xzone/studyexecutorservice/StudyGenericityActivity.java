package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xl on 2018/8/24.
 */

public class StudyGenericityActivity extends Activity {
    public static final String TAG = "泛型测试";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_study_genericity);
        List<String> listString = new ArrayList<String>();
        listString.add("aaa");
        for (int i = 0; i < listString.size(); i++) {
            String o = (String) listString.get(i);
            Log.i("泛型测试", o.toString());
        }
        List<Integer> listInteger = new ArrayList<Integer>();

        Class classStringList = listString.getClass();
        Class classIntegerList = listInteger.getClass();

        if (classIntegerList.equals(classStringList)) {
            Log.i(TAG, "类型相同");
        }
        Generic<String> strGeneric = new Generic("asdfasd");
        Generic<Integer> intGeneric = new Generic<Integer>(5);

        showKeyValue1(intGeneric);

        try {
            String s = genericMethod(String.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        printMsg(1, 2, 3);
    }

    class Generic<T> {
        private T key;

        public Generic(T name)

        {
            key = name;
        }

        public T getKey() {
            return key;
        }
    }

    interface GenericInterface<T> {
        public T get();
    }

    class GenericImpl<T> implements GenericInterface<T> {

        @Override
        public T get() {
            return null;
        }


    }

    class GenericImplString implements GenericInterface<String> {

        @Override
        public String get() {
            return null;
        }
    }

    public void showKeyValue1(Generic<? extends Number> obj) {
        Log.d("泛型测试", "key value is " + obj.getKey());
    }

    public <T> T genericMethod(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T t = tClass.newInstance();

        return t;
    }

    public <T> void printMsg(T... args) {
        for (T arg : args) {
            Log.i(TAG, "printMsg" + arg);
        }
    }
}
