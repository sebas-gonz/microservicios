package com.trabajador_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Schema(description = "Representa un esquema de la entidad detalle boleta")
public class DetalleBoletaDTO {
	@Schema(description = "Representa el id del prodcuto", example="1")
	private int productoId;
	@Schema(description = "Representa nombre del producto", example="Perfume")
	private String nombreProducto;
	@Schema(description = "Representa el id de detalle boleta", example="1")
	private int detalleBoletaId;
	@Schema(description = "Representa la cantidad de productos", example="10")
	private int cantidad;
	@Schema(description = "Representa el subtotal de la venta", example="410000")
	private int subtotal;
	@Schema(description = "Representa el ID de la boleta", example="1")
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getDetalleBoletaId() {
		return detalleBoletaId;
	}
	public void setDetalleBoletaId(int detalle_boleta_id) {
		this.detalleBoletaId = detalle_boleta_id;
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
	
	
	
}
