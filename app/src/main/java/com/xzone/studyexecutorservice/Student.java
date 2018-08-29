package com.xzone.studyexecutorservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xl on 2018/6/5.
 */

public class Student implements Parcelable {
    int age;
    String name;

    protected Student(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }


    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);

    }

}
