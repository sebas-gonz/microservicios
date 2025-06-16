package com.envio_perfulandia.config;

import java.util.List;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.envio_perfulandia.entidad.Envio;
import com.envio_perfulandia.controllador.EnvioControllador;

public class EnvioAssembler implements RepresentationModelAssembler <Envio,EntityModel<Envio>>{
	@Override
	public EntityModel<Envio> toModel(Envio envio){
		return EntityModel.of(envio,
				linkTo(methodOn(EnvioControllador.class).Envios()).withRel("Pedidos"),
				linkTo(methodOn(EnvioControllador.class).envioById(envio.getEnvioId())).withSelfRel()
				
				
				);
		
	}
	public CollectionModel<EntityModel<Envio>>modeloToCollection(List<Envio>envio){
		List<EntityModel<Envio>> modelEnvio = envio.stream().map(this::toModel).toList();
		return CollectionModel.of(modelEnvio,linkTo(methodOn(EnvioControllador.class).Envios()).withRel("Envios"));
	}
	

}
