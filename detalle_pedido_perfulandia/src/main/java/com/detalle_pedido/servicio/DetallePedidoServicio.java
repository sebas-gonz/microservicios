package com.detalle_pedido.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.detalle_pedido.dto.DetallePedidoDTO;
import com.detalle_pedido.dto.ProductoDTO;
import com.detalle_pedido.entidad.DetallePedido;
import com.detalle_pedido.repositorio.DetallePedidoRepositorio;

@Service
public class DetallePedidoServicio {
	
	@Autowired
	private DetallePedidoRepositorio repositorio;
	
	@Autowired
	
	private RestTemplate restTemplate;
	
	public DetallePedido crearDetallePedido(DetallePedido detallepedido) {
		return repositorio.save(detallepedido);
	}
	
	public void eliminarDetallePedido(int detallePedidoId) {
		repositorio.deleteById(detallePedidoId);
	}
	
	public List<DetallePedido> detallePedidos(){
		List<DetallePedido> detallePedidos = repositorio.findAll();
		if(detallePedidos.isEmpty()) {
			return null;
		}
		return detallePedidos;
	}
	
	public DetallePedido detallePedidoById(int detallePedidoId) {
		DetallePedido detallePedido = repositorio.findById(detallePedidoId).orElse(null);
		return detallePedido;
	}
	
	public DetallePedido editarDetallePedido(int detallePedidoId, DetallePedido nuevoDetallePedido) {
		DetallePedido detallePedido = repositorio.findById(detallePedidoId).orElse(null);
		if(detallePedido == null) {
			return null;
		}
		detallePedido.setCantidad(nuevoDetallePedido.getCantidad());
		detallePedido.setNombreProducto(nuevoDetallePedido.getNombreProducto());
		detallePedido.setProductoId(nuevoDetallePedido.getProductoId());
		detallePedido.setSubtotal(nuevoDetallePedido.getSubtotal());
		
		return repositorio.save(detallePedido);
	}
	
	public List<DetallePedido> crearDetallesPedidos(List<DetallePedidoDTO> detallePedidoDTO) {
		List<DetallePedido> detallePedido = new ArrayList<>();
		
		for(DetallePedidoDTO detalleDTO : detallePedidoDTO) {
			
			String urlProducto = "http://localhost:8081/producto/" + detalleDTO.getProductoId();
			ProductoDTO producto = restTemplate.getForObject(urlProducto, ProductoDTO.class);
			
			DetallePedido detalle = new DetallePedido();
			
			detalle.setCantidad(detalleDTO.getCantidad());
			detalle.setNombreProducto(producto.getNombreProducto());
			detalle.setPedidoId(detalleDTO.getPedidoId());
			detalle.setSubtotal(producto.getPrecio() * detalleDTO.getCantidad());
			detalle.setProductoId(detalleDTO.getProductoId());
			
			detallePedido.add(detalle);
		}
		return repositorio.saveAll(detallePedido);
	}
	
	public List<DetallePedido> detallesPedidoByPedidoId(int pedidoId){
		List<DetallePedido> detalles = repositorio.findByPedidoId(pedidoId);
		if(detalles.isEmpty()) {
			return null;
		}
		return detalles;
	}
}
