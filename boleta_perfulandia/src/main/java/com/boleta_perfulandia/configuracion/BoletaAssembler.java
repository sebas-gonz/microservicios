package com.boleta_perfulandia.configuracion;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;

import com.boleta_perfulandia.controllador.BoletaControllador;
import com.boleta_perfulandia.entidades.Boleta;
@Configuration
public class BoletaAssembler implements RepresentationModelAssembler<Boleta, EntityModel<Boleta>>{


	@Override
	public EntityModel<Boleta> toModel(Boleta boleta) {
		// TODO Auto-generated method stub
		return EntityModel.of(boleta,
				linkTo(methodOn(BoletaControllador.class).obtenerBoleta(boleta.getBoletaId())).withSelfRel(),
				linkTo(methodOn(BoletaControllador.class).editarBoleta(boleta.getBoletaId(), boleta)).withRel("Actualizar boleta"),
				linkTo(methodOn(BoletaControllador.class).eliminarBoleta(boleta.getBoletaId())).withRel("Eliminar boleta"),
				linkTo(methodOn(BoletaControllador.class).obtenerDetalles(boleta.getBoletaId())).withRel("Detalles de la boleta"),
				linkTo(methodOn(BoletaControllador.class).obtenerBoletas()).withRel("Listado de boletas"));
	}
	
	public CollectionModel<EntityModel<Boleta>> modelToCollection(List<Boleta> boleta){
		List<EntityModel<Boleta>> boletasCollection = boleta.stream().map(this::toModel).toList();
		return CollectionModel.of(boletasCollection,
				linkTo(methodOn(BoletaControllador.class).obtenerBoletas()).withSelfRel()
				);
	}

}
