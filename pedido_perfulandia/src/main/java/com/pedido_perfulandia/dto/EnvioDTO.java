package com.pedido_perfulandia.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "EnvioDTO", description = "DTO que representa la información de un envío asociado a una boleta y pedido")
public class EnvioDTO {

    @Schema(description = "ID único del envío", example = "9001")
    private int envioId;

    @Schema(description = "ID de la boleta relacionada al envío", example = "5001")
    private int boletaId;

    @Schema(description = "ID del pedido relacionado al envío", example = "120")
    private int pedidoId;

    @Schema(description = "ID del usuario que solicitó el envío", example = "15")
    private int usuarioId;

    @Schema(description = "ID de la sucursal desde donde se envía", example = "3")
    private int sucursalId;

    @Schema(description = "Dirección donde se realizará la entrega", example = "Av. Libertador 1234, Santiago")
    private String direccionEnvio;

    @Schema(description = "Estado actual del envío (ej: PENDIENTE, EN_CAMINO, ENTREGADO)", example = "EN_CAMINO")
    private String estado;

    @Schema(description = "Fecha y hora en que se realizó el envío", example = "2025-06-12T09:00:00")
    private LocalDateTime fechaEnvio;

    @Schema(description = "Fecha y hora estimada o real de entrega", example = "2025-06-14T18:30:00")
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
