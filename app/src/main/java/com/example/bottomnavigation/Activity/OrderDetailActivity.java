package com.example.bottomnavigation.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnavigation.Domain.OrderDomain;
import com.example.bottomnavigation.R;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

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
    }
}