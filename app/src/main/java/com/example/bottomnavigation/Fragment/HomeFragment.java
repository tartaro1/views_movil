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
import com.example.bottomnavigation.API.ApiClient;
import com.example.bottomnavigation.API.ApiService;
import com.example.bottomnavigation.API.Product;
import com.example.bottomnavigation.Adapter.CategoryAdapter;
import com.example.bottomnavigation.Adapter.RecommendedAdapter;
import com.example.bottomnavigation.Domain.CategoryDomain;
import com.example.bottomnavigation.LoginMainActivity;
import com.example.bottomnavigation.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private ImageSlider imageSlider;
    private RecyclerView.Adapter adapter, recommendedAdapter, carnesAdapter;
    private RecyclerView recyclerViewCategoryList, recyclerViewRecommended, recyclerViewCarnes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.imageSlider);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        setupImageSlider();
        setupRecyclerViews(view);
        loadProducts();

        return view;
    }

    private void setupImageSlider() {
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.img_11, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_9, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_10, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }

    private void setupRecyclerViews(View view) {
        recyclerViewCategoryList = view.findViewById(R.id.recyclerViewCat);
        recyclerViewRecommended = view.findViewById(R.id.recyclerViewRec);
        recyclerViewCarnes = view.findViewById(R.id.recyclerViewCarnes);

        recyclerViewCategoryList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCarnes.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        setupCategoryRecyclerView();
    }

    private void setupCategoryRecyclerView() {
        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Frutas y vegetales", "cat_1"));
        categoryList.add(new CategoryDomain("Carnes", "cat_2"));
        categoryList.add(new CategoryDomain("Lácteos", "cat_3"));
        categoryList.add(new CategoryDomain("Enlatados", "cat_4"));
        categoryList.add(new CategoryDomain("Panes", "cat_5"));
        categoryList.add(new CategoryDomain("Bebidas", "cat_6"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void loadProducts() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateRecyclerViews(response.body());
                } else {
                    Toast.makeText(requireContext(), "Error al cargar productos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(requireContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateRecyclerViews(List<Product> products) {
        recommendedAdapter = new RecommendedAdapter(new ArrayList<>(products));
        recyclerViewRecommended.setAdapter(recommendedAdapter);

        List<Product> carnes = products.stream()
                .filter(p -> "Carnes".equals(p.getID_Categoria()))
                .collect(Collectors.toList());
        carnesAdapter = new RecommendedAdapter(new ArrayList<>(carnes));
        recyclerViewCarnes.setAdapter(carnesAdapter);
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
            Toast.makeText(requireContext(), "Mi cuenta", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginMainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.cerrar) {
            Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}