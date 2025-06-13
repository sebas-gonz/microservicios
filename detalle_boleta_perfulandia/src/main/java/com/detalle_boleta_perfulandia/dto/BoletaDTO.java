package com.detalle_boleta_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa una boletaDTO del sistema.")
public class BoletaDTO {
	@Schema(description = "id del empleado.", example = "1")
	private int sucursalId;
	@Schema(description = "id del usuario.", example = "2")
	private int usuarioId;
	@Schema(description = "id del producto.", example = "4")
	private int productoId;
	@Schema(description = "id de la boleta.", example = "5")
	private int boletaId;
	@Schema(description = "cantidad de la boleta.", example = "10")
	private int cantidad;
	@Schema(description = "id del emoleado.", example = "6")
	private int empleadoId;
		
	public int getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public int getProductoId() {
		return productoId;
	}
	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}
	public int getBoletaId() {
		return boletaId;
	}
	public void setBoletaId(int boletaId) {
		this.boletaId = boletaId;
	}
	public BoletaDTO() {
		super();
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}
	
	
}
