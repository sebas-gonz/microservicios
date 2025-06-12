package com.usuario_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa el detalle de un producto dentro de un pedido")
public class DetallePedidoDTO {

    @Schema(description = "ID del producto incluido en el pedido", example = "3")
    private int productoId;

    @Schema(description = "Información del producto solicitado")
    private ProductoDTO producto;

    @Schema(description = "Cantidad del producto solicitada en el pedido", example = "2")
    private int cantidad;
    
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
	public DetallePedidoDTO() {
		super();
	}
	public ProductoDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	
}
