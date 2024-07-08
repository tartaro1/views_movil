package com.example.bottomnavigation.API;
import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String NombreProducto;
    private String Marca;

    public Product(int calificacion, String descripcion, int id, String ID_Categoria, String imagen, String ID_Proveedor, String marca, String nombreProducto, int numberInCart, int stock, String precioVenta, int vendidos) {
        Calificacion = calificacion;
        Descripcion = descripcion;
        this.id = id;
        this.ID_Categoria = ID_Categoria;
        this.imagen = imagen;
        this.ID_Proveedor = ID_Proveedor;
        Marca = marca;
        NombreProducto = nombreProducto;
        this.numberInCart = numberInCart;
        Stock = stock;
        PrecioVenta = precioVenta;
        Vendidos = vendidos;
    }

    private String Descripcion;
    private String PrecioVenta;
    private int Calificacion;
    private int Vendidos;
    private String imagen;
    private int Stock;

    public int getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(int calificacion) {
        Calificacion = calificacion;
    }

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

    public void setPrecioVenta(String precioVenta) {
        PrecioVenta = precioVenta;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getID_Proveedor() {
        return ID_Proveedor;
    }

    public void setID_Proveedor(String ID_Proveedor) {
        this.ID_Proveedor = ID_Proveedor;
    }

    public String getID_Categoria() {
        return ID_Categoria;
    }

    public void setID_Categoria(String ID_Categoria) {
        this.ID_Categoria = ID_Categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    private String ID_Categoria;
    private String ID_Proveedor;
    private int numberInCart;

    // Constructor, getters y setters existentes...

    public int getId() {
        return id;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public String getPrecioVenta() {
        return PrecioVenta;
    }

    // ... otros métodos ...
}