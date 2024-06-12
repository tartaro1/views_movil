package com.example.bottomnavigation.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.Domain.DescubreDomain;
import com.example.bottomnavigation.R;

import java.util.ArrayList;

public class DescubreAdapter extends RecyclerView.Adapter<DescubreAdapter.ViewHolder> {

    ArrayList<DescubreDomain> descubreDomain;

    public DescubreAdapter(ArrayList<DescubreDomain> descubreDomain){
        this.descubreDomain = descubreDomain;
    }
    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_descubre,parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public  void  onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.descubreName.setText(descubreDomain.get(position).getDataTitle());
        String picUrl="";
        switch (position) {
            case 0: {
                picUrl = "img_3";
                break;
            }
            case 1: {
                picUrl = "img_4";
                break;
            }
            case 2: {
                picUrl = "img_5";
                break;
            }
            case 3: {
                picUrl = "cat_4";
                break;
            }
            case 4: {
                picUrl = "cat_5";
                break;
            }
        }

        int drawableReourceId=holder.itemView.getContext().getResources()
                .getIdentifier(picUrl,"drawable",
                        holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableReourceId).into(holder.descubreImg);

    }

    @Override
    public int getItemCount() { return descubreDomain.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView descubreName;
        ImageView descubreImg;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descubreName = itemView.findViewById(R.id.DesTitle);
            descubreImg = itemView.findViewById(R.id.DesImg);
            mainLayout = itemView.findViewById(R.id.mainLayout1);
        }
    }
}
