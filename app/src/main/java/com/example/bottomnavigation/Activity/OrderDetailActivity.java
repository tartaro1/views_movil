package com.example.bottomnavigation.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnavigation.API.ApiService;
import com.example.bottomnavigation.API.OrderStateUpdate;
import com.example.bottomnavigation.Domain.OrderDomain;
import com.example.bottomnavigation.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderDetailActivity extends AppCompatActivity {

    private OrderDomain order;
    private ApiService apiService;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        order = getIntent().getParcelableExtra("order");
        OrderDomain order = getIntent().getParcelableExtra("order");

        if (order != null) {
            TextView idPedido = findViewById(R.id.idPedido);
            TextView estadoPedido = findViewById(R.id.estadoPedido);
            TextView direccion = findViewById(R.id.direccion);
            TextView precioVenta = findViewById(R.id.precioVenta);
            TextView fechaPedido = findViewById(R.id.fechaPedido);
            TextView nombreCliente = findViewById(R.id.nombreCliente);
            TextView idRepartidor = findViewById(R.id.idRepartidor);

            idPedido.setText("Pedido #" + order.getID_Pedido());
            estadoPedido.setText("Estado: " + order.getEstadoPedido());
            direccion.setText("Dirección: " + order.getDireccion());
            precioVenta.setText("Precio: $" + order.getPrecioVenta());
            fechaPedido.setText("Fecha: " + order.getFechaPedido());
            nombreCliente.setText("Cliente: " + order.getNombre_Cliente());
            idRepartidor.setText("Repartidor: " + order.getID_Repartidor());
        }

        if (order != null) {
            // ... (tu código existente para mostrar los detalles del pedido)

            Button updateButton = findViewById(R.id.updateButton);
            updateButton.setOnClickListener(v -> showBottomSheet());
        }

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ms-backend-tartaro.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        // Obtener el token de las SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        token = prefs.getString("token", "");
    }

    private void showBottomSheet() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_update_state, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bottomSheetView);

        TextInputEditText editTextEstado = bottomSheetView.findViewById(R.id.edit_text_estado);
        Button sendButton = bottomSheetView.findViewById(R.id.outlinedButton);

        sendButton.setOnClickListener(v -> {
            String newState = editTextEstado.getText().toString();
            if (!newState.isEmpty()) {
                updateOrderState(newState);
                bottomSheetDialog.dismiss();
            } else {
                Toast.makeText(this, "Por favor, ingrese un nuevo estado", Toast.LENGTH_SHORT).show();
            }
        });

        bottomSheetDialog.show();
    }

    private void updateOrderState(String newState) {
        OrderStateUpdate stateUpdate = new OrderStateUpdate(newState);

        Call<OrderDomain> call = apiService.updateOrderState("Bearer " + token, order.getID_Pedido(), stateUpdate);
        call.enqueue(new Callback<OrderDomain>() {
            @Override
            public void onResponse(Call<OrderDomain> call, Response<OrderDomain> response) {
                if (response.isSuccessful()) {
                    order = response.body();
                    updateUI();
                    Toast.makeText(OrderDetailActivity.this, "Estado actualizado con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OrderDetailActivity.this, "Error al actualizar el estado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderDomain> call, Throwable t) {
                Toast.makeText(OrderDetailActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        // Actualizar la UI con los nuevos datos del pedido
        TextView estadoPedido = findViewById(R.id.estadoPedido);
        estadoPedido.setText("Estado: " + order.getEstadoPedido());
    }
}