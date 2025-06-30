package com.producto_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detalle de un producto incluido en una boleta de compra")
public class DetalleBoletaDTO {

    @Schema(description = "ID del producto comprado", example = "101")
    private int productoId;

    @Schema(description = "Nombre del producto", example = "Perfume Aromax 50ml")
    private String nombre_producto;

    @Schema(description = "ID del detalle de boleta", example = "5001")
    private int detalleBoletaId;

    @Schema(description = "Cantidad de unidades compradas", example = "2")
    private int cantidad;

    @Schema(description = "Subtotal por este producto (cantidad x precio unitario)", example = "29980")
    private int subtotal;

    @Schema(description = "ID de la boleta a la que pertenece este detalle", example = "3001")
    private int boletaId;
	
	public int getBoletaId() {
		return boletaId;
	}
	public void setBoletaId(int boleta_id) {
		this.boletaId = boleta_id;
	}
	public int getProductoId() {
		return productoId;
	}
	public void setProductoId(int producto_id) {
		this.productoId = producto_id;
	}
	
	
	public int getDetalleBoletaId() {
		return detalleBoletaId;
	}
	public void setDetalleBoletaId(int detalle_boleta_id) {
		this.detalleBoletaId = detalle_boleta_id;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
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
	
	
}
