package com.pedido_perfulandia.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "PedidoDTO", description = "DTO que representa un pedido realizado por un usuario en una sucursal, con sus detalles")
public class PedidoDTO {

    @Schema(description = "ID único del pedido", example = "1001")
    private int pedidoId;

    @Schema(description = "ID del usuario que realiza el pedido", example = "10")
    private int usuarioId;

    @Schema(description = "ID de la sucursal donde se realiza el pedido", example = "5")
    private int sucursalId;

    @Schema(description = "Estado actual del pedido (ej: PENDIENTE, EN_PREPARACIÓN, ENVIADO)", example = "PENDIENTE")
    private String estado;

    @Schema(description = "Fecha y hora en que se realizó el pedido", example = "2025-06-12T14:30:00")
    private LocalDateTime fechaPedido;

    @Schema(description = "Lista con los detalles del pedido")
    private List<DetallePedidoDTO> detalles;
	public List<DetallePedidoDTO> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetallePedidoDTO> detalles) {
		this.detalles = detalles;
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
	public PedidoDTO() {
		super();
	}
	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}
    
	
    
}
