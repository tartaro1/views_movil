package com.example.bottomnavigation.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bottomnavigation.Adapter.CategoryAdapter;
import com.example.bottomnavigation.Adapter.RecommendedAdapter;
import com.example.bottomnavigation.Domain.CategoryDomain;
import com.example.bottomnavigation.Domain.FoodDomain;
import com.example.bottomnavigation.LoginMainActivity;
import com.example.bottomnavigation.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ImageSlider imageSlider;
    private RecyclerView.Adapter adapter, adapter2, adapter3, adapter4;
    private RecyclerView recyclerViewCategotyList, recyclerViewPopularList, recyclerViewDescubreList,recyclerViewCarnesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.imageSlider);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        setupImageSlider();
        recyclerViewCategory(view);
        recyclerViewPopular(view);
        recyclerViewDescubre(view);
        recyclerViewCarnes(view);

        return view;
    }

    private void setupImageSlider() {
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img_11, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_9, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_10, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pefil) {
            Toast.makeText(requireContext(), "mi cuenta", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), LoginMainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.cerrar) {
            Toast.makeText(requireContext(), "session cerrada", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void recyclerViewPopular(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = view.findViewById(R.id.recyclerViewRec);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Carnes Madurados", "rec_5", R.string.descripcionRec1, 13.0, R.string.esprecificacionRec1, 3.5, 1000));
        foodlist.add(new FoodDomain("Carne de Diablo Zenú", "rec_6", R.string.descripcionRec2, 15.0, R.string.esprecificacionRec2, 5, 1500));
        foodlist.add(new FoodDomain("Salsa Fruco de tomate ", "rec_7", R.string.descripcionRec3, 11.0, R.string.esprecificacionRec3, 4.5, 800));
        foodlist.add(new FoodDomain("Café Nescafé Dolca", "rec_8", R.string.descripcionRec4, 13.0, R.string.esprecificacionRec4, 3, 1000));
        foodlist.add(new FoodDomain("Yogurt Finesse", "rec_9", R.string.descripcionRec5, 11.0, R.string.esprecificacionRec5, 4.5, 800));

        adapter2 = new RecommendedAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategory(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategotyList = view.findViewById(R.id.recyclerViewCat);
        recyclerViewCategotyList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categorylist = new ArrayList<>();
        categorylist.add(new CategoryDomain("Frutas y vegetales", "cat_1"));
        categorylist.add(new CategoryDomain("Carnes", "cat_2"));
        categorylist.add(new CategoryDomain("Lateos", "cat_3"));
        categorylist.add(new CategoryDomain("Enlatados", "cat_4"));
        categorylist.add(new CategoryDomain("Panes", "cat_5"));
        categorylist.add(new CategoryDomain("Bebidas", "cat_6"));

        adapter = new CategoryAdapter(categorylist);
        recyclerViewCategotyList.setAdapter(adapter);
    }

    private void recyclerViewDescubre(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDescubreList = view.findViewById(R.id.recyclerViewDescubre);
        recyclerViewDescubreList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> DescubreList = new ArrayList<>();
        DescubreList.add(new FoodDomain("Sal REFISAL Refinada", "des_1", R.string.descripcionDes1, 11.0, R.string.esprecificacionDes1, 2.5, 800));
        DescubreList.add(new FoodDomain("Plátano llanero", "des_2", R.string.descripcionDes2, 2.0, R.string.esprecificacionDes2, 3, 800));
        DescubreList.add(new FoodDomain("Pan bimbo blanco \nactidefens tajado", "des_3", R.string.descripcionDes3, 7.000, R.string.esprecificacionRec5, 3.5, 800));

        adapter3 = new RecommendedAdapter(DescubreList);
        recyclerViewDescubreList.setAdapter(adapter3);
    }

    private void recyclerViewCarnes(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCarnesList = view.findViewById(R.id.recyclerViewCarnes);
        recyclerViewCarnesList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> carneslist = new ArrayList<>();
        carneslist.add(new FoodDomain("churrasco", "carnes1", R.string.descripcionRec1, 13.0, R.string.esprecificacionCarne1, 20, 1000));
        carneslist.add(new FoodDomain("Chuck Eye Roll \nEntrecot Americano", "carnes2", R.string.descripcionRec1, 15.0, R.string.esprecificacionCarne1, 18, 1500));
        carneslist.add(new FoodDomain("Costilla en bandeja", "carnes3", R.string.descripcionRec1, 11.0, R.string.esprecificacionCarne2, 16, 800));
        carneslist.add(new FoodDomain("Fressas", "rec_4", R.string.descripcionRec1, 13.0, 5, 20, 1000));

        adapter4 = new RecommendedAdapter(carneslist);
        recyclerViewCarnesList.setAdapter(adapter4);
    }

}

