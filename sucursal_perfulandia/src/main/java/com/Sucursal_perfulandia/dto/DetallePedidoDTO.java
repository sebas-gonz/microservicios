package com.Sucursal_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa el detalle de un pedido.")
public class DetallePedidoDTO {

    @Schema(description = "Identificador del producto incluido en el pedido.", example = "3")
    private int productoId;

    @Schema(description = "Información del producto incluido en el pedido.")
    private ProductoDTO producto;

    @Schema(description = "Cantidad de unidades del producto solicitadas.", example = "2")
    private int cantidad;

    @Schema(description = "Identificador del pedido al que pertenece este detalle.", example = "1")
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
	public ProductoDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	
}
