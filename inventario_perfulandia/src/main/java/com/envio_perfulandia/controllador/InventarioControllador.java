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

import com.envio_perfulandia.dto.InventarioDTO;
import com.envio_perfulandia.entidad.Inventario;
import com.envio_perfulandia.servicio.InventarioServicio;

@RestController
@RequestMapping("/inventario")
public class InventarioControllador {
	@Autowired
	private InventarioServicio servicio;
	
	
	@GetMapping("/")
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
}
