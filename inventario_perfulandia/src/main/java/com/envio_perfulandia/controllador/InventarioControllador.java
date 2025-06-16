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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/inventario")
@Tag(name = "inventario", description = "Operaciones relacionados con el inventario")
public class InventarioControllador {

    private final RestTemplate restTemplate;
	@Autowired
	private InventarioServicio servicio;


    InventarioControllador(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	
	@GetMapping("/")
	@Operation(summary = "Obtener todos los invc", description = "Obtiene una lista de todos los usuarios.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida."),
	    @ApiResponse(responseCode = "204", description = "No hay usuarios registrados.")
	})
	public ResponseEntity<List<Inventario>> Inventarios(){
		List<Inventario> inventarios = servicio.obtenerInventarios();
		return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{inventarioid}")
	public ResponseEntity<InventarioDTO> Inventario(@PathVariable("inventarioid")int inventarioId){
		InventarioDTO inventario = servicio.inventarioById(inventarioId);
		return inventario != null ? ResponseEntity.ok(inventario) : ResponseEntity.noContent().build();
	}
	
	@PostMapping("/")
	public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario){
		return ResponseEntity.ok(servicio.crearInventario(inventario));
	}
	
	@DeleteMapping("/{inventarioid}")
	public ResponseEntity<Void> eliminarInventario(@PathVariable("inventarioid")int inventarioId){
		servicio.eliminarInventario(inventarioId);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{inventarioid}")
	public ResponseEntity<Inventario> editarInventario(@PathVariable("inventarioid")int inventarioId,Inventario inventarioAct){
		Inventario inventario = servicio.editarInventario(inventarioId, inventarioAct);
		return inventario != null ? ResponseEntity.ok(inventario) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/sucursal/{sucursalid}")
	public ResponseEntity<List<Inventario>> inventarioPorSucursal(@PathVariable("sucursalid")int sucursalId){
		List<Inventario> inventarios = servicio.inventarioBySucursalId(sucursalId);
		return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/producto/{productoid}")
	public ResponseEntity<List<Inventario>> inventarioPorProducto(@PathVariable("productoid")int productoId){
		List<Inventario> inventarios = servicio.inventarioByProductoId(productoId);
		return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{sucursalid}/{productoid}")
	public ResponseEntity<List<Inventario>> inventariosPorSucursalYProducto(@PathVariable("sucursalid")int sucursalId,@PathVariable("productoid")int productoId){
		List<Inventario> inventarios = servicio.inventariosPorSucursalYProductos(sucursalId, productoId);
		return inventarios == null ? ResponseEntity.notFound().build() : ResponseEntity.ok( inventarios);
	}
	
	@PostMapping("/pedido")
	public ResponseEntity<List<Inventario>> actualizarInventario(@RequestBody List<InventarioDTO> inventariosDTO){
		
		List<Inventario> inventarios = servicio.editarInventario(inventariosDTO);
		return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.notFound().build(); 
	}
}
