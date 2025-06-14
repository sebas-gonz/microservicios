package com.pedido_perfulandia.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedido_perfulandia.config.PedidoAssembler;
import com.pedido_perfulandia.dto.PedidoDTO;
import com.pedido_perfulandia.entidad.Pedido;
import com.pedido_perfulandia.servicio.PedidoServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedido", description = "Operaciones relacionadas con el pedido")
public class PedidoControllador {
	
	@Autowired
	private PedidoServicio servicio;
	
	@Autowired
	private PedidoAssembler assembler;
	
	@GetMapping("/")
	@Operation(summary = "Obtener todos los pedidos", description = "Obtiene una lista de todos los pedidos dentro del sistema.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de envios obtenidos."),
	    @ApiResponse(responseCode = "204", description = "No hay envios dentro sistema.")
	})
	public ResponseEntity<List<Pedido>> obtenerPedidos(){
		List<Pedido> pedidos = servicio.pedidos();
		return pedidos == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(pedidos);
	}
	
	@GetMapping("/{pedidoid}")
	@Operation(
		    summary = "Obtener los pedidos especificos",
		    description = "Obtiene todos los pedidos dentro del sistema mediante una id especifica"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = ""),
		    @ApiResponse(
		        responseCode = "204",
		        description = "Datos del pedido erroneos."
		    )
		})
	public ResponseEntity<Pedido> obtenerPedido(@PathVariable("pedidoid")int pedidoId){
		Pedido pedido = servicio.pedidoById(pedidoId);
		return pedido == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(pedido);
	}
	
	@PostMapping("/")
	public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoDTO pedidoDTO){
		Pedido pedido = servicio.crearPedido(pedidoDTO);
	    return pedido == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(pedido);
	}
	
	@DeleteMapping("/{pedidoid}")
	public ResponseEntity<Void> eliminarPedido(@PathVariable("pedidodid")int pedidoId){
		servicio.eliminarPedido(pedidoId);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{pedidoid}")
	public ResponseEntity<Pedido> editarPedido(@PathVariable("pedidoid")int pedidoId,@RequestBody Pedido pedidoAct){
		Pedido pedido = servicio.editarPedido(pedidoId, pedidoAct);
		return pedido == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(pedido);
	}
	
	@GetMapping("/sucursal/{sucursalid}")
	public ResponseEntity<List<Pedido>> pedidoPorSucursal(@PathVariable("sucursalid")int sucursalId){
		List<Pedido> pedidos = servicio.pedidoPorSucursalId(sucursalId);
		return pedidos != null ? ResponseEntity.ok(pedidos) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/usuario/{usuarioid}")
	public ResponseEntity<List<Pedido>> pedidoPorUsuario(@PathVariable("usuarioid")int usuarioId){
		List<Pedido> pedidos = servicio.pedidoPorUsuarioId(usuarioId);
		return pedidos != null ? ResponseEntity.ok(pedidos) : ResponseEntity.noContent().build();
	}
}
