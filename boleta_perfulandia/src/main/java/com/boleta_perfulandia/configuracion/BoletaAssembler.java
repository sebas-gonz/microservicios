package com.boleta_perfulandia.configuracion;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.boleta_perfulandia.entidades.Boleta;
@Component
public class BoletaAssembler implements RepresentationModelAssembler<Boleta, EntityModel<Boleta>>{

	@Override
	public EntityModel<Boleta> toModel(Boleta boleta) {
		// TODO Auto-generated method stub
		return null;
	}

}
