package com.example.bottomnavigation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.API.ApiService;
import com.example.bottomnavigation.Adapter.OrderAdapter;
import com.example.bottomnavigation.Domain.OrderDomain;
import com.example.bottomnavigation.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeliveryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private List<OrderDomain> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderList = new ArrayList<>();
        adapter = new OrderAdapter(this, orderList, this::showOrderDetails);
        recyclerView.setAdapter(adapter);

        fetchOrders();
    }

    private void fetchOrders() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ms-backend-tartaro.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<OrderDomain>> call = apiService.getOrders();

        call.enqueue(new Callback<List<OrderDomain>>() {
            @Override
            public void onResponse(Call<List<OrderDomain>> call, Response<List<OrderDomain>> response) {
                if (response.isSuccessful()) {
                    List<OrderDomain> orders = response.body();
                    if (orders != null) {
                        orderList.clear();
                        orderList.addAll(orders);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(DeliveryActivity.this, "Pedidos cargados con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DeliveryActivity.this, "No se recibieron pedidos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DeliveryActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OrderDomain>> call, Throwable t) {
                Toast.makeText(DeliveryActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showOrderDetails(OrderDomain order) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("order", order); // Asegúrate de que Order implemente Parcelable
        startActivity(intent);
    }
}