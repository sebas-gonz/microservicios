package com.trabajador_perfulandia.entidad;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(description = "Entidad que representa un empleado del sistema.")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Representa el id del emplado")
	private int empleadoId;
	@Schema(description = "Representa el nombre del empleado", example="Martin")
	private String nombre;
	@Schema(description = "Representa el correo del empleado", example="AguanteLaU@gmail.com")
	private String correo;
	@Schema(description = "Representa el RUT del empleado", example="12.345.678-1")
	private String rut;
	@Schema(description = "Representa el cargo quen tendra el empleado", example="Jefe de ventas")
	private String cargo;
	@Schema(description = "Representa el ID de la sucursal que pertenece el empleado", example="1")
	private int sucursalId;
	
	public Empleado() {
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
