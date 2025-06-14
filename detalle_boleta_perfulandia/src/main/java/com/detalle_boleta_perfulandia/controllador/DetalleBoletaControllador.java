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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/detalle_boleta")
@Tag(name = "Detalle boleta", description = "Operaciones relacionadas con el detalle de las boletas")
public class DetalleBoletaControllador {
	
	@Autowired
	private DetalleBoletaServicio detalleBoletaServicio;
	
	
	@GetMapping("/")
	@Operation(summary = "Obtener todos los detalle boletas", description = "Obtiene una lista de los detalle boletas.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de detalle boletas."),
	    @ApiResponse(responseCode = "204", description = "No hay detalle boletas registradass.")
	})
	public ResponseEntity<List<DetalleBoleta>> obtenerDetalleBoleta(){
		if(detalleBoletaServicio.obtenerDetalleBoletas().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detalleBoletaServicio.obtenerDetalleBoletas());
	}
	
	@GetMapping("/{id}")
	@Operation(
		    summary = "Obtener detalle boleta por ID",
		    description = "Retorna un detalle boleta específico según su ID. Si no se encuentra, retorna código 404."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "detalle boleta encontrado exitosamente."),
		    @ApiResponse(
		        responseCode = "204",
		        description = "detalle boleta no encontrado"
		    )
		})
		@Parameter(
		    name = "id",
		    description = "ID de detalle boleta a obtener",
		    required = true,
		    example = "1"
		)
	public ResponseEntity<DetalleBoleta> obtenerBoletaPorId(@PathVariable("id")int id){
		DetalleBoleta detalleBoleta = detalleBoletaServicio.obtenerDetalleBoletaById(id);
		if(detalleBoleta == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detalleBoleta);
	}
	
	@PostMapping("/")
	@Operation(
		    summary = "Crear una nueva detalle boleta",
		    description = "Registra una nueva detalle boleta"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "detalle boleta creada"),
		    @ApiResponse(
		        responseCode = "500",
		        description = "Datos de detalle boleta erroneas."
		    )
		})
	public ResponseEntity<DetalleBoleta> crearDetalleBoleta(@RequestBody DetalleBoleta detalleBoleta){
		return ResponseEntity.ok(detalleBoletaServicio.crearDetalleBoleta(detalleBoleta));
	}
	
	@PostMapping("/pedido")
	@Operation(
		    summary = "Crear una nueva  detalle boleta",
		    description = "Registra una nueva detalle boleta cuando se crea un nuevo pedido"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "detalle boleta creada"),
		    @ApiResponse(
		        responseCode = "500",
		        description = "Datos de detalle boleta erroneas."
		    )
		})
	public ResponseEntity<List<DetalleBoleta>> crearDetalleBoleta(@RequestBody List<DetalleBoletaDTO> detallesBoletaDTO){
		List<DetalleBoleta> detalleBoleta = detalleBoletaServicio.crearDetalleBoleta(detallesBoletaDTO);
		if(detalleBoleta.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detalleBoleta);
	}
	
	@PostMapping("/{id}")
	@Operation(
		    summary = "actualiza una detalle boleta",
		    description = "actualiza una nueva  detalle boleta ya existente"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "detalle boleta editada"),
		    @ApiResponse(
		        responseCode = "204",
		        description = "detalle boleta no encontrada."
		    )
		})
	public ResponseEntity<DetalleBoleta> actualizarDetalleBoleta(@RequestBody DetalleBoleta detalleBoletaActualizado,@PathVariable("id")int id){
		DetalleBoleta detalleBoleta = detalleBoletaServicio.obtenerDetalleBoletaById(id);
		if(detalleBoleta == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detalleBoletaServicio.editarDetalleBoletaById(id, detalleBoletaActualizado));
	}
	
	@DeleteMapping("/{id}")
	@Operation(
		    summary = "Eliminar una detalle boleta por ID",
		    description = "Elimina una detalle boleta existente por su ID. Retorna 204 si se elimina correctamente, o 404 si no se encuentra el usuario."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "204",
		        description = "detalle boleta eliminada."
		    ),
		    @ApiResponse(
		        responseCode = "204",
		        description = "detalle boleta no encontrada."
		    )
		})
		@Parameter(
		    name = "id",
		    description = "ID de detalle boleta que se desea eliminar",
		    required = true,
		    example = "1"
		)
	public ResponseEntity<DetalleBoleta> eliminarDetalleBoleta(@PathVariable("id") int id){
		detalleBoletaServicio.eliminarDetalleBoletaById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/boleta/{id}")
	@Operation(
		    summary = "se obtiene una detalle boleta segun la id",
		    description = "se obtiene una detalle boleta segun la id"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "detalle boleta encontrada"),
		    @ApiResponse(
		        responseCode = "204",
		        description = "detalle boleta no encontrada."
		    )
		})
	public ResponseEntity<List<DetalleBoleta>> obtenerDetalleBoletaByIdBoleta(@PathVariable("id")int id){
		List<DetalleBoleta> detalleBoletas = detalleBoletaServicio.obtenerDetalleBoletasByIdBoleta(id);
		if(detalleBoletas == null) {
			return ResponseEntity.noContent().build();
		}	
		return ResponseEntity.ok(detalleBoletas);
	}
	
	
	@GetMapping("/producto/{productoid}")
	@Operation(
		    summary = "se obtiene una detalle boleta segun la id de producto",
		    description = "se obtiene una detalle boleta segun la id de producto"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "detalle boleta encontrada"),
		    @ApiResponse(
		        responseCode = "204",
		        description = "detalle boleta no encontrada."
		    )
		})
	public ResponseEntity<List<DetalleBoleta>> boletasProductoId(@PathVariable("productoid")int productoId){
		List<DetalleBoleta> detalleBoletas = detalleBoletaServicio.boletasProducto(productoId);
		return detalleBoletas == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(detalleBoletas);
	}
}
