package com.pedido_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DetallePedidoDTO", description = "DTO que representa el detalle de un producto dentro de un pedido")
public class DetallePedidoDTO {

    @Schema(description = "ID del producto", example = "50")
    private int productoId;

    @Schema(description = "Cantidad del producto en el pedido", example = "2")
    private int cantidad;

    @Schema(description = "ID del pedido al que pertenece este detalle", example = "101")
    private int pedidoId;
    
	public int getProductoId() {
		return productoId;
	}
	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}
	public DetallePedidoDTO() {
		super();
	}
	
}
