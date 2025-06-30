package com.envio_perfulandia.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Entidad que representa los atributos DTO")
public class EnvioDTO {
	@Schema(description = "Representa el id del envio", example="1")
	private int envioId;
	@Schema(description = "Representa el id de la boleta", example="2")
    private int boletaId;
	@Schema(description = "Representa el id del pedido", example="1")
    private int pedidoId;
	@Schema(description = "Representa el id del usuario", example="1")
    private int usuarioId;
	@Schema(description = "Representa el id de la sucursal", example="1")
    private int sucursalId;
	@Schema(description = "Representa la direccion del envio", example="Av.Manuel V.")
    private String direccionEnvio;
	@Schema(description = "Representa el estado en el que se encuentra el pedido", example="Cancelado")
    private String estado; 
	@Schema(description = "Repreeenta la fecha de envio", example="15-06-2025")
    private LocalDateTime fechaEnvio;
	@Schema(description = "Representa la fecha de entrega del envio", example="16-06-2025")
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
