package com.envio_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa el inventario con sus detalles")
public class InventarioDTO {
	@Schema(description = "ID único del inventario", example = "1")
	private int inventarioId;
	
	@Schema(description = "la cantidad disponible de un producto en el inventario", example = "107")
    private int cantidadDisponible;
	
	@Schema(description = "informacion del producto obtenido con exito en el inventario", example = "109")
    private ProductoDTO producto;
    
    @Schema(description = "informacion de la sucursal a base de inventario ", example = "112") 
    private SucursalDTO sucursal;
    
	public InventarioDTO() {
		super();
	}
	public int getInventarioId() {
		return inventarioId;
	}
	public void setInventarioId(int inventarioId) {
		this.inventarioId = inventarioId;
	}
	public int getCantidadDisponible() {
		return cantidadDisponible;
	}
	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}
	public ProductoDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	public SucursalDTO getSucursal() {
		return sucursal;
	}
	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}
    
    
}
