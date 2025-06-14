package com.boleta_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa una detalle boletaDTO del sistema.")
public class DetalleBoletaDTO {
	
	@Schema(description = "id del producto.", example = "1")
	private int productoId;
	@Schema(description = "nombre del producto.", example = "colonia")
	private String nombre_producto;
	@Schema(description = "id del detalle boletaDTO.", example = "2")
	private int detalleBoletaId;
	@Schema(description = "caantidad del detalle boletaDTO.", example = "10")
	private int cantidad;
	@Schema(description = "subtotal del detalle boletaDTO.", example = "$10")
	private int subtotal;
	@Schema(description = "id de boleta.", example = "3")
	private int boletaId;
	@Schema(description = "id de sucursal.", example = "4")
	private int sucursalId;
	@Schema(description = "direccion de la sucursal.", example = "av españa")
	private String sucursalDireccion;
	
	public String getSucursalDireccion() {
		return sucursalDireccion;
	}
	public void setSucursalDireccion(String sucursalDireccion) {
		this.sucursalDireccion = sucursalDireccion;
	}
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getUsuarioNombre() {
		return usuarioNombre;
	}
	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}
	private int usuarioId;
	private String usuarioNombre;

	
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
	public int getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}
	
	
}
