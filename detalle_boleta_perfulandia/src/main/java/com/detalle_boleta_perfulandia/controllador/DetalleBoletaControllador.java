package com.detalle_boleta_perfulandia.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.detalle_boleta_perfulandia.dto.DetalleBoletaDTO;
import com.detalle_boleta_perfulandia.entidades.DetalleBoleta;
import com.detalle_boleta_perfulandia.servicio.DetalleBoletaServicio;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/detalle_boleta")
@Tag(name = "Detalle boleta", description = "Operaciones relacionadas con el detalle de las boletas")
public class DetalleBoletaControllador {
	
	@Autowired
	private DetalleBoletaServicio detalleBoletaServicio;
	
	
	@GetMapping("/")
	public ResponseEntity<List<DetalleBoleta>> obtenerDetalleBoleta(){
		if(detalleBoletaServicio.obtenerDetalleBoletas().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detalleBoletaServicio.obtenerDetalleBoletas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalleBoleta> obtenerBoletaPorId(@PathVariable("id")int id){
		DetalleBoleta detalleBoleta = detalleBoletaServicio.obtenerDetalleBoletaById(id);
		if(detalleBoleta == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detalleBoleta);
	}
	
	@PostMapping("/")
	public ResponseEntity<DetalleBoleta> crearDetalleBoleta(@RequestBody DetalleBoleta detalleBoleta){
		return ResponseEntity.ok(detalleBoletaServicio.crearDetalleBoleta(detalleBoleta));
	}
	
	@PostMapping("/pedido")
	public ResponseEntity<List<DetalleBoleta>> crearDetalleBoleta(@RequestBody List<DetalleBoletaDTO> detallesBoletaDTO){
		List<DetalleBoleta> detalleBoleta = detalleBoletaServicio.crearDetalleBoleta(detallesBoletaDTO);
		if(detalleBoleta.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detalleBoleta);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<DetalleBoleta> actualizarDetalleBoleta(@RequestBody DetalleBoleta detalleBoletaActualizado,@PathVariable("id")int id){
		DetalleBoleta detalleBoleta = detalleBoletaServicio.obtenerDetalleBoletaById(id);
		if(detalleBoleta == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detalleBoletaServicio.editarDetalleBoletaById(id, detalleBoletaActualizado));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DetalleBoleta> eliminarDetalleBoleta(@PathVariable("id") int id){
		detalleBoletaServicio.eliminarDetalleBoletaById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/boleta/{id}")
	public ResponseEntity<List<DetalleBoleta>> obtenerDetalleBoletaByIdBoleta(@PathVariable("id")int id){
		List<DetalleBoleta> detalleBoletas = detalleBoletaServicio.obtenerDetalleBoletasByIdBoleta(id);
		if(detalleBoletas == null) {
			return ResponseEntity.noContent().build();
		}	
		return ResponseEntity.ok(detalleBoletas);
	}
	
	
	@GetMapping("/producto/{productoid}")
	public ResponseEntity<List<DetalleBoleta>> boletasProductoId(@PathVariable("productoid")int productoId){
		List<DetalleBoleta> detalleBoletas = detalleBoletaServicio.boletasProducto(productoId);
		return detalleBoletas == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(detalleBoletas);
	}
}
