package com.pedido_perfulandia.dto;

import java.util.List;

public class BoletaDTO {
	private int boletaId;
	private int usuarioId;
    private String nombreUsuario;
    private int sucursalId;
    private String nombreSucursal;
    private int empleadoId;
    private String nombreEmpleado;
    private int pedidoId;
    private int total;
    private List<DetalleBoletaDTO> detalles;

	public BoletaDTO() {
		super();
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public int getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public int getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DetalleBoletaDTO> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleBoletaDTO> detalles) {
		this.detalles = detalles;
	}

	public int getBoletaId() {
		return boletaId;
	}

	public void setBoletaId(int boletaId) {
		this.boletaId = boletaId;
	}
    
	
    
}
