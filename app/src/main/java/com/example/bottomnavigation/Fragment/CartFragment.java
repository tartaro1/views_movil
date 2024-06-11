package com.example.bottomnavigation.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

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
    private TextView totaltarifaCart,TaxTxT,delivaryTxt,totalTxT, vacioTxT;
    private double tax;
    private ScrollView scrollView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initView();
        initList();
        calcularteCard();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), getContext(), new ChangeNumberitemsListener() {
            @Override
            public void changed() {
                calcularteCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            vacioTxT.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }else {
            vacioTxT.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calcularteCard(){
        double percenTax = 0.18; //puede cambiar este artículo por el precio de impuestos
        double delivery = 10; //puede cambiar este artículo necesita precio para la entrega

        tax=Math.round((managementCart.getTotalFee()*percenTax)*100.0)/100.0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0)/100.0;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

        totaltarifaCart.setText("$"+itemTotal);
        TaxTxT.setText("$"+tax);
        delivaryTxt.setText("$"+delivery);
        totalTxT.setText("$"+total);
    }

    private void initView() {
        totaltarifaCart = totaltarifaCart.findViewById(R.id.totaltarifaCart);
        TaxTxT = TaxTxT.findViewById(R.id.TaxTxT);
        delivaryTxt = delivaryTxt.findViewById(R.id.delivaryTxt);
        totalTxT = totalTxT.findViewById(R.id.totalCart);
        recyclerViewList = recyclerViewList.findViewById(R.id.view);
        scrollView = scrollView.findViewById(R.id.scrollView);
        vacioTxT = vacioTxT.findViewById(R.id.vacioCart);
    }
}