package com.trabajador_perfulandia.dto;

import java.util.List;

public class BoletaDTO {
	
	private int boletaId;
	private String nombreUsuario;
	private String nombreSucursal;
	private String nombreEmpleado;
	private int usuarioId;
	private int sucursalId;
	private int empleadoId;
	private int total;
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
