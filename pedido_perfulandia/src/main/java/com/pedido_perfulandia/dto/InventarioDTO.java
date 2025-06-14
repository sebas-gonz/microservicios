package com.pedido_perfulandia.dto;

public class InventarioDTO {
	
	private int productoId;
    private int sucursalId;
    private int cantidadDisponible;
    
	private int inventarioId;
    public int getInventarioId() {
		return inventarioId;
	}
	public void setInventarioId(int inventarioId) {
		this.inventarioId = inventarioId;
	}
	public int getProductoId() {
		return productoId;
	}
	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}
	public int getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}
	public int getCantidadDisponible() {
		return cantidadDisponible;
	}
	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
	
	public InventarioDTO() {
		super();
		
	}

}
