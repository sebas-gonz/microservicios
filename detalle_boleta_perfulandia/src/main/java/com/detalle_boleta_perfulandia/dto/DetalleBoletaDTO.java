package com.detalle_boleta_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa una detalle boletaDTO del sistema.")
public class DetalleBoletaDTO {
	@Schema(description = "id de detalle boleta.", example = "1")
	private int detalleBoletaId;
	@Schema(description = "id del producto.", example = "2")
	private int productoId;
	@Schema(description = "nombre del producto.", example = "perfume")
    private String nombreProducto;
	@Schema(description = "cantidad de detalle boletaDTO.", example = "10")
    private int cantidad;
	@Schema(description = "subtotal de detalle boletaDTO.", example = "$500")
    private int subtotal;
	@Schema(description = "id de boleta.", example = "3")
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
