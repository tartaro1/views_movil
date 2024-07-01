package com.example.bottomnavigation.Fragment;

import android.content.Context;
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
import com.example.bottomnavigation.API.RegisterRequest;
import com.example.bottomnavigation.API.RegisterResponse;
import com.example.bottomnavigation.R;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroFragment extends Fragment {

    private EditText etName,etPhone, etCedula, etLocation, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private ApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        etName = view.findViewById(R.id.signup_name);
        etPhone = view.findViewById(R.id.signup_telefono);
        etCedula = view.findViewById(R.id.signup_cedula);
        etLocation = view.findViewById(R.id.signup_location);
        etEmail = view.findViewById(R.id.signup_email);
        etPassword = view.findViewById(R.id.signup_password);
        etConfirmPassword = view.findViewById(R.id.signup_confirm);
        btnRegister = view.findViewById(R.id.signup_button);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor authInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                // Obtén el token de donde lo tengas almacenado
                String token = getTokenFromStorage();

                Request newRequest = originalRequest.newBuilder()
                        .header("x-access-token", token)
                        .build();

                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ms-backend-tartaro.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        return view;
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        int cedula = Integer.parseInt(etCedula.getText().toString().trim());
        String celular = etPhone.getText().toString().trim();
        String direccion = etLocation.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (name.isEmpty() || cedula == 0 || celular.isEmpty() || direccion.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtén el token de donde lo tengas almacenado
        String token = getTokenFromStorage(); // Implementa este método según donde almacenes el token

        RegisterRequest registerRequest = new RegisterRequest(name,cedula, celular, direccion, email, password);

        Call<RegisterResponse> call = apiService.registerUser(token, registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegisterResponse registerResponse = response.body();
                    if (!registerResponse.isError() && registerResponse.getStatus() == 201) {
                        Toast.makeText(getContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                        // Aquí puedes navegar a la siguiente actividad o cerrar este fragmento
                        Fragment perfilFragment = new ProfileFragment();
                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.ProfileFragment, perfilFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else {
                        Toast.makeText(getContext(), "Registro fallido: " + registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (response.code() == 401) {
                        Toast.makeText(getContext(), "Token no válido o expirado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getTokenFromStorage() {
        // Implementa este método para obtener el token de donde lo tengas almacenado
        // Por ejemplo, si lo tienes en SharedPreferences:
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return prefs.getString("access_token", "");
    }
}