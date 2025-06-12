package com.pedido_perfulandia.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
	private int pedidoId;
	private int usuarioId;
    private int sucursalId;
    private String estado;
	private LocalDateTime fechaPedido;
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
