package com.example.bottomnavigation.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.bottomnavigation.API.Product;
import com.example.bottomnavigation.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertProduct(Product item) {
        ArrayList<Product> listProduct = getListCart();
        boolean existAlready = false;
        int n = 0;
        tinyDB.putListObject("CartList", listProduct);
        tinyDB.debugPrintCart("CartList");  // Añade esta línea
        Toast.makeText(context, "Añadido al carrito", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getId() == item.getId()) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listProduct.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listProduct.add(item);
        }

        tinyDB.putListObject("CartList", listProduct);
        Toast.makeText(context, "Añadido al carrito", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Product> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberProduct(ArrayList<Product> listProduct, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listProduct.get(position).setNumberInCart(listProduct.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", listProduct);
        changeNumberItemsListener.changed();
    }

    public void minusNumberProduct(ArrayList<Product> listProduct, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listProduct.get(position).getNumberInCart() == 1) {
            listProduct.remove(position);
        } else {
            listProduct.get(position).setNumberInCart(listProduct.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", listProduct);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<Product> listProduct = getListCart();
        double fee = 0;
        for (int i = 0; i < listProduct.size(); i++) {
            fee = fee + (Double.parseDouble(listProduct.get(i).getPrecioVenta()) * listProduct.get(i).getNumberInCart());
        }
        return fee;
    }

    public void clearCart() {
        tinyDB.remove("CartList");
    }
}