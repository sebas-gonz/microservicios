package com.detalle_pedido.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.detalle_pedido.config.DetallePedidoAssembler;
import com.detalle_pedido.dto.DetallePedidoDTO;
import com.detalle_pedido.entidad.DetallePedido;
import com.detalle_pedido.servicio.DetallePedidoServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/detalle_pedido")
@Tag(name = "Detalle de Pedido", description = "Operaciones relacionadas con los detalles de los pedidos")
public class DetallePedidoControllador {
	
	@Autowired
	private DetallePedidoServicio servicio;
	
	@Autowired
	private DetallePedidoAssembler assembler;
	
	@GetMapping("/")
	@Operation(
	    summary = "Obtener todos los detalles de pedidos",
	    description = "Retorna una lista con todos los detalles de pedidos registrados en el sistema"
	)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de detalles de pedidos obtenida exitosamente"),
	    @ApiResponse(responseCode = "204", description = "No hay detalles de pedidos disponibles")
	})
	public ResponseEntity<CollectionModel<EntityModel<DetallePedido>>> obtenerDetallesPedidos(){
		List<DetallePedido> detallesPedidos = servicio.detallePedidos();
		
		return detallesPedidos == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(assembler.modelToCollection(detallesPedidos));
	}
	
	@GetMapping("/{detallepedidoid}")
	@Operation(
	    summary = "Obtener detalle de pedido por ID",
	    description = "Retorna un detalle de pedido específico según su ID"
	)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Detalle de pedido encontrado"),
	    @ApiResponse(responseCode = "204", description = "No se encontró detalle de pedido con ese ID")
	})
	@Parameter(description = "ID del detalle de pedido", example = "123") 
	public ResponseEntity<EntityModel<DetallePedido>> DetallePedidoById(int detallePedidoId){
		DetallePedido detallePedido = servicio.detallePedidoById(detallePedidoId);
		
		return detallePedido == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(assembler.toModel(detallePedido));
	}
	
	@PostMapping("/")
	@Operation(
	    summary = "Crear un nuevo detalle de pedido",
	    description = "Crea y retorna un nuevo detalle de pedido a partir de los datos proporcionados"
	)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Detalle de pedido creado exitosamente"),
	    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
	})
	public ResponseEntity<DetallePedido> crearDetallePedido(@RequestBody DetallePedido detallePedido){
		DetallePedido nuevo = servicio.crearDetallePedido(detallePedido);
		return ResponseEntity.ok(nuevo);
	}
	
	
	@PutMapping("/{detallepedidoid}")
	@Operation(
	    summary = "Editar un detalle de pedido existente",
	    description = "Actualiza el detalle de pedido identificado por su ID con la información proporcionada"
	)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Detalle de pedido actualizado correctamente"),
	    @ApiResponse(responseCode = "204", description = "No se encontró detalle de pedido con ese ID"),
	    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
	})
	@Parameter(description = "ID del detalle de pedido a editar", example = "123")
	public ResponseEntity<EntityModel<DetallePedido>> editarDetallePedido(@PathVariable("detallepedidodid")int detallePedidoId,
			@RequestBody DetallePedido nuevoDetallePedido){
		
		DetallePedido detallePedido = servicio.editarDetallePedido(detallePedidoId, nuevoDetallePedido);
		
		return detallePedido == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(assembler.toModel(detallePedido));
	}
	
	@PostMapping("/pedido")
	@Operation(
	    summary = "Crear múltiples detalles de pedido",
	    description = "Permite crear varios detalles de pedido a partir de una lista de DetallePedidoDTO"
	)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Detalles de pedido creados exitosamente"),
	    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
	})
	public ResponseEntity<Void> crearDetallesPedido(@RequestBody List<DetallePedidoDTO> detallesPedido){
		servicio.crearDetallesPedidos(detallesPedido);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/pedido/{pedidoid}")
	@Operation(
		    summary = "Obtener una lista de detalle pedidos por el id del pedido",
		    description = "Obtiene la lista de los detalles pedidos de un pedido por su ID"
		)
		@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Detalles de pedido creados exitosamente"),
		    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
		})
	@Parameter(description = "ID del pedido a buscar", example = "123")
	public ResponseEntity<List<DetallePedido>> detallesPorPedido(@PathVariable("pedidoid")int pedidoId){
		List<DetallePedido> detalles = servicio.detallesPedidoByPedidoId(pedidoId);
		return detalles == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(detalles);
	}
}
