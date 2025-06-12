package com.detalle_pedido.dto;

public class DetallePedidoDTO {
	
	private int productoId;
    private int cantidad;
    private int pedidoId;
    
	public int getProductoId() {
		return productoId;
	}
	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}
	public DetallePedidoDTO() {
		super();
	}
	
}
