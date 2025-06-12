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

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/empleado")
public class EmpleadoControllador {
	
	
	@Autowired
	private empleadoServicio servicio;
	
	@GetMapping("/")
	public ResponseEntity<List<Empleado>> empleados(){
		List<Empleado> empleados = servicio.Empleados();
		if(empleados.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(empleados);
	}
	
	@PostMapping("/")
	public ResponseEntity<Empleado> agregarEmpleado(@RequestBody Empleado empleado){
		return ResponseEntity.ok(servicio.crearEmpleado(empleado));
	}
	
	@GetMapping("/{empleadoid}")
	public ResponseEntity<Empleado> empleado(@PathVariable("empleadoid") int empleadoId){
		Empleado empleado = servicio.EmpleadoPorId(empleadoId);
		return empleado == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(empleado);
	}
	
	@PostMapping("/{empleadoid}")
	public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable("empleadoid")int empleadoId,@RequestBody Empleado empleado){
		return servicio.editarEmpleado(empleado, empleadoId) == null ? ResponseEntity.noContent().build() : ResponseEntity.ok
				(servicio.editarEmpleado(empleado, empleadoId));
	}
	
	@DeleteMapping("/{empleadoid}")
	public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable("empleadoid")int empleadoId){
		if(servicio.EmpleadoPorId(empleadoId) == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{empleadoid}/boletas")
	public ResponseEntity<List<BoletaDTO>> boletasEmpleados(@PathVariable("empleadoid")int empleadoId){
		List<BoletaDTO> boletas = servicio.boletasEmpleado(empleadoId);
		return boletas == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(boletas);
	}
	@GetMapping("/{sucursalid}/sucursal")
	public ResponseEntity<List<Empleado>> empleadosSucursal(@PathVariable("sucursalid")int sucursalId){
		List<Empleado> empleados = servicio.empleadosSucursal(sucursalId);
		return empleados == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(empleados);
	}
}
