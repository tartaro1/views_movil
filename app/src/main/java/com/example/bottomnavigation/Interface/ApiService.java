package com.example.bottomnavigation.Interface;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("register")
    Call<Void> registerUser(@Body User user);

    @POST("login")
    Call<Void> loginUser(@Body User user);
}
