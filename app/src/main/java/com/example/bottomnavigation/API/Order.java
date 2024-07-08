package com.example.bottomnavigation.API;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("ID_Pedido")
    private int idPedido;

    @SerializedName("EstadoPedido")
    private String estadoPedido;

    @SerializedName("Direccion")
    private String direccion;

    @SerializedName("PrecioVenta")
    private String precioVenta;

    @SerializedName("FechaPedido")
    private String fechaPedido;

    @SerializedName("Nombre_Cliente")
    private String nombreCliente;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(String idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    @SerializedName("ID_Repartidor")
    private String idRepartidor;
}
