	package com.detalle_boleta_perfulandia.entidades;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(description = "Entidad que representa una detalle boleta del sistema.")
public class DetalleBoleta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único de una detalle boleta.")
	private int detalleBoletaId;
	@Schema(description = "Id de una producto segun detalle boleta.", example = "2")
	private int productoId;
	@Schema(description = "Nombre del producto una detalle boleta.", example = "perfume")
	private String nombreProducto;
	@Schema(description = "cantidad de una detalle boleta.", example = "3")
	private int cantidad;
	@Schema(description = "Subtotal de una detalle boleta.", example = "4")
	private int subtotal;
	@Schema(description = "Id de boleta segun detalle boleta.", example = "5")
	private int boletaId;

	
	public int getBoletaId() {
		return boletaId;
	}
	public void setBoletaId(int boletaId) {
		this.boletaId = boletaId;
	}
	public int getProductoId() {
		return productoId;
	}
	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}
	public int getDetalleBoletaId() {
		return detalleBoletaId;
	}
	public void setDetalleBoletaId(int detalleBoletaId) {
		this.detalleBoletaId = detalleBoletaId;
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
	public DetalleBoleta() {
		super();
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
}
