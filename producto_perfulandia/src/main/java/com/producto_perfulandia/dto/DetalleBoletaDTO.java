package com.producto_perfulandia.dto;


public class DetalleBoletaDTO {
	
	private int productoId;
	private String nombre_producto;
	private int detalleBoletaId;
	private int cantidad;
	private int subtotal;
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
