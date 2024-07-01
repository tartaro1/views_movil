package com.example.bottomnavigation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnavigation.API.ApiService;
import com.example.bottomnavigation.Adapter.OrderAdapter;
import com.example.bottomnavigation.Domain.OrderDomain;
import com.example.bottomnavigation.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
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
    private ListView listView;
    private OrderAdapter adapter;
    private List<OrderDomain> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        listView = findViewById(R.id.listview);
        orderList = new ArrayList<>();
        adapter = new OrderAdapter(this, orderList);
        listView.setAdapter(adapter);

        fetchOrders();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            OrderDomain selectedOrder = orderList.get(position);
            showOrderDetails(selectedOrder);
        });
    }

    private void fetchOrders() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ms-backend-tartaro.onrender.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<OrderDomain>> call = apiService.getOrders();

        call.enqueue(new Callback<List<OrderDomain>>() {
            @Override
            public void onResponse(Call<List<OrderDomain>> call, Response<List<OrderDomain>> response) {
                Log.d("API", "Código de respuesta: " + response.code());
                try {
                    Log.d("API", "Cuerpo de la respuesta: " + response.errorBody().string());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (response.code() == 404) {
                    // Procesar el cuerpo de la respuesta incluso si es 404
                    try {
                        String jsonResponse = response.errorBody().string();
                        List<OrderDomain> orders = new Gson().fromJson(jsonResponse, new TypeToken<List<OrderDomain>>(){}.getType());
                        orderList.clear();
                        orderList.addAll(orders);
                        adapter.notifyDataSetChanged();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(DeliveryActivity.this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                    }
                } else if (response.isSuccessful() && response.body() != null) {
                    orderList.clear();
                    orderList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(DeliveryActivity.this, "Error en la respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
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