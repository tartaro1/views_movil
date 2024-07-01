package com.example.bottomnavigation.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.bottomnavigation.Domain.DescubreDomain;
import com.example.bottomnavigation.R;

import java.util.ArrayList;
import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    List<DescubreDomain> dataList;
    DescubreDomain androidData;
    SearchView searchView;
    private View recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchCatalogo);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(CatalogoActivity.this, 1);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        dataList = new ArrayList<>();
//        androidData = new DescubreDomain("Camera", R.string.lomo_de_res, "Java", "R.drawable.lomores)";
//        dataList.add(androidData);
//        androidData = new DescubreDomain("RecyclerView",R.string.lomo_de_res, "Kotlin", "R.drawable.lomores");
//        dataList.add(androidData);
//        androidData = new DescubreDomain("Date Picker", R.string., "Java", R.drawable.);
//        dataList.add(androidData);
//        androidData = new DescubreDomain("EditText", R.string., "Kotlin", R.drawable.);
//        dataList.add(androidData);
//        androidData = new DescubreDomain("Rating Bar", R.string., "Java", R.drawable.);
//        dataList.add(androidData);
//        adapter = new DescubreAdapter(CatalogoActivity.this, DescubreDomain);
//        recyclerView.getContext(adapter);

    }
    private void searchList(String text){
        List<DescubreDomain> dataSearchList = new ArrayList<>();
        for (DescubreDomain data : dataList){
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {

        }
    }
}