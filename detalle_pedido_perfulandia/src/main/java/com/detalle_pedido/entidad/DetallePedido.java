package com.detalle_pedido.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DetallePedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detallePedidoId;

    private int productoId;
    private String nombreProducto;
    private int cantidad;
    private int subtotal;
    private int pedidoId;

    public DetallePedido() {}

    public int getDetallePedidoId() {
        return detallePedidoId;
    }

    public void setDetallePedidoId(int detallePedidoId) {
        this.detallePedidoId = detallePedidoId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }
}
