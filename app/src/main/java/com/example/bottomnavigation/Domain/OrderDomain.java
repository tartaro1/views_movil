package com.example.bottomnavigation.Domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OrderDomain implements Parcelable {
    @SerializedName("ID_Pedido")
    private int ID_Pedido;

    @SerializedName("EstadoPedido")
    private String EstadoPedido;

    @SerializedName("Direccion")
    private String Direccion;

    @SerializedName("PrecioVenta")
    private String PrecioVenta;

    @SerializedName("FechaPedido")
    private String FechaPedido;

    @SerializedName("Nombre_Cliente")
    private String Nombre_Cliente;

    @SerializedName("ID_Repartidor")
    private String ID_Repartidor;

    public OrderDomain(int ID_Pedido, String EstadoPedido, String Direccion, String PrecioVenta, String FechaPedido, String Nombre_Cliente, String ID_Repartidor) {
        this.ID_Pedido = ID_Pedido;
        this.EstadoPedido = EstadoPedido;
        this.Direccion = Direccion;
        this.PrecioVenta = PrecioVenta;
        this.FechaPedido = FechaPedido;
        this.Nombre_Cliente = Nombre_Cliente;
        this.ID_Repartidor = ID_Repartidor;
    }

    protected OrderDomain(Parcel in) {
        ID_Pedido = in.readInt();
        EstadoPedido = in.readString();
        Direccion = in.readString();
        PrecioVenta = in.readString();
        FechaPedido = in.readString();
        Nombre_Cliente = in.readString();
        ID_Repartidor = in.readString();
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

    public int getID_Pedido() {
        return ID_Pedido;
    }

    public String getEstadoPedido() {
        return EstadoPedido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getPrecioVenta() {
        return PrecioVenta;
    }

    public String getFechaPedido() {
        return FechaPedido;
    }

    public String getNombre_Cliente() {
        return Nombre_Cliente;
    }

    public String getID_Repartidor() {
        return ID_Repartidor;
    }

    @Override
    public int describeContents() {
        return 0;
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
}
