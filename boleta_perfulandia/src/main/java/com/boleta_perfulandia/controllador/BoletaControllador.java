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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/boleta")
public class BoletaControllador {
	@Autowired
	private BoletaServicio boletaServicio;
	
	@GetMapping("/")
	@Operation(summary = "Obtener todas las boletas", description = "Obtiene una lista das las boletas.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de boletas."),
	    @ApiResponse(responseCode = "500", description = "No hay boletas registradas.")
	})
	public ResponseEntity<List<Boleta>> obtenerBoletas(){
		List<Boleta> boletas = boletaServicio.obtenerBoletas();
		if(boletas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(boletas);
	}
	
	@GetMapping("/{id}")
	@Operation(
		    summary = "Obtener boleta por ID",
		    description = "Retorna una boleta específico según su ID. Si no se encuentra, retorna código 404."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "boleta encontrado exitosamente."),
		    @ApiResponse(
		        responseCode = "204",
		        description = "boleta no encontrado"
		    )
		})
		@Parameter(
		    name = "id",
		    description = "ID dela boleta a obtener",
		    required = true,
		    example = "1"
		)
	public ResponseEntity<Boleta> obtenerBoleta(@PathVariable("id") int id){
		if( boletaServicio.boletaById(id).equals(null)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(boletaServicio.boletaById(id));
	}
	
	@PostMapping("/")
	@Operation(
		    summary = "Crear una nueva boleta",
		    description = "Registra una nueva boleta"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "boleta creada"),
		    @ApiResponse(
		        responseCode = "500",
		        description = "Datos dela boleta erroneas."
		    )
		})
	public ResponseEntity<Boleta> crearBoleta(@RequestBody Boleta boleta){
		return ResponseEntity.ok(boletaServicio.crearBoleta(boleta));
	}
	
	@PostMapping("/pedido")
	@Operation(
		    summary = "Crear una nueva boleta",
		    description = "Registra una nueva boleta cuando se crea un nuevo pedido"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "boleta creada"),
		    @ApiResponse(
		        responseCode = "500",
		        description = "Datos dela boleta erroneas."
		    )
		})
	public ResponseEntity<Boleta> crearBoleta(@RequestBody BoletaDTO boletaDTO){
		return ResponseEntity.ok(boletaServicio.crearBoletaPorPedido(boletaDTO));
		
	}
	
	@DeleteMapping("/{id}")
	@Operation(
		    summary = "Eliminar una boleta por ID",
		    description = "Elimina una boleta existente por su ID. Retorna 204 si se elimina correctamente, o 404 si no se encuentra el usuario."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "204",
		        description = "boleta eliminada."
		    ),
		    @ApiResponse(
		        responseCode = "204",
		        description = "boleta no encontrada."
		    )
		})
		@Parameter(
		    name = "id",
		    description = "ID dela boleta que se desea eliminar",
		    required = true,
		    example = "1"
		)
	public ResponseEntity<Boleta> eliminarBoleta(@PathVariable("id") int id){
		if(boletaServicio.boletaById(id).equals(null)) {
			return ResponseEntity.noContent().build();
		}
		boletaServicio.borrarBoletaById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/actualizar_total")
	@Operation(
		    summary = "actualiza la boleta",
		    description = "puede actualizar una boleta"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "boleta actualizada"),
		    @ApiResponse(
		        responseCode = "500",
		        description = "actualizacion de boleta no encontrada."
		    )
		})
	public ResponseEntity<Void> actualizarTotal(@RequestBody Map<String,Object> detalleBoleta){
		boletaServicio.actualizarBoleta(detalleBoleta);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/{id}/recalcular")
	@Operation(
		    summary = "recalcula la boleta",
		    description = "recalcula la boleta segun la id"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "boleta recalculada"),
		    @ApiResponse(
		        responseCode = "500",
		        description = "recalculado no encontrado."
		    )
		})
    public ResponseEntity<Void> recalcularTotalBoleta(@PathVariable("id") int id) {
        boletaServicio.recalcularTotal(id);
        return ResponseEntity.ok().build();
    }
	
	@PostMapping("/{id}")
	@Operation(
		    summary = "Ediata una boleta",
		    description = "Edita una nueva boleta ya existente"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "boleta editada"),
		    @ApiResponse(
		        responseCode = "204",
		        description = "boleta no encontrada."
		    )
		})
	public ResponseEntity<Boleta> editarBoleta(@PathVariable("id") int id,@RequestBody Boleta boleta){
		if(boletaServicio.boletaById(id).equals(null)) {
			return ResponseEntity.noContent().build();
		}
		boletaServicio.editarBoletaById(id, boleta);
		return ResponseEntity.ok(boleta);
	}
	
	@GetMapping("/detalle/{id}")
	@Operation(
		    summary = "obtiene un listado de detalle boleta",
		    description = "obtiene un listado de detalle boleta buscado por su id"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "listado de la boleta detalle obtenida"),
		    @ApiResponse(
		        responseCode = "204",
		        description = "no se pudo obtener la informacion de detalle boleta."
		    )
		})
	public ResponseEntity<List<DetalleBoletaDTO>> obtenerDetalles(@PathVariable("id") int id){
		List<DetalleBoletaDTO> detalles = boletaServicio.obtenerDetallesBoleta(id);
		if(detalles.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(detalles);
	}
	
	@GetMapping("/usuario/{id}")
	@Operation(
		    summary = "se obtiene un listado de las boletas de un usuario",
		    description = "obtiene un listado de las boletas segun el usuario"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "se a obtenido el listado de las boletas segun el usuario"),
		    @ApiResponse(
		        responseCode = "204",
		        description = "no se pudo obtener la informacion del listado de boletas segun el usuario."
		    )
		})
	public ResponseEntity<List<BoletaDTO>> obtenerBoletasUsuarios(@PathVariable("id") int id){
		List<BoletaDTO> boletas = boletaServicio.obtenerBoletaUsuario(id);
		if(boletas == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(boletas);
	}
	
	@GetMapping("/sucursal/{sucursalid}")
	@Operation(
		    summary = "se obtiene un bolta segun la sucursal",
		    description = "obtiene un boletas segun la id de sucursal"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "se obtuvo la boleta segun la sucursal"),
		    @ApiResponse(
		        responseCode = "204",
		        description = "no se obtuvo la boleta segun la id de sucursal"
		    )
		})
	public ResponseEntity<List<BoletaDTO>> boletasSucursal(@PathVariable("sucursalid") int sucursalId){
		List<BoletaDTO> boletas = boletaServicio.boletaSucursal(sucursalId);
		return boletas == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(boletas);
	}
	
	@GetMapping("/empleado/{empleadoid}")
	@Operation(
		    summary = "se obtiene un bolta segun el empleado",
		    description = "obtiene un boletas segun la id del empleado"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "se obtuvo la boleta segun el empleado"),
		    @ApiResponse(
		        responseCode = "204",
		        description = "no se obtuvo la boleta segun la id del empleado"
		    )
		})
	public ResponseEntity<List<Boleta>> boletasEmpleado(@PathVariable("empleadoid")int empleadoId){
		List<Boleta> boletas = boletaServicio.boletasEmpleado(empleadoId);
		return boletas == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(boletas);
	}
	
	@GetMapping("/pedido/{pedidoid}")
	@Operation(
		    summary = "se obtiene un bolta segun el pedido",
		    description = "obtiene un boletas segun la id del pedido"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "se obtuvo la boleta segun el pedido"),
		    @ApiResponse(
		        responseCode = "204",
		        description = "no se obtuvo la boleta segun la id del pedido"
		    )
		})
	public ResponseEntity<Boleta> boletaPorPedidodId(@PathVariable("pedidoid")int pedidoId){
		Boleta boleta = boletaServicio.boletaByPedidoId(pedidoId);
		return boleta != null ? ResponseEntity.ok(boleta) : ResponseEntity.noContent().build();
	}
}
