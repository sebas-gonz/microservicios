package com.envio_perfulandia.controllador;

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
import org.springframework.web.client.RestTemplate;
import com.envio_perfulandia.dto.InventarioDTO;
import com.envio_perfulandia.entidad.Inventario;
import com.envio_perfulandia.servicio.InventarioServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/inventario")
@Tag(name = "inventariio", description = "Operaciones relacionados con el inventario")
public class InventarioControllador {

    private final RestTemplate restTemplate;
	@Autowired
	private InventarioServicio servicio;


    InventarioControllador(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	
	@GetMapping("/")
	@Operation(summary = "Obtener todos los inventarios", description = "Obtiene una lista de todos los inventarios.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de inventarios obtenida."),
	    @ApiResponse(responseCode = "204", description = "No hay inventario registrados.")
	})
	public ResponseEntity<List<Inventario>> Inventarios(){
		List<Inventario> inventarios = servicio.obtenerInventarios();
		return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{inventarioid}")
	@Operation(
		    summary = "Obtener un inventario por ID",
		    description = "Retorna al inventario específico según su ID. Si no se encuentra, retorna código 404."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "inventario encontrado exitosamente."),
		    @ApiResponse(
		        responseCode = "404",
		        description = "inventario no encontrado"
		    )
		})
		@Parameter(
		    name = "inventarioid",
		    description = "ID del inventario a obtener",
		    required = true,
		    example = "1"
		)
	public ResponseEntity<InventarioDTO> Inventario(@PathVariable("inventarioid")int inventarioId){
		InventarioDTO inventario = servicio.inventarioById(inventarioId);
		return inventario != null ? ResponseEntity.ok(inventario) : ResponseEntity.noContent().build();
	}
	
	@PostMapping("/")
	@Operation(
		    summary = "Crear un nuevo inventario",
		    description = "Registra un nuevo inventario"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "inventario creado"),
		    @ApiResponse(
		        responseCode = "400",
		        description = "Datos del inventario erroneos."
		        )
		})
	public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario){
		return ResponseEntity.ok(servicio.crearInventario(inventario));
	}
	
	@DeleteMapping("/{inventarioid}")
	@Operation(
		    summary = "Eliminar un inventario por ID",
		    description = "Elimina el inventario existente por su ID. Retorna 204 si se elimina correctamente, o 404 si no se encuentra el inventario."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "204",
		        description = "inventario eliminado."
		    ),
		    @ApiResponse(
		        responseCode = "404",
		        description = "inventario no encontrado."
		    )
		})
		@Parameter(
		    name = "inventarioid",
		    description = "ID del inventario que se desea eliminar",
		    required = true,
		    example = "1"
		    )
	public ResponseEntity<Void> eliminarInventario(@PathVariable("inventarioid")int inventarioId){
		servicio.eliminarInventario(inventarioId);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{inventarioid}")
	@Operation(summary = "Actualizar un inventarioi", description = "Actualiza un inventarioi existente mediante un id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "inventarioi actualizado correctamente."),
			@ApiResponse(responseCode = "404", description = "inventarioi no encontrado")
	})
	public ResponseEntity<Inventario> editarInventario(@PathVariable("inventarioid")int inventarioId,Inventario inventarioAct){
		Inventario inventario = servicio.editarInventario(inventarioId, inventarioAct);
		return inventario != null ? ResponseEntity.ok(inventario) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/sucursal/{sucursalid}")
	@Operation(summary = "Busca inventario de una suscursal",
	   description = "se retorna todos los inventarios de una sucursal")
	@ApiResponses(value={
	@ApiResponse(
			responseCode = "200",
			description = "inventarios encontrados"),
	@ApiResponse(
			responseCode = "404",
			description = "inventarios no encontrados o sucursal no registra inventarios")
	})
	@Parameter(
		name="sucursalid",
		description = "Id de la sucursal a la que queremos ver los inventarios",
		required = true,
		example = "1001")
	public ResponseEntity<List<Inventario>> inventarioPorSucursal(@PathVariable("sucursalid")int sucursalId){
		List<Inventario> inventarios = servicio.inventarioBySucursalId(sucursalId);
		return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/producto/{productoid}")
	@Operation(summary = "Busca inventario de un producto ",
	   description = "se retorna todos los inventarios de un producto")
@ApiResponses(value={
@ApiResponse(
		responseCode = "200",
		description = "inventario obtenido con exito"),
@ApiResponse(
		responseCode = "404",
		description = "producto no encontrado o  no registrado en el inventario")
})
@Parameter(
	name="productoid",
	description = "Id del producto al que queremos ver los inventarios",
	required = true,
	example = "1001")
	public ResponseEntity<List<Inventario>> inventarioPorProducto(@PathVariable("productoid")int productoId){
		List<Inventario> inventarios = servicio.inventarioByProductoId(productoId);
		return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{sucursalid}/{productoid}")
	@Operation(summary = "Busca inventario de un producto y sucursal dirigida ",
	   description = "se retorna todos los inventarios de un producto y sucursal ")
@ApiResponses(value={
@ApiResponse(
		responseCode = "200",
		description = "inventarios del producto obtenido con exito con sucursal"),
@ApiResponse(
		responseCode = "404",
		description = "producto no encontrado en la sucursal o  no registrado en el inventario de esa sucursal")
})
@Parameter(
	name="productoid",
	description = "Id del producto al cual que queremos ver la sucursal cuando se entra al inventarios",
	required = true,
	example = "1001")
	public ResponseEntity<List<Inventario>> inventariosPorSucursalYProducto(@PathVariable("sucursalid")int sucursalId,@PathVariable("productoid")int productoId){
		List<Inventario> inventarios = servicio.inventariosPorSucursalYProductos(sucursalId, productoId);
		return inventarios == null ? ResponseEntity.notFound().build() : ResponseEntity.ok( inventarios);
	}
	
	@PostMapping("/pedido")
	@Operation(
		    summary = "retorna una lista de los inventarios actualizados por un pedido",
		    description = "al crear un pedido se actualizan los inventarios de la sucursal a la que se le a echo un pedido , restando la cantidad de un producto solicitado en el pedido. Cada pedido incluye sus productos, cantidades, sucursal, estado y fecha del pedido."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Lista de pedidos obtenida correctamente."),
		    @ApiResponse(
		        responseCode = "404",
		        description = "No se encontró un inventario con el ID de la sucrusal especificado o no ."
		    )
		})
	public ResponseEntity<List<Inventario>> actualizarInventario(@RequestBody List<InventarioDTO> inventariosDTO){
		
		List<Inventario> inventarios = servicio.editarInventario(inventariosDTO);
		return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.notFound().build(); 
	}
}
