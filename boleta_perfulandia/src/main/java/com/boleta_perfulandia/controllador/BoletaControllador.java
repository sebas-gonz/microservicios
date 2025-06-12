package com.boleta_perfulandia.controllador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boleta_perfulandia.dto.BoletaDTO;
import com.boleta_perfulandia.dto.DetalleBoletaDTO;
import com.boleta_perfulandia.entidades.Boleta;
import com.boleta_perfulandia.servicio.BoletaServicio;

@RestController
@RequestMapping("/boleta")
public class BoletaControllador {
	@Autowired
	private BoletaServicio boletaServicio;
	
	@GetMapping("/")
	public ResponseEntity<List<Boleta>> obtenerBoletas(){
		List<Boleta> boletas = boletaServicio.obtenerBoletas();
		if(boletas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(boletas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Boleta> obtenerBoleta(@PathVariable("id") int id){
		if( boletaServicio.boletaById(id).equals(null)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(boletaServicio.boletaById(id));
	}
	
	@PostMapping("/")
	public ResponseEntity<Boleta> crearBoleta(@RequestBody Boleta boleta){
		return ResponseEntity.ok(boletaServicio.crearBoleta(boleta));
	}
	
	@PostMapping("/pedido")
	public ResponseEntity<Boleta> crearBoleta(@RequestBody BoletaDTO boletaDTO){
		return ResponseEntity.ok(boletaServicio.crearBoletaPorPedido(boletaDTO));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boleta> eliminarBoleta(@PathVariable("id") int id){
		if(boletaServicio.boletaById(id).equals(null)) {
			return ResponseEntity.noContent().build();
		}
		boletaServicio.borrarBoletaById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/actualizar_total")
	public ResponseEntity<Void> actualizarTotal(@RequestBody Map<String,Object> detalleBoleta){
		boletaServicio.actualizarBoleta(detalleBoleta);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/{id}/recalcular")
    public ResponseEntity<Void> recalcularTotalBoleta(@PathVariable("id") int id) {
        boletaServicio.recalcularTotal(id);
        return ResponseEntity.ok().build();
    }
	
	@PostMapping("/{id}")
	public ResponseEntity<Boleta> editarBoleta(@PathVariable("id") int id,@RequestBody Boleta boleta){
		if(boletaServicio.boletaById(id).equals(null)) {
			return ResponseEntity.noContent().build();
		}
		boletaServicio.editarBoletaById(id, boleta);
		return ResponseEntity.ok(boleta);
	}
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<List<DetalleBoletaDTO>> obtenerDetalles(@PathVariable("id") int id){
		List<DetalleBoletaDTO> detalles = boletaServicio.obtenerDetallesBoleta(id);
		if(detalles.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detalles);
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<BoletaDTO>> obtenerBoletasUsuarios(@PathVariable("id") int id){
		List<BoletaDTO> boletas = boletaServicio.obtenerBoletaUsuario(id);
		if(boletas == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(boletas);
	}
	
	@GetMapping("/sucursal/{sucursalid}")
	public ResponseEntity<List<BoletaDTO>> boletasSucursal(@PathVariable("sucursalid") int sucursalId){
		List<BoletaDTO> boletas = boletaServicio.boletaSucursal(sucursalId);
		return boletas == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(boletas);
	}
	@GetMapping("/sucursal/{sucursalid}/usuario")
	public ResponseEntity<List<Integer>> usuariosSucursal(@PathVariable("sucursalid") int sucursalId){
		List<Integer> usuariosId = boletaServicio.usuariosSucursal(sucursalId);
		return usuariosId == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuariosId);
	}
	
	@GetMapping("/empleado/{empleadoid}")
	public ResponseEntity<List<Boleta>> boletasEmpleado(@PathVariable("empleadoid")int empleadoId){
		List<Boleta> boletas = boletaServicio.boletasEmpleado(empleadoId);
		return boletas == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(boletas);
	}
	
	@GetMapping("/pedido/{pedidoid}")
	public ResponseEntity<Boleta> boletaPorPedidodId(@PathVariable("pedidoid")int pedidoId){
		Boleta boleta = boletaServicio.boletaByPedidoId(pedidoId);
		return boleta != null ? ResponseEntity.ok(boleta) : ResponseEntity.noContent().build();
	}
}
