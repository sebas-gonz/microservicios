package com.pedido_perfulandia.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedido_perfulandia.config.PedidoAssembler;
import com.pedido_perfulandia.dto.PedidoDTO;
import com.pedido_perfulandia.entidad.Pedido;
import com.pedido_perfulandia.servicio.PedidoServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
	public ResponseEntity<CollectionModel<EntityModel<Pedido>>> listarPedidos(){
		List<Pedido> pedidos = servicio.pedidos();
		return pedidos == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(assembler.modelToCollection(pedidos));
	}
	
	@GetMapping("/{pedidoid}")
	@Operation(
		    summary = "Obtener un pedido especifico",
		    description = "Obtiene todos los pedidos dentro del sistema mediante una id especifica"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Pedido encontrado"),
		    @ApiResponse(
		        responseCode = "204",
		        description = "No hay pedidos."
		    )
		})
	@Parameter(
			name="pedidoid",
			description = "Id del pedido a buscar",
			required = true,
			example = "1")
	public ResponseEntity<EntityModel<Pedido>> obtenerPedido(@PathVariable("pedidoid")int pedidoId){
		Pedido pedido = servicio.pedidoById(pedidoId);
		return pedido == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(assembler.toModel(pedido));
	}
	
	@PostMapping("/")
	@Operation(
		    summary = "Crear un pedido",
		    description = "Crear un pedido con sus respectivos datos"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Pedido Creado"),
		    @ApiResponse(
		        responseCode = "400",
		        description = "Datos ingresados erroneos."
		    )
		})
	public ResponseEntity<EntityModel<Pedido>> crearPedido(@RequestBody PedidoDTO pedidoDTO){
		Pedido pedido = servicio.crearPedido(pedidoDTO);
	    return pedido == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(assembler.toModel(pedido));
	}
	
	
	@DeleteMapping("/{pedidoid}")
	@Operation(
		    summary = "Borar un pedido especifico",
		    description = "Borra un pedido segun el id"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Pedido borrado"),
		    @ApiResponse(
		        responseCode = "404",
		        description = "Pedido no encontrado."
		    )
		})
	@Parameter(
		    name = "pedidoid",
		    description = "ID del pedido que se desea eliminar",
		    required = true,
		    example = "1001"
		)
	public ResponseEntity<Pedido> eliminarPedido(@PathVariable("pedidoid")int pedidoId){
		servicio.eliminarPedido(pedidoId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{pedidoid}")
	@Operation(summary ="Editar pedido",
			   description="Editar los datos de un pedido segun la id")
		@ApiResponses(value = {
			@ApiResponse(
				responseCode = "200",
				description="Pedido editado correctamente"),
			@ApiResponse(
				responseCode = "400",
				description = "Datos erronoes al editar")		
		})
	@Parameter(
			name="pedidoid",
			description = "Id del pedido a editar",
			required = true,
			example = "1001")
	public ResponseEntity<EntityModel<Pedido>> editarPedido(@PathVariable("pedidoid")int pedidoId,@RequestBody Pedido pedidoAct){
		Pedido pedido = servicio.editarPedido(pedidoId, pedidoAct);
		return pedido == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(assembler.toModel(pedido));
	}
	
	@GetMapping("/sucursal/{sucursalid}")
	@Operation(summary = "Buscar pedidor por suscursal",
			   description = "Se buscar todos los pedidos que se realizaron en una sucursal")
	@ApiResponses(value={
		@ApiResponse(
				responseCode = "200",
				description = "Pedidos encontrados"),
		@ApiResponse(
				responseCode = "404",
				description = "Sucursal no encontrada o sucursal no tiene pedidos")
	})
	@Parameter(
			name="sucursalid",
			description = "Id de la sucursal a la que queremos ver los pedidos",
			required = true,
			example = "1001")
	public ResponseEntity<List<Pedido>> pedidoPorSucursal(@PathVariable("sucursalid")int sucursalId){
		List<Pedido> pedidos = servicio.pedidoPorSucursalId(sucursalId);
		return pedidos != null ? ResponseEntity.ok(pedidos) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/usuario/{usuarioid}")
	@Operation(summary = "Buscar pedidos realizados por el usuario",
			   description = "Traer todos los pedidos realizados, segun el id del usuario")
	@ApiResponses(value= {
			@ApiResponse(
					responseCode = "200",
					description = "Pedidos encontrados"),
			@ApiResponse(
					responseCode = "400",
					description = "No hay pedidos por el usuario o usuario no existente")
	})
	@Parameter(
			name="usuarioid",
			description = "Id del usuario al extraer los pedidos",
			required = true,
			example="1001")
	public ResponseEntity<List<Pedido>> pedidoPorUsuario(@PathVariable("usuarioid")int usuarioId){
		List<Pedido> pedidos = servicio.pedidoPorUsuarioId(usuarioId);
		return pedidos != null ? ResponseEntity.ok(pedidos) : ResponseEntity.noContent().build();
	}

}
