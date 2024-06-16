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
import com.example.bottomnavigation.Domain.FoodDomain;
import com.example.bottomnavigation.Helper.ManagementCart;
import com.example.bottomnavigation.Interface.ChangeNumberitemsListener;
import com.example.bottomnavigation.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    ArrayList<FoodDomain> listFooSelected;
    private ManagementCart managementCart;
    ChangeNumberitemsListener changerNumberitemListener;

    public CartListAdapter(ArrayList<FoodDomain> listFooSelectd, Context context, ChangeNumberitemsListener changerNumberitemListener){
        this.listFooSelected = listFooSelectd;
        managementCart = new ManagementCart(context);
        this.changerNumberitemListener = changerNumberitemListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public  void  onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.title.setText(listFooSelected.get(position).getTitle());
        holder.precioUnidadItem.setText("$"+ listFooSelected.get(position).getFee());
        holder.totalEachItem.setText("$"+ Math.round((listFooSelected.get(position).getNumberInCart()*listFooSelected.get(position).getFee())));
        holder.num.setText(String.valueOf(listFooSelected.get(position).getNumberInCart()));


        int drawableReourceId = holder.itemView.getContext().getResources()
                .getIdentifier(listFooSelected.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).
                load(drawableReourceId).
                into(holder.imagen);

        holder.plusItem.setOnClickListener(v -> {
            managementCart.plusNumberFood(listFooSelected, position, () -> {
                notifyDataSetChanged();
                changerNumberitemListener.changed();
            });
        });

        holder.minusItem.setOnClickListener(v -> managementCart.miplusNumberFood(listFooSelected, position, () -> {
            notifyDataSetChanged();
            changerNumberitemListener.changed();
        }));
    }

    @Override
    public int getItemCount() { return listFooSelected.size();}

    // --Commented out by Inspection (4/06/24, 8:50 PM):public abstract void changerd();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, precioUnidadItem;
        ImageView imagen, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt); // Cambiar a titleTxt si ese es el ID en el diseño
            imagen = itemView.findViewById(R.id.picCart); // Cambiar a picCart si ese es el ID en el diseño
            precioUnidadItem = itemView.findViewById(R.id.feeEachTxt); // Cambiar a feeEachTxt si ese es el ID en el diseño
            totalEachItem = itemView.findViewById(R.id.totalEachTxt); // Cambiar a totalEachTxt si ese es el ID en el diseño
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
            num = itemView.findViewById(R.id.numberItemTxT);
        }
    }
}
