package com.pedido_perfulandia.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "EmpleadoDTO", description = "DTO que representa la información de un empleado y sus boletas asociadas")
public class EmpleadoDTO {

    @Schema(description = "ID único del empleado", example = "101")
    private int empleadoId;

    @Schema(description = "Nombre completo del empleado", example = "Carlos Martínez")
    private String nombre;

    @Schema(description = "Correo electrónico del empleado", example = "carlos.martinez@empresa.com")
    private String correo;

    @Schema(description = "RUT del empleado", example = "12.345.678-9")
    private String rut;

    @Schema(description = "Cargo o puesto del empleado", example = "Vendedor")
    private String cargo;

    @Schema(description = "ID de la sucursal a la que pertenece el empleado", example = "5")
    private int sucursalId;

    @Schema(description = "Lista de boletas asociadas al empleado")
    private List<BoletaDTO> boletas;
    
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
	public List<BoletaDTO> getBoletas() {
		return boletas;
	}
	public void setBoletas(List<BoletaDTO> boletas) {
		this.boletas = boletas;
	}
	
}
