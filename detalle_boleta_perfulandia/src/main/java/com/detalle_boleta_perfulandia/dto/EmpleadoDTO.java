package com.detalle_boleta_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa una emoleadoDTO del sistema.")
public class EmpleadoDTO {
	@Schema(description = "id del empleado.", example = "1")
	private int empleadoId;
	@Schema(description = "nombre del empleado.", example = "juan")
	private String nombre;
	@Schema(description = "correo del empleado.", example = "juan@123.com")
	private String correo;
	@Schema(description = "rut del empleado.", example = "23878097-2")
	private String rut;
	@Schema(description = "cargo del empleado.", example = "jefe")
	private String cargo;
	@Schema(description = "id de la sucursal.", example = "2")
	private int sucursalId;
	
	public EmpleadoDTO() {
		super();
	}
	public int getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}
	
}
