package com.Sucursal_perfulandia.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa un pedido realizado por un usuario")
public class PedidoDTO {

    @Schema(description = "ID único del pedido", example = "1")
    private int pedidoId;

    @Schema(description = "ID del usuario que realizó el pedido", example = "3")
    private int usuarioId;
    
    @Schema(description = "Informacion del usuario asociado")
    private UsuarioDTO usuario;

    @Schema(description = "ID de la sucursal donde se registró el pedido", example = "2")
    private int sucursalId;
    @Schema(description = "Estado actual del pedido", example = "pendiente")
    private String estado;

    @Schema(description = "Fecha y hora en que se realizó el pedido", example = "2025-06-05T22:34:36")
    private LocalDateTime fechaPedido;

    @Schema(description = "Lista de detalles del pedido, incluyendo productos y cantidades")
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
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
    
	
    
}
