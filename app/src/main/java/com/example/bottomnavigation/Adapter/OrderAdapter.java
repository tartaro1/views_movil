package com.example.bottomnavigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bottomnavigation.Domain.OrderDomain;
import com.example.bottomnavigation.R;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<OrderDomain> {
    private Context context;
    private List<OrderDomain> orders;

    public OrderAdapter(Context context, List<OrderDomain> orders) {
        super(context, 0, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        OrderDomain currentOrder = orders.get(position);

        TextView idPedido = listItem.findViewById(R.id.idPedido);
        TextView estadoPedido = listItem.findViewById(R.id.estadoPedido);
        TextView nombreCliente = listItem.findViewById(R.id.nombreCliente);

        idPedido.setText("Pedido #" + currentOrder.getID_Pedido());
        estadoPedido.setText(currentOrder.getEstadoPedido());
        nombreCliente.setText(currentOrder.getNombre_Cliente());

        return listItem;
    }
}
