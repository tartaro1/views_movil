package com.example.bottomnavigation.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bottomnavigation.Adapter.CategoryAdapter;
import com.example.bottomnavigation.Adapter.RecommendedAdapter;
import com.example.bottomnavigation.Domain.CategoryDomain;
import com.example.bottomnavigation.Domain.FoodDomain;
import com.example.bottomnavigation.R;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private ImageSlider imageSlider;
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategotyList, recyclerViewPopularList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        imageSlider = view.findViewById(R.id.imageSlider);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        setupImageSlider();
        recyclerViewCategory();
//        recyclerViewPopular();

        return view;
    }

    private void setupImageSlider() {
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, Objects.requireNonNull(inflater));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pefil) {
            Toast.makeText(requireContext(), "mi cuenta", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.cerrar) {
            Toast.makeText(requireContext(), "session cerrada", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = recyclerViewPopularList.findViewById(R.id.recyclerViewRec);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Pepperoni pizza", "pizza1", "slices, mozzarella cheese, fresh oregano, ground black pepper, pizza sauce", 13.0, 5, 20, 1000));
        foodlist.add(new FoodDomain("Cheese Burger", "burger", "beef, Gouda Cheese, Special sauce, Lettuce, tomato", 15.0, 4, 18, 1500));
        foodlist.add(new FoodDomain("Vegetable pizza", "pizza3", "olive oil, Vegetable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 11.0, 3, 16, 800));

        adapter2 = new RecommendedAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategotyList = recyclerViewCategotyList.findViewById(R.id.recyclerViewCat);
        recyclerViewCategotyList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categorylist = new ArrayList<>();
        categorylist.add(new CategoryDomain("Pizza", "cat_1"));
        categorylist.add(new CategoryDomain("Burger", "cat_2"));
        categorylist.add(new CategoryDomain("Hotdog", "cat_3"));
        categorylist.add(new CategoryDomain("Drink", "cat_4"));
        categorylist.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdapter(categorylist);
        recyclerViewCategotyList.setAdapter(adapter);
    }
}

