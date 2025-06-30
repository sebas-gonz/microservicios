package com.trabajador_perfulandia.config;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.trabajador_perfulandia.controllador.EmpleadoControllador;
import com.trabajador_perfulandia.entidad.Empleado;




@Component
public class EmpleadoAssembler implements RepresentationModelAssembler<Empleado, EntityModel<Empleado>> {
	@Override
	public EntityModel<Empleado> toModel(Empleado empleado) {
				return EntityModel.of(empleado,
						linkTo(methodOn(EmpleadoControllador.class).empleado(empleado.getEmpleadoId())).withSelfRel(),
						linkTo(methodOn(EmpleadoControllador.class).empleados()).withRel("Todos los empleados"),
						linkTo(methodOn(EmpleadoControllador.class).boletasEmpleados(empleado.getEmpleadoId())).withRel("Boletas empleado"),
						linkTo(methodOn(EmpleadoControllador.class).empleadosSucursal(empleado.getSucursalId())).withRel("Sucursal")
						);
	}
	public CollectionModel<EntityModel<Empleado>> modelToCollection(List<Empleado> empleados){
		List<EntityModel<Empleado>> empleadoModel = empleados.stream().map(this::toModel).toList();
		
		return CollectionModel.of(empleadoModel,linkTo(methodOn(EmpleadoControllador.class).empleados()).withSelfRel());
	}
  
}

