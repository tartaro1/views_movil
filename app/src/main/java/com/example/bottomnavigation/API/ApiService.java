package com.example.bottomnavigation.API;

import com.example.bottomnavigation.Domain.OrderDomain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("orders/")
    Call<List<OrderDomain>> getOrders();

    @Headers("Content-Type: application/json")
    @GET("products")
    Call<List<Product>> getProducts();

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
