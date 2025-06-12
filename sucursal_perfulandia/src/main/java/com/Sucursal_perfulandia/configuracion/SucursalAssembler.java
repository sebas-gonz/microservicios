package com.Sucursal_perfulandia.configuracion;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.Sucursal_perfulandia.Controller.SucuController;
import com.Sucursal_perfulandia.entidad.Sucursal;

@Configuration
public class SucursalAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>>{


	@Override
	public EntityModel<Sucursal> toModel(Sucursal sucursal) {
		// TODO Auto-generated method stub
		return EntityModel.of(sucursal,linkTo(methodOn(SucuController.class).CargarSucuId(sucursal.getSucursalId())).withSelfRel(),
				
				linkTo(methodOn(SucuController.class).cargarSucursales()).withRel("sucursales"),
				
				linkTo(methodOn(SucuController.class).boletasSurcursal(sucursal.getSucursalId())).withRel("boletas"),
				
				linkTo(methodOn(SucuController.class).empleadosSucursal(sucursal.getSucursalId())).withRel("empleados"),
				
				linkTo(methodOn(SucuController.class).inventarioSucursal(sucursal.getSucursalId())).withRel("inventario"),
				
				linkTo(methodOn(SucuController.class).pedidosPorSucursal(sucursal.getSucursalId())).withRel("pedidos")
				);
	}
	
	public CollectionModel<EntityModel<Sucursal>> modelToCollection(List<Sucursal> sucursales){
			List<EntityModel<Sucursal>> sucursalesModel = sucursales.stream().map(this::toModel).toList();
		
		return CollectionModel.of(sucursalesModel,
				linkTo(methodOn(SucuController.class).cargarSucursales()).withSelfRel());
	}

}
