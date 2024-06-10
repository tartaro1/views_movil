package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bottomnavigation.Fragment.CartFragment;
import com.example.bottomnavigation.Fragment.DashFragment;
import com.example.bottomnavigation.Fragment.HomeFragment;
import com.example.bottomnavigation.Fragment.MapsFragment;
import com.example.bottomnavigation.Fragment.ProfileFragment;
import com.example.bottomnavigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

       binding.bottomNavigationView.setOnItemSelectedListener(item -> {

           switch (item.getItemId()) {
               case R.id.home:
                   replaceFragment(new HomeFragment());
                   break;

               case R.id.profile:
                   replaceFragment(new ProfileFragment());
                   break;

               case R.id.cart:
                   replaceFragment(new CartFragment());
                   break;

               case R.id.map:
                   replaceFragment(new MapsFragment());
                   break;

               case R.id.settings:
                   replaceFragment(new DashFragment());
                   break;
           }

           return true;

       });

        binding.floatingActionButton.setOnClickListener(v -> {
            replaceFragment(new CartFragment());
            binding.bottomNavigationView.setSelectedItemId(R.id.cart);
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}