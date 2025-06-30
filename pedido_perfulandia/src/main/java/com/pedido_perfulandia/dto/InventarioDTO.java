package com.pedido_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "InventarioDTO", description = "DTO que representa el inventario generada para un pedido")
public class InventarioDTO {
	@Schema(description = "Representa el el id del producto DTO", example="1")
	private int productoId;
	@Schema(description = "Representa el el id de la sucursal DTO", example="2")
    private int sucursalId;
	@Schema(description = "Representa el la cantidad disponible del DTO", example="1000")
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
