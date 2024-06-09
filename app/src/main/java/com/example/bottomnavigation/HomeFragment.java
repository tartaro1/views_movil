package com.example.bottomnavigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private ImageSlider imageSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        imageSlider = view.findViewById(R.id.imageSlider);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        setupImageSlider();

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
}