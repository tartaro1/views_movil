package com.example.bottomnavigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.API.Product;
import com.example.bottomnavigation.Helper.ManagementCart;
import com.example.bottomnavigation.Interface.ChangeNumberItemsListener;
import com.example.bottomnavigation.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private ArrayList<Product> listFooSelected;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changerNumberitemListener;

    public CartListAdapter(ArrayList<Product> listFooSelected, Context context, ChangeNumberItemsListener changerNumberitemListener) {
        this.listFooSelected = listFooSelected;
        this.managementCart = new ManagementCart(context);
        this.changerNumberitemListener = changerNumberitemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = listFooSelected.get(position);
        holder.title.setText(product.getNombreProducto());
        holder.precioUnidadItem.setText("$" + product.getPrecioVenta());

        double totalPrice = product.getNumberInCart() * Double.parseDouble(product.getPrecioVenta());
        holder.totalEachItem.setText("$" + String.format("%.2f", totalPrice));

        holder.num.setText(String.valueOf(product.getNumberInCart()));

        Glide.with(holder.itemView.getContext())
                .load(product.getImagen())
                .into(holder.imagen);

        holder.plusItem.setOnClickListener(v -> {
            managementCart.plusNumberProduct(listFooSelected, position, () -> {
                notifyDataSetChanged();
                changerNumberitemListener.changed();
            });
        });

        holder.minusItem.setOnClickListener(v -> {
            managementCart.minusNumberProduct(listFooSelected, position, () -> {
                notifyDataSetChanged();
                changerNumberitemListener.changed();
            });
        });
    }

    @Override
    public int getItemCount() {
        return listFooSelected.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, precioUnidadItem, totalEachItem, num;
        ImageView imagen, plusItem, minusItem;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            imagen = itemView.findViewById(R.id.picCart);
            precioUnidadItem = itemView.findViewById(R.id.feeEachTxt);
            totalEachItem = itemView.findViewById(R.id.totalEachTxt);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
            num = itemView.findViewById(R.id.numberItemTxT);
        }
    }
}