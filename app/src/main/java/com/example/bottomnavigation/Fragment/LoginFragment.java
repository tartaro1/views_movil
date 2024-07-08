package com.example.bottomnavigation.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bottomnavigation.API.ApiService;
import com.example.bottomnavigation.API.LoginRequest;
import com.example.bottomnavigation.API.LoginResponse;
import com.example.bottomnavigation.Activity.DeliveryActivity;
import com.example.bottomnavigation.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = view.findViewById(R.id.login_email);
        etPassword = view.findViewById(R.id.login_password);
        btnLogin = view.findViewById(R.id.login_button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ms-backend-tartaro.onrender.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        return view;
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest loginRequest = new LoginRequest(email, password);

        Call<LoginResponse> call = apiService.loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    String token = loginResponse.getToken();
                    int role = loginResponse.getRole();

                    if (token != null && !token.isEmpty()) {
                        saveTokenToStorage(token);
                        saveRoleToStorage(role);
                        Toast.makeText(getContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                        // Navegar según el rol
                        navigateBasedOnRole(role);
                    } else {
                        Toast.makeText(getContext(), "Token inválido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateBasedOnRole(int role) {
        if (role == 1) {  // Asumiendo que 1 es para usuarios normales
            Fragment perfilFragment = new ProfileFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.ProfileFragment, perfilFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (role == 3) {  // Asumiendo que 3 es para repartidores
            Intent intent = new Intent(getActivity(), DeliveryActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Rol no reconocido", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveTokenToStorage(String token) {
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("access_token", token);
        editor.apply();
    }

    private void saveRoleToStorage(int role) {
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("user_role", role);
        editor.apply();
    }
}