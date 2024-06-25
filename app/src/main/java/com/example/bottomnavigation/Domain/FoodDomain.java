package com.example.bottomnavigation.Domain;

import java.io.Serializable;

public class FoodDomain  implements Serializable {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    private String title;
    private String pic;
    private String descripcion;
    private Double fee;
    private int time;
    private float star;
    private int calories;

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    private int numberInCart;
    public FoodDomain(String title, String pic, String descripcion, Double fee, int time, float star, int calories) {
        this.title = title;
        this.pic = pic;
        this.descripcion = descripcion;
        this.fee = fee;
        this.time = time;
        this.star = star;
        this.calories = calories;
    }
}
