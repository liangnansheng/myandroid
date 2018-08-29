package com.xzone.studyexecutorservice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xl on 2018/8/8.
 */

public class StudyReflectAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Class<?> user = Class.forName("com.xzone.studyexecutorservice.User");
            Method[] methods = user.getMethods();
            Method[] declareMethods = user.getDeclaredMethods();
//            Method method = user.getMethod()
            Method b = user.getDeclaredMethod("go");
            b.setAccessible(true);
            User user1 = new User();
            Class<? extends User> aClass = user1.getClass();
            Log.i("EQUA", (aClass == user) + "");
            for (Method m : methods) {
                Log.i("methods", m.getName());
            }
            for (Method m : declareMethods) {
                Log.i("declareMethods", m.getName());
            }
            Constructor<?> constructor = user.getDeclaredConstructor();
            Constructor<?> constructor2 = user.getDeclaredConstructor(String.class, String.class);

            Constructor<?> constructor3 = user.getDeclaredConstructor(int.class);
            constructor3.setAccessible(true);
            Log.i("Constructor", (constructor == constructor2) + "");
            Object o = constructor.newInstance();
            Object o1 = constructor2.newInstance("1", "2");
            Object o2 = constructor3.newInstance(3);
            Log.i("asfasdfa", (o instanceof User) + "");
            Constructor<?>[] constructors = user.getConstructors();
            Constructor<?>[] declaredConstructors = user.getDeclaredConstructors();
            for (Constructor to : constructors) {
                Log.i("constructors.length", "name:" + to.getName() + "   " + constructors.length + "  ");
                Class[] parameterTypes = to.getParameterTypes();
                for(Class  name :parameterTypes){
                    Log.i("parameterType",name.getName());
                }
            }

            for (Constructor to : declaredConstructors) {
                Log.e("constructors.length", "name:" + to.getName() + "   " + declaredConstructors.length + "  ");
                Class[] parameterTypes = to.getParameterTypes();
                for(Class  name :parameterTypes){
                    Log.e("parameterType",name.getName());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.i("dsfas", e.toString());

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
