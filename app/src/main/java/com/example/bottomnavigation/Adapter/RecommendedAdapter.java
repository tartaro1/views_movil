package com.example.bottomnavigation.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.Domain.FoodDomain;
import com.example.bottomnavigation.Fragment.ShowProductFragment;
import com.example.bottomnavigation.R;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {

    ArrayList<FoodDomain> RecommendedDomains;

    public RecommendedAdapter(ArrayList<FoodDomain> recommendedDomains){
        this.RecommendedDomains = recommendedDomains;
    }
    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recomendados,parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public  void  onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.title.setText(RecommendedDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(RecommendedDomains.get(position).getFee()));


        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(RecommendedDomains.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).
                load(drawableResourceId).
                into(holder.pic);

        holder.addBtn.setOnClickListener(v -> {
            FoodDomain foodDomain = RecommendedDomains.get(position);

            // 1. Crear una instancia del nuevo fragmento
            ShowProductFragment fragment = new ShowProductFragment();

            // 2. Agregar los argumentos necesarios al nuevo fragmento
            Bundle bundle = new Bundle();
            bundle.putSerializable("object", foodDomain);
            fragment.setArguments(bundle);

            // 3. Realizar una transacción de fragmento para agregar el nuevo fragmento al contenedor de fragmentos
            FragmentManager fragmentManager = ((FragmentActivity) holder.itemView.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

    @Override
    public int getItemCount() { return RecommendedDomains.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,fee;
        ImageView pic,addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Rectitle);
            pic = itemView.findViewById(R.id.Recomeimg);
            fee = itemView.findViewById(R.id.RecomePrecio);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
