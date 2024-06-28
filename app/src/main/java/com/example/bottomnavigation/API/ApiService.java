package com.example.bottomnavigation.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("users")
    Call<RegisterResponse> registerUser(
            @Header("x-access-token") String token,
            @Body RegisterRequest registerRequest
    );

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
