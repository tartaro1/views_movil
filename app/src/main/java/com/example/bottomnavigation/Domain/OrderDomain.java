package com.example.bottomnavigation.Domain;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderDomain implements Parcelable{
    private int ID_Pedido;
    private String EstadoPedido;
    private String Direccion;
    private String PrecioVenta;
    private String FechaPedido;

    public int getID_Pedido() {
        return ID_Pedido;
    }

    public void setID_Pedido(int ID_Pedido) {
        this.ID_Pedido = ID_Pedido;
    }

    public String getEstadoPedido() {
        return EstadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        EstadoPedido = estadoPedido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getPrecioVenta() {
        return PrecioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        PrecioVenta = precioVenta;
    }

    public String getFechaPedido() {
        return FechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        FechaPedido = fechaPedido;
    }

    public String getNombre_Cliente() {
        return Nombre_Cliente;
    }

    public void setNombre_Cliente(String nombre_Cliente) {
        Nombre_Cliente = nombre_Cliente;
    }

    public String getID_Repartidor() {
        return ID_Repartidor;
    }

    public void setID_Repartidor(String ID_Repartidor) {
        this.ID_Repartidor = ID_Repartidor;
    }

    private String Nombre_Cliente;

    public OrderDomain(int ID_Pedido, String estadoPedido, String direccion, String precioVenta, String fechaPedido, String nombre_Cliente, String ID_Repartidor) {
        this.ID_Pedido = ID_Pedido;
        EstadoPedido = estadoPedido;
        Direccion = direccion;
        PrecioVenta = precioVenta;
        FechaPedido = fechaPedido;
        Nombre_Cliente = nombre_Cliente;
        this.ID_Repartidor = ID_Repartidor;
    }

    private String ID_Repartidor;

    protected OrderDomain(Parcel in) {
        ID_Pedido = in.readInt();
        EstadoPedido = in.readString();
        Direccion = in.readString();
        PrecioVenta = in.readString();
        FechaPedido = in.readString();
        Nombre_Cliente = in.readString();
        ID_Repartidor = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID_Pedido);
        dest.writeString(EstadoPedido);
        dest.writeString(Direccion);
        dest.writeString(PrecioVenta);
        dest.writeString(FechaPedido);
        dest.writeString(Nombre_Cliente);
        dest.writeString(ID_Repartidor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderDomain> CREATOR = new Creator<OrderDomain>() {
        @Override
        public OrderDomain createFromParcel(Parcel in) {
            return new OrderDomain(in);
        }

        @Override
        public OrderDomain[] newArray(int size) {
            return new OrderDomain[size];
        }
    };
}
