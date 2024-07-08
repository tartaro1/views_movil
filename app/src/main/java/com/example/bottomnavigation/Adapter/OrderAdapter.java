package com.example.bottomnavigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.Domain.OrderDomain;
import com.example.bottomnavigation.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context context;
    private List<OrderDomain> orders;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(OrderDomain order);
    }

    public OrderAdapter(Context context, List<OrderDomain> orders, OnItemClickListener listener) {
        this.context = context;
        this.orders = orders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderDomain currentOrder = orders.get(position);
        holder.bind(currentOrder, listener);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView idPedido;
        private TextView estadoPedido;
        private TextView direccion;
        private TextView precioVenta;
        private TextView fechaPedido;
        private TextView nombreCliente;
        private TextView idRepartidor;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            idPedido = itemView.findViewById(R.id.idPedido);
            estadoPedido = itemView.findViewById(R.id.estadoPedido);
            direccion = itemView.findViewById(R.id.direccion);
            precioVenta = itemView.findViewById(R.id.precioVenta);
            fechaPedido = itemView.findViewById(R.id.fechaPedido);
            nombreCliente = itemView.findViewById(R.id.nombreCliente);
            idRepartidor = itemView.findViewById(R.id.idRepartidor);
        }

        public void bind(final OrderDomain order, final OnItemClickListener listener) {
            idPedido.setText("Pedido #" + order.getID_Pedido());
            estadoPedido.setText(order.getEstadoPedido());
            direccion.setText(order.getDireccion());
            precioVenta.setText("Precio: $" + order.getPrecioVenta());
            fechaPedido.setText("Fecha: " + order.getFechaPedido());
            nombreCliente.setText("Cliente: " + order.getNombre_Cliente());
            idRepartidor.setText("Repartidor: " + order.getID_Repartidor());

            itemView.setOnClickListener(v -> listener.onItemClick(order));
        }
    }
}
