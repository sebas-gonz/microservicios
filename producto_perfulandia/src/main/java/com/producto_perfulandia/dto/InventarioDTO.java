package com.producto_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Inventario de un producto en una sucursal específica")
public class InventarioDTO {

    @Schema(description = "ID del inventario", example = "1001")
    private int inventarioId;

    @Schema(description = "ID del producto registrado en inventario", example = "2001")
    private int productoId;

    @Schema(description = "ID de la sucursal donde se encuentra el producto", example = "3001")
    private int sucursalId;

    @Schema(description = "Datos de la sucursal donde se encuentra el inventario")
    private SucursalDTO sucursal;

    @Schema(description = "Cantidad disponible del producto en esta sucursal", example = "25")
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
	
	public SucursalDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}
	
}
