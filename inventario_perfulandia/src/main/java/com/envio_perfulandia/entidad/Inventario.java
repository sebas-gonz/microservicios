package com.envio_perfulandia.entidad;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(description = "una entidad que representa el inventario sucursal y producto.")
public class Inventario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "inventado tiene que tener producto y su sucursal asignado .")
	private int inventarioId;
	@Schema(description = "producto de cada inventario con su respectiva sucursal.", example = "390")
    private int productoId;
	@Schema(description = "sucursal dirigida a producto el cual tiene un inventario.", example = "384")
    private int sucursalId;
	@Schema(description = "cantidad de productos de cada sucursal con su respectivo inventario.", example = "274")
    private int cantidadDisponible;

	public Inventario() {
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
	
	
    
}
