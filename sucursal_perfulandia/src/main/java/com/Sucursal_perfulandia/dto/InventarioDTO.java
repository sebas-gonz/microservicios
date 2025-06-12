package com.Sucursal_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa el estado del inventario de un producto en una sucursal.")
public class InventarioDTO {

    @Schema(description = "Identificador del registro de inventario.", example = "1")
    private int inventarioId;

    @Schema(description = "Identificador del producto en inventario.", example = "5")
    private int productoId;

    @Schema(description = "Información detallada del producto.")
    private ProductoDTO producto;
    
    @Schema(description = "Identificador de la sucursal donde se encuentra el inventario.", example = "2")
    private int sucursalId;

    @Schema(description = "Cantidad disponible del producto en la sucursal.", example = "30")
    private int cantidadDisponible;

   
	public InventarioDTO() {
		super();
	}

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

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	
}
