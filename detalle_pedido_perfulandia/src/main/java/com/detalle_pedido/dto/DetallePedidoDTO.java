package com.detalle_pedido.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DetallePedidoDTO", description = "DTO que representa un ítem dentro de un pedido")
public class DetallePedidoDTO {

    @Schema(description = "ID del producto que se está solicitando", example = "100")
    private int productoId;

    @Schema(description = "Cantidad del producto solicitada", example = "3")
    private int cantidad;

    @Schema(description = "ID del pedido al que pertenece este detalle", example = "2001")
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
