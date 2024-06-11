package com.example.bottomnavigation.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        managementCart = new ManagementCart(this);

        initView();
        getBundle();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_product, container, false);
    }

    private void getBundle() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", foodDomainObject);
        ShowProductFragment fragment = new ShowProductFragment();
        fragment.setArguments(bundle);

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
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

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxT.setText(String.valueOf(numberOrder));
                totalPriceTxT.setText("$" + Math.round(numberOrder * object.getFee()));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxT.setText(String.valueOf(numberOrder));
                totalPriceTxT.setText("$" + Math.round(numberOrder * object.getFee()));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        TitleTxT = findViewById(R.id.titleTxT);
        feeTxT = findViewById(R.id.priceTxT);
        descripcionTxT = findViewById(R.id.descriptionTxT);
        numberOrderTxT = findViewById(R.id.numberItemTxT);
        plusBtn = findViewById(R.id.plusCardBtn);
        minusBtn = findViewById(R.id.minusCardBtn);
        picFood = findViewById(R.id.foodPic);
        totalPriceTxT = findViewById(R.id.totalPriceTxT);
        starTxT = findViewById(R.id.starTxT);
        caloryTxT = findViewById(R.id.VicaloriesTxt);
        timeTxT = findViewById(R.id.timeTxT);
    }
}