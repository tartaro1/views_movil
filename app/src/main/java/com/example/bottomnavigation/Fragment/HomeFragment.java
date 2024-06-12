package com.example.bottomnavigation.Fragment;

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
import com.example.bottomnavigation.Adapter.DescubreAdapter;
import com.example.bottomnavigation.Adapter.RecommendedAdapter;
import com.example.bottomnavigation.Domain.CategoryDomain;
import com.example.bottomnavigation.Domain.DescubreDomain;
import com.example.bottomnavigation.Domain.FoodDomain;
import com.example.bottomnavigation.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ImageSlider imageSlider;
    private RecyclerView.Adapter adapter, adapter2, adapter3;
    private RecyclerView recyclerViewCategotyList, recyclerViewPopularList, recyclerViewDescubreList;

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
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pefil) {
            Toast.makeText(requireContext(), "mi cuenta", Toast.LENGTH_SHORT).show();
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
        foodlist.add(new FoodDomain("Pepperoni pizza", "rec_1", "slices, mozzarella cheese, fresh oregano, ground black pepper, pizza sauce", 13.0, 5, 20, 1000));
        foodlist.add(new FoodDomain("Cheese Burger", "rec_2", "beef, Gouda Cheese, Special sauce, Lettuce, tomato", 15.0, 4, 18, 1500));
        foodlist.add(new FoodDomain("Vegetable pizza", "rec_3", "olive oil, Vegetable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 11.0, 3, 16, 800));
        foodlist.add(new FoodDomain("Fressas", "rec_4", "slices, mozzarella cheese, fresh oregano, ground black pepper, pizza sauce", 13.0, 5, 20, 1000));

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

        ArrayList<DescubreDomain> DescubreList = new ArrayList<>();
        DescubreList.add(new DescubreDomain("Detodito", R.drawable.img_3, R.string.des_detail, "0"));
        DescubreList.add(new DescubreDomain("Detodito verde", R.drawable.img_4, R.string.des_detail2,"" ));
        DescubreList.add(new DescubreDomain("Todo Rico", R.drawable.img_5, R.string.des_detail3, "11.0"));
        //DescubreList.add(new FoodDomain("Fressas", "rec_4", "slices, mozzarella cheese, fresh oregano, ground black pepper, pizza sauce", 13.0, 5, 20, 1000));

        adapter3 = new DescubreAdapter(DescubreList);
        recyclerViewDescubreList.setAdapter(adapter3);
    }

}

