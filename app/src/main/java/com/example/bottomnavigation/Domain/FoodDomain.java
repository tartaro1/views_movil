package com.example.bottomnavigation.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private int id;
    private String NombreProducto;
    private String Marca;
    private String Descripcion;
    private String PrecioVenta;
    private int Calificacion;

    public int getVendidos() {
        return Vendidos;
    }

    public void setVendidos(int vendidos) {
        Vendidos = vendidos;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getPrecioVenta() {
        return PrecioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        PrecioVenta = precioVenta;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getID_Proveedor() {
        return ID_Proveedor;
    }

    public void setID_Proveedor(String ID_Proveedor) {
        this.ID_Proveedor = ID_Proveedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getID_Categoria() {
        return ID_Categoria;
    }

    public void setID_Categoria(String ID_Categoria) {
        this.ID_Categoria = ID_Categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(int calificacion) {
        Calificacion = calificacion;
    }

    private int Vendidos;
    private String imagen;
    private int Stock;
    private String ID_Categoria;
    private String ID_Proveedor;
    private int numberInCart;

    // Añade getters y setters para todos los campos
    // ...

    public String getTitle() {
        return NombreProducto;
    }

    public double getFee() {
        return Double.parseDouble(PrecioVenta);
    }

    public String getPic() {
        return imagen;
    }

    public double getStar() {
        return Calificacion;
    }

    // ... otros métodos necesarios
}
