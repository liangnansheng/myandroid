package com.xzone.studyexecutorservice;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by xl on 2018/6/26.
 */

public class User extends BaseObservable {
    public String firstName;
    public String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected User() {
        firstName = "a";

    }
private User(int a){

}
    @Bindable
    public String getFirstName() {
        return this.firstName;
    }

    @Bindable
    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    private void go(){

    }

}
