package com.envio_perfulandia.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.envio_perfulandia.dto.EnvioDTO;
import com.envio_perfulandia.entidad.Envio;
import com.envio_perfulandia.servicio.EnvioServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/envio")
@Tag(name = "usuarios", description = "Operaciones relacionados con los envios")
public class EnvioControllador {
	
	@Autowired
	private EnvioServicio servicio;
	
	
	
	@GetMapping("/")
	@Operation(summary = "Obtener todos los envios", description = "Obtiene una lista de todos los envios.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de envios obtenidos."),
	    @ApiResponse(responseCode = "204", description = "No hay envios dentro sistema.")
	})
	public ResponseEntity<List<Envio>> Envios(){
		List<Envio> envios = servicio.envios();
		return envios == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(envios);
	}
	
	@PostMapping("/pedido")
	@Operation(
		    summary = "Crear un nuevo pedido",
		    description = "Registra un nuevo pedido"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Pedido creado"),
		    @ApiResponse(
		        responseCode = "400",
		        description = "Datos del pedido erroneos."
		    )
		})
	public ResponseEntity<Envio> crearEnvio(@RequestBody EnvioDTO patata){
		Envio envio = servicio.crearEnvio(patata);
		return envio == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(envio);
	}
	
	@PostMapping("/")
	@Operation(
		    summary = "Crear un nuevo envio",
		    description = "Registra un nuevo envio en el sistema"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Envio creado"),
		    @ApiResponse(
		        responseCode = "400",
		        description = "Datos del envio erroneos."
		    )
		})
	public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio){
		return ResponseEntity.ok(servicio.crearEnvio(envio));
	}
	
	
	@GetMapping("/{envioid}")
	@Operation(summary = "Obtener todos los envios mediante un Id", description = "Obtiene una lista de todos los envios segun el id de este.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de envios obtenidos."),
	    @ApiResponse(responseCode = "204", description = "No hay envios dentro sistema.")
	})
	@Parameter(
		    name = "envioid",
		    description = "ID del envio del que queremos los datos.",
		    required = true,
		    example = "7"
		)
	public ResponseEntity<Envio> envioById(@PathVariable("envioid")int envioId){
		Envio envio = servicio.envioPorId(envioId);
		return envio == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(envio);
	}
	
	@PostMapping("/{envioid}")
	@Operation(summary = "Edita los datos de un pedido", description = "Edita los datos de un pedido segun la id solicitada")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Datos del envio editados"),
	    @ApiResponse(responseCode = "404", description = "Dato erronoe ingresado")
	})
	@Parameter(
		    name = "envioid",
		    description = "ID del envio del que queremos editar los datos",
		    required = true,
		    example = "5"
		)
	public ResponseEntity<Envio> editarEnvio(@PathVariable("envioid")int envioId,@RequestBody Envio envioAct){
		Envio envio = servicio.editarEnvio(envioId, envioAct);
		return envio == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(envio);
	}
}
