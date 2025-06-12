package com.usuario_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa el detalle de una boleta, incluyendo el producto, cantidad y subtotal")
public class DetalleBoletaDTO {

    @Schema(description = "ID único del detalle de boleta", example = "1")
    private int detalleBoletaId;

    @Schema(description = "ID del producto incluido en el detalle", example = "3")
    private int productoId;

    @Schema(description = "Información detallada del producto")
    private ProductoDTO producto;

    @Schema(description = "Cantidad del producto", example = "2")
    private int cantidad;

    @Schema(description = "Subtotal del producto (cantidad * precio)", example = "1566")
    private int subtotal;
    
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
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	public DetalleBoletaDTO() {
		super();
	}
	public int getDetalleBoletaId() {
		return detalleBoletaId;
	}
	public void setDetalleBoletaId(int detalleBoletaId) {
		this.detalleBoletaId = detalleBoletaId;
	}
	public ProductoDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	
}
