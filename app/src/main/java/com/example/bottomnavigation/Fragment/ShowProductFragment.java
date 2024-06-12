package com.example.bottomnavigation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.Domain.FoodDomain;
import com.example.bottomnavigation.Helper.ManagementCart;
import com.example.bottomnavigation.R;

public class ShowProductFragment extends Fragment {

    private TextView addToCartBtn;
    private TextView TitleTxT, feeTxT, descripcionTxT, numberOrderTxT, totalPriceTxT, starTxT, caloryTxT, timeTxT;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_product, container, false);

        managementCart = new ManagementCart(requireContext());

        initView(view);
        getBundle();

        return view;
    }

    private void getBundle() {
        if (getArguments() != null) {
            object = (FoodDomain) getArguments().getSerializable("object");

            int drawableResourceId = requireContext().getResources().getIdentifier(object.getPic(), "drawable", requireContext().getPackageName());
            Glide.with(this)
                    .load(drawableResourceId)
                    .into(picFood);

            TitleTxT.setText(object.getTitle());
            feeTxT.setText("$" + object.getFee());
            descripcionTxT.setText(object.getDescripcion());
            numberOrderTxT.setText(String.valueOf(numberOrder));
            caloryTxT.setText(object.getCalories()+"calorias");
            starTxT.setText(object.getStar() + "");
            timeTxT.setText(object.getTime()+"minutos");
            totalPriceTxT.setText("$" + Math.round(numberOrder * object.getFee()));
        }

        // Configurar los OnClickListeners para los botones
        // ...
    }

    private void initView(View view) {
        addToCartBtn = view.findViewById(R.id.addToCartBtn);
        TitleTxT = view.findViewById(R.id.titleTxT);
        feeTxT = view.findViewById(R.id.priceTxT);
        descripcionTxT = view.findViewById(R.id.descriptionTxT);
        numberOrderTxT = view.findViewById(R.id.numberItemTxT);
        plusBtn = view.findViewById(R.id.plusCardBtn);
        minusBtn = view.findViewById(R.id.minusCardBtn);
        picFood = view.findViewById(R.id.foodPic);
        totalPriceTxT = view.findViewById(R.id.totalPriceTxT);
        starTxT = view.findViewById(R.id.starTxT);
        caloryTxT = view.findViewById(R.id.VicaloriesTxt);
        timeTxT = view.findViewById(R.id.timeTxT);
    }
}