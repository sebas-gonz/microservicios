package com.trabajador_perfulandia.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.trabajador_perfulandia.config.EmpleadoAssembler;
import com.trabajador_perfulandia.dto.BoletaDTO;
import com.trabajador_perfulandia.entidad.Empleado;
import com.trabajador_perfulandia.servicio.EmpleadoServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/empleado")
@Tag(name = "empleados", description = "Operaciones relacionados con los empleados")
public class EmpleadoControllador {
	
	
	@Autowired
	private EmpleadoServicio servicio;
	@Autowired
	private EmpleadoAssembler EmpAssembler;
	@GetMapping("/")
	@Operation(summary = "Obtener a todos los empleados", description = "Nos da una lista de los empleados")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida."),
	    @ApiResponse(responseCode = "204", description = "No hay empleados en la lista.")
	})
	public ResponseEntity<CollectionModel<EntityModel<Empleado>>> empleados(){
		List<Empleado> empleados = servicio.Empleados();
		if(empleados.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(EmpAssembler.modelToCollection(empleados));
	}
	
	
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
	public ResponseEntity<EntityModel<Empleado>> agregarEmpleado(@RequestBody Empleado empleado){
		Empleado creado = servicio.crearEmpleado(empleado);
		return ResponseEntity.ok(EmpAssembler.toModel(creado));
	}
	
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
		    name = "empleadoid",
		    description = "ID del Emplado que necesitamos extraer",
		    required = true,
		    example = "1"
		)
	@GetMapping("/{empleadoid}")
	public ResponseEntity<EntityModel<Empleado>> empleado(@PathVariable("empleadoid") int empleadoId){
		Empleado empleado = servicio.EmpleadoPorId(empleadoId);
		return empleado == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(EmpAssembler.toModel(empleado));
	}

	@PutMapping("/{empleadoid}")
	@Operation(summary = "Actualizar datos de un empleado", description = "Actualiza un empleado siempre que el id exista.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente."),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	public ResponseEntity<EntityModel<Empleado>> actualizarEmpleado(@PathVariable("empleadoid")int empleadoId,@RequestBody Empleado empleado){
		Empleado actualizado = servicio.editarEmpleado(empleado, empleadoId);
		return actualizado != null ? ResponseEntity.ok(EmpAssembler.toModel(actualizado)) : ResponseEntity.notFound().build();
	}

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
		    name = "empleadoid",
		    description = "ID del Empleado del cual queremos saber las boletas realizadas",
		    required = true,
		    example = "1"
		)
	
	public ResponseEntity<List<BoletaDTO>> boletasEmpleados(@PathVariable("empleadoid")int empleadoId){
		List<BoletaDTO> boletas = servicio.boletasEmpleado(empleadoId);
		return boletas == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(boletas);
	}
	

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
		    name = "sucursalid",
		    description = "ID de la sucursal",
		    required = true,
		    example = "1"
		)
	@GetMapping("/{sucursalid}/sucursal")
	public ResponseEntity<List<Empleado>> empleadosSucursal(@PathVariable("sucursalid")int sucursalId){
		List<Empleado> empleados = servicio.empleadosSucursal(sucursalId);
		return empleados == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(empleados);
	}
	
}
