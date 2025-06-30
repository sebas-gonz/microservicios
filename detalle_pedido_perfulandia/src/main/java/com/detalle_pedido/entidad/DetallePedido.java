package com.detalle_pedido.entidad;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Schema(name = "DetallePedido", description = "Entidad que representa el detalle de un producto dentro de un pedido")
@Entity
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del detalle del pedido", example = "501")
    private int detallePedidoId;

    @Schema(description = "ID del producto relacionado", example = "100")
    private int productoId;

    @Schema(description = "Nombre del producto", example = "Zapatos deportivos")
    private String nombreProducto;

    @Schema(description = "Cantidad solicitada del producto", example = "2")
    private int cantidad;

    @Schema(description = "Subtotal correspondiente a este detalle (precio * cantidad)", example = "29980")
    private int subtotal;

    @Schema(description = "ID del pedido al que pertenece este detalle", example = "2001")
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
