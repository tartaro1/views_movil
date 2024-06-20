package com.example.bottomnavigation.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.Adapter.CartListAdapter;
import com.example.bottomnavigation.Helper.ManagementCart;
import com.example.bottomnavigation.Interface.ChangeNumberitemsListener;
import com.example.bottomnavigation.R;

public class CartFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totaltarifaCart, TaxTxT, delivaryTxt, totalTxT, vacioTxT,successButtom;
    private double tax;
    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);


        managementCart = new ManagementCart(requireContext());

        initView(view);
        initList(view);
        calculateCart();

        return view;
    }

    private void initList(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), requireContext(), new ChangeNumberitemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            vacioTxT.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            vacioTxT.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double percentTax = 0.18; // puede cambiar este valor por el porcentaje de impuestos
        double delivery = 10; // puede cambiar este valor por el costo de entrega

        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100.0) / 100.0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

        totaltarifaCart.setText("$" + itemTotal);
        TaxTxT.setText("$" + tax);
        delivaryTxt.setText("$" + delivery);
        totalTxT.setText("$" + total);
    }

    private void initView(View view) {
        totaltarifaCart = view.findViewById(R.id.totaltarifaCart);
        TaxTxT = view.findViewById(R.id.TaxTxT);
        delivaryTxt = view.findViewById(R.id.delivaryTxt);
        totalTxT = view.findViewById(R.id.totalCart);
        recyclerViewList = view.findViewById(R.id.view);
        scrollView = view.findViewById(R.id.scrollView);
        vacioTxT = view.findViewById(R.id.vacioCart);
        successButtom = view.findViewById(R.id.successButtom);

        successButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccessDialog();
            }
        });
    }

    private void showSuccessDialog() {
        ConstraintLayout successContraintLayout;
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.success_dialog, null);
        Button successDone = view.findViewById(R.id.successDone);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        successDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Toast.makeText(requireContext(), "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
            }
        });
        if (alertDialog.isShowing()) {
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        alertDialog.show();
    }
}
