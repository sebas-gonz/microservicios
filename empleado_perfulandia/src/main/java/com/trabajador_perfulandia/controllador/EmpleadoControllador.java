package com.trabajador_perfulandia.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabajador_perfulandia.dto.BoletaDTO;
import com.trabajador_perfulandia.entidad.Empleado;
import com.trabajador_perfulandia.servicio.empleadoServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/empleado")
public class EmpleadoControllador {
	
	
	@Autowired
	private empleadoServicio servicio;

	//Inicio1

	@GetMapping("/")
	@Operation(summary = "Obtener a todos los empleados", description = "Nos da una lista de los empleados")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida."),
	    @ApiResponse(responseCode = "204", description = "No hay empleados en la lista.")
	})
	public ResponseEntity<List<Empleado>> empleados(){
		List<Empleado> empleados = servicio.Empleados();
		if(empleados.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(empleados);
	}
	//Fin1
	//Inicio2
	@PostMapping("/")
	@Operation(summary="Ingresa empleado",description="Ingresa un empleado a la BDD")
	@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Empleado creado"),
		    @ApiResponse(
		        responseCode = "400",
		        description = "Datos del emplado erroneos."
		    )
		})
	public ResponseEntity<Empleado> agregarEmpleado(@RequestBody Empleado empleado){
		return ResponseEntity.ok(servicio.crearEmpleado(empleado));
	}
	//Fin2
	//Inicio3
	@Operation(
		    summary = "Obtener un Empleado por ID",
		    description = "Retorna un Empleado específico según su ID."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Empleado encontrado exitosamente."),
		    @ApiResponse(
		        responseCode = "204",
		        description = "Empleado no esta en la lista"
		    )
		})
		@Parameter(
		    name = "id",
		    description = "ID del Emplado que necesitamos extraer",
		    required = true,
		    example = "1"
		)
	@GetMapping("/{empleadoid}")
	public ResponseEntity<Empleado> empleado(@PathVariable("empleadoid") int empleadoId){
		Empleado empleado = servicio.EmpleadoPorId(empleadoId);
		return empleado == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(empleado);
	}
	//Fin3
	//Inicio4
	@PostMapping("/{empleadoid}")
	@Operation(summary = "Actualizar datos de un empleado", description = "Actualiza un empleado siempre que el id exista.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente."),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable("empleadoid")int empleadoId,@RequestBody Empleado empleado){
		return servicio.editarEmpleado(empleado, empleadoId) == null ? ResponseEntity.noContent().build() : ResponseEntity.ok
				(servicio.editarEmpleado(empleado, empleadoId));
	}
	//Fin4
	//Inicio5
	@DeleteMapping("/{empleadoid}")
	@Operation(
		    summary = "Eliminar un Empleado por ID",
		    description = "Elimina al empleado segun la id. Se Retornara 204 si se elimina correctamente, o 404 si no se encuentra el Empleado."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "204",
		        description = "Empleado eliminado."
		    ),
		    @ApiResponse(
		        responseCode = "404",
		        description = "Empleado no encontrado."
		    )
		})
		@Parameter(
		    name = "Id",
		    description = "ID del Empleado que se desea eliminar",
		    required = true,
		    example = "1"
		)
	public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable("empleadoid")int empleadoId){
		if(servicio.EmpleadoPorId(empleadoId) == null) {
			return ResponseEntity.notFound().build();
		}
		servicio.eliminarEmpleado(empleadoId);
		return ResponseEntity.noContent().build();
        

	}
	//Fin5
	//Inicio6
	@GetMapping("/{empleadoid}/boletas")
	@Operation(
		    summary = " Obtener todas las boletas del registradas por el empleado",
		    description = "Retorna todas las boletas hechas por el empleado."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Boletas  encontradas exitosamente."),
		    @ApiResponse(
		        responseCode = "204",
		        description = "No hay boletas en la lista"
		    )
		})
		@Parameter(
		    name = "id",
		    description = "ID del Empleado del cual queremos saber las boletas realizadas",
		    required = true,
		    example = "1"
		)
	
	public ResponseEntity<List<BoletaDTO>> boletasEmpleados(@PathVariable("empleadoid")int empleadoId){
		List<BoletaDTO> boletas = servicio.boletasEmpleado(empleadoId);
		return boletas == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(boletas);
	}
	
	//Inicio6
	@Operation(
		    summary = "Extraer empleados de una sucursal",
		    description = "Extrae los datos de los empleados segun id sucursal"
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Los empleados se extrayeron exitosamente"
		    ),
		    @ApiResponse(
		        responseCode = "404",
		        description = "El id de la sucursal no existe."
		    )
		})
		@Parameter(
		    name = "Id",
		    description = "ID de la sucursal",
		    required = true,
		    example = "1"
		)
	@GetMapping("/{sucursalid}/sucursal")
	public ResponseEntity<List<Empleado>> empleadosSucursal(@PathVariable("sucursalid")int sucursalId){
		List<Empleado> empleados = servicio.empleadosSucursal(sucursalId);
		return empleados == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(empleados);
	}
	//Final6
}
