package com.envio_perfulandia.dto;

import java.time.LocalDateTime;

public class EnvioDTO {
	private int envioId;
    private int boletaId;
    private int pedidoId;
    private int usuarioId;
    private int sucursalId;
    private String direccionEnvio;
    private String estado; 
    private LocalDateTime fechaEnvio;
    private LocalDateTime fechaEntrega;
    
	public EnvioDTO() {
	}

	public int getEnvioId() {
		return envioId;
	}

	public void setEnvioId(int envioId) {
		this.envioId = envioId;
	}

	public int getBoletaId() {
		return boletaId;
	}

	public void setBoletaId(int boletaId) {
		this.boletaId = boletaId;
	}

	public int getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(LocalDateTime fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
    
}
