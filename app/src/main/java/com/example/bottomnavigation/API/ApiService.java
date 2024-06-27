package com.example.bottomnavigation.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
}
