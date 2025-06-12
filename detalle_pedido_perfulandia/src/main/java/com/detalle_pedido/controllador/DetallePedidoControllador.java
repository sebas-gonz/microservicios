package com.detalle_pedido.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.detalle_pedido.dto.DetallePedidoDTO;
import com.detalle_pedido.entidad.DetallePedido;
import com.detalle_pedido.servicio.DetallePedidoServicio;

@RestController
@RequestMapping("/detalle_pedido")
public class DetallePedidoControllador {
	
	@Autowired
	private DetallePedidoServicio servicio;
	
	@GetMapping("/")
	public ResponseEntity<List<DetallePedido>> obtenerDetallesPedidos(){
		List<DetallePedido> detallesPedidos = servicio.detallePedidos();
		
		return detallesPedidos == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(detallesPedidos);
	}
	
	@GetMapping("/{detallepedidoid}")
	public ResponseEntity<DetallePedido> DetallePedidoById(int detallePedidoId){
		DetallePedido detallePedido = servicio.detallePedidoById(detallePedidoId);
		
		return detallePedido == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(detallePedido);
	}
	
	@PostMapping("/")
	public ResponseEntity<DetallePedido> crearDetallePedido(@RequestBody DetallePedido detallePedido){
		
		return ResponseEntity.ok(servicio.crearDetallePedido(detallePedido));
	}
	
	@PostMapping("/{detallepedidoid}")
	public ResponseEntity<DetallePedido> editarDetallePedido(@PathVariable("detallepedidodid")int detallePedidoId,
			@RequestBody DetallePedido nuevoDetallePedido){
		
		DetallePedido detallePedido = servicio.editarDetallePedido(detallePedidoId, nuevoDetallePedido);
		
		return detallePedido == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(detallePedido);
	}
	
	@PostMapping("/pedido")
	public ResponseEntity<Void> crearDetallesPedido(@RequestBody List<DetallePedidoDTO> detallesPedido){
		servicio.crearDetallesPedidos(detallesPedido);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/pedido/{pedidoid}")
	public ResponseEntity<List<DetallePedido>> detallesPorPedido(@PathVariable("pedidoid")int pedidoId){
		List<DetallePedido> detalles = servicio.detallesPedidoByPedidoId(pedidoId);
		return detalles == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(detalles);
	}
}
