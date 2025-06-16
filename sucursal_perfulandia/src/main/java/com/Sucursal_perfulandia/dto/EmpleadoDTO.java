package com.Sucursal_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa a un empleado.")
public class EmpleadoDTO {
	
	@Schema(description = "ID único del empleado", example = "101")
    private int empleadoId;
	
    @Schema(description = "Nombre completo del empleado.", example = "Juan Pérez")
    private String nombre;

    @Schema(description = "Correo electrónico del empleado.", example = "juan.perez@example.com")
    private String correo;

    @Schema(description = "RUT del empleado (Rol Único Tributario).", example = "12.345.678-9")
    private String rut;

    @Schema(description = "Cargo o puesto del empleado.", example = "Administrador")
    private String cargo;

    @Schema(description = "Identificador de la sucursal donde trabaja el empleado.", example = "3")
    private int sucursalId;
    
	public EmpleadoDTO() {
		super();
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
	public int getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}
	
	
}
