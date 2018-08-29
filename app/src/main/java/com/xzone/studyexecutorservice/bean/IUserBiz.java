package com.xzone.studyexecutorservice.bean;

import com.xzone.studyexecutorservice.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xl on 2018/8/20.
 */

public interface IUserBiz {
    @GET("{name}")
    Call<User> getUser(@Path("name") String name);

}
