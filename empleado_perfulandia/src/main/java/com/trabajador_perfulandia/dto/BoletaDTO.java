package com.trabajador_perfulandia.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Representa los atributos de este esquema")
public class BoletaDTO {
	@Schema(description = "Representa el ID de la Boleta", example="1")
	private int boletaId;
	@Schema(description = "Representa el nombre del usuario", example="Andres")
	private String nombreUsuario;
	@Schema(description = "Representa el nombre de la sucursal", example="Sucursal Alameda")
	private String nombreSucursal;
	@Schema(description = "Representa nombre del empleado que realizo la venta", example="Andrea")
	private String nombreEmpleado;
	@Schema(description = "Representa el Id del usuario", example="1")
	private int usuarioId;
	@Schema(description = "Representa el id de la sucursal", example="1")
	private int sucursalId;
	@Schema(description = "Representa el id del empleado", example="1")
	private int empleadoId;
	@Schema(description = "Representa el total de la venta", example="$100")
	private int total;
	@Schema(description = "Representa la lista del detalle boletaDTO")
	private List<DetalleBoletaDTO> detalleBoletas;
	
	
	public BoletaDTO() {
		super();
	}
	public int getBoletaId() {
		return boletaId;
	}

	public void setBoletaId(int boleta_id) {
		this.boletaId = boleta_id;
	}


	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuario_id) {
		this.usuarioId = usuario_id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursal_id) {
		this.sucursalId = sucursal_id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	public List<DetalleBoletaDTO> getDetalleBoletas() {
		return detalleBoletas;
	}
	public void setDetalleBoletas(List<DetalleBoletaDTO> detalleBoletas) {
		this.detalleBoletas = detalleBoletas;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public int getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}
	
	
}
