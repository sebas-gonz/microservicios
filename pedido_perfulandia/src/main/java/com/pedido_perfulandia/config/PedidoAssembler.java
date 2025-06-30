package com.pedido_perfulandia.config;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.pedido_perfulandia.controllador.PedidoControllador;
import com.pedido_perfulandia.entidad.Pedido;
@Configuration
public class PedidoAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>>{

	@Override
	public EntityModel<Pedido> toModel(Pedido pedido) {
		return EntityModel.of(pedido,linkTo(methodOn(PedidoControllador.class).obtenerPedido(pedido.getPedidoId())).withSelfRel(),
				linkTo(methodOn(PedidoControllador.class).editarPedido(pedido.getPedidoId(), pedido)).withRel("Editar pedido"),
				linkTo(methodOn(PedidoControllador.class).eliminarPedido(pedido.getPedidoId())).withRel("Eliminar pedido"),
				linkTo(methodOn(PedidoControllador.class).pedidoPorSucursal(pedido.getSucursalId())).withRel("Pedidos por sucursal")
				);
	}
	
	public CollectionModel<EntityModel<Pedido>> modelToCollection(List<Pedido> pedidos){
		List<EntityModel<Pedido>> pedidosModel = pedidos.stream().map(this::toModel).toList();
		
		return CollectionModel.of(pedidosModel, linkTo(methodOn(PedidoControllador.class).listarPedidos()).withSelfRel());
	}

}
