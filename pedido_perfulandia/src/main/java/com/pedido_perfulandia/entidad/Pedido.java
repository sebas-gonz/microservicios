package com.pedido_perfulandia.entidad;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(name = "Pedido", description = "Representa un pedido realizado por un usuario en una sucursal")
public class Pedido {

    @Schema(description = "ID único del pedido", example = "1001")
    private int pedidoId;

    @Schema(description = "ID de la sucursal donde se realizó el pedido", example = "5")
    private int sucursalId;

    @Schema(description = "ID del usuario que realizó el pedido", example = "10")
    private int usuarioId;

    @Schema(description = "Estado actual del pedido (ej: PENDIENTE, EN_PREPARACIÓN, ENVIADO)", example = "PENDIENTE")
    private String estado;

    @Schema(description = "Fecha y hora en que se realizó el pedido", example = "2025-06-12T14:30:00")
    private LocalDateTime fechaPedido;

	
	public Pedido() {
		this.fechaPedido = LocalDateTime.now();
		this.estado = "pendiente";
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	
	
	
	
	
}
