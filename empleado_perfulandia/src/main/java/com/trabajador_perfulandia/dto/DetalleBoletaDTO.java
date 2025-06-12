package com.trabajador_perfulandia.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class DetalleBoletaDTO {
	
	private int productoId;
	private String nombre_producto;
	private int detalleBoletaId;
	private int cantidad;
	private int subtotal;
	private int boletaId;
	private int sucursalId;
	private String sucursalDireccion;
	private int usuarioId;
	private String usuarioNombre;
	
	public int getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(int sucursal_id) {
		this.sucursalId = sucursal_id;
	}
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuario_id) {
		this.usuarioId = usuario_id;
	}
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
	public String getSucursalDireccion() {
		return sucursalDireccion;
	}
	public void setSucursalDireccion(String sucursalDireccion) {
		this.sucursalDireccion = sucursalDireccion;
	}
	public String getUsuarioNombre() {
		return usuarioNombre;
	}
	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}
	
	
	
}
