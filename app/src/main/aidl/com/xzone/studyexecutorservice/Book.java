package com.xzone.studyexecutorservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xl on 2018/6/12.
 */

public class Book implements Parcelable{
    String name;
    double prize;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", prize=" + prize +
                '}';
    }

    Book (String name,Double prize){
        this.name = name;
        this.prize = prize;
    }
    protected Book(Parcel in) {
        name = in.readString();
        prize = in.readDouble();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(prize);
    }


}
