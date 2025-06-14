package com.pedido_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DetalleBoletaDTO", description = "DTO que representa el detalle de un producto dentro de una boleta")
public class DetalleBoletaDTO {

    @Schema(description = "ID único del detalle de la boleta", example = "100")
    private int detalleBoletaId;

    @Schema(description = "ID del producto asociado", example = "50")
    private int productoId;

    @Schema(description = "Nombre del producto", example = "Camiseta deportiva")
    private String nombreProducto;

    @Schema(description = "Cantidad del producto en el detalle", example = "3")
    private int cantidad;

    @Schema(description = "Subtotal calculado para este detalle", example = "45000")
    private int subtotal;

    @Schema(description = "ID de la boleta a la que pertenece este detalle", example = "5001")
    private int boletaId;
    
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
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
	public int getBoletaId() {
		return boletaId;
	}
	public void setBoletaId(int boletaId) {
		this.boletaId = boletaId;
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
	
}
