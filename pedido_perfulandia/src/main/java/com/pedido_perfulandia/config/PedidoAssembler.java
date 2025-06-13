package com.pedido_perfulandia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.pedido_perfulandia.entidad.Pedido;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Configuration
public class PedidoAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>>{

	@Override
	public EntityModel<Pedido> toModel(Pedido pedido) {
		return null;
	}

}
