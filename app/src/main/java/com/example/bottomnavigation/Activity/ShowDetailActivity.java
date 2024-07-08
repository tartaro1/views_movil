package com.example.bottomnavigation.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.API.Product;
import com.example.bottomnavigation.Helper.ManagementCart;
import com.example.bottomnavigation.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, priceTxt, descriptionTxt, numberOrderTxt, brandTxt, categoryTxt, providerTxt, stockTxt;
    private ImageView plusBtn, minusBtn, productPic;
    private RatingBar ratingBar;
    private Product object;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (Product) getIntent().getSerializableExtra("object");

        if (object == null) {
            Toast.makeText(this, "Error: No se pudo cargar el producto", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Glide.with(this)
                .load(object.getImagen())
                .into(productPic);

        titleTxt.setText(object.getNombreProducto());
        priceTxt.setText("$" + object.getPrecioVenta());
        descriptionTxt.setText(object.getDescripcion());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        brandTxt.setText("Marca: " + object.getMarca());
        categoryTxt.setText("Categoría: " + object.getID_Categoria());
        providerTxt.setText("Proveedor: " + object.getID_Proveedor());
        stockTxt.setText("Stock: " + object.getStock());
        ratingBar.setRating(object.getCalificacion());

        plusBtn.setOnClickListener(v -> {
            numberOrder++;
            numberOrderTxt.setText(String.valueOf(numberOrder));
        });

        minusBtn.setOnClickListener(v -> {
            if (numberOrder > 1) {
                numberOrder--;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(v -> {
            object.setNumberInCart(numberOrder);
            managementCart.insertProduct(object);
            Toast.makeText(this, "Añadido al carrito", Toast.LENGTH_SHORT).show();
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        priceTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        productPic = findViewById(R.id.productPic);
        brandTxt = findViewById(R.id.brandTxt);
        categoryTxt = findViewById(R.id.categoryTxt);
        providerTxt = findViewById(R.id.providerTxt);
        stockTxt = findViewById(R.id.stockTxt);
        ratingBar = findViewById(R.id.ratingBar);
    }
}