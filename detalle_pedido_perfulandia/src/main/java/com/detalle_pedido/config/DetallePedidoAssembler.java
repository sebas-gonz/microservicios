package com.detalle_pedido.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.hateoas.CollectionModel;

import com.detalle_pedido.controllador.DetallePedidoControllador;
import com.detalle_pedido.entidad.DetallePedido;

public class DetallePedidoAssembler implements RepresentationModelAssembler<DetallePedido, EntityModel<DetallePedido>>{
	
	@Override
	public EntityModel<DetallePedido> toModel(DetallePedido detallePedido) {
	    return EntityModel.of(
	        detallePedido,
	        linkTo(methodOn(DetallePedidoControllador.class).DetallePedidoById(detallePedido.getDetallePedidoId())).withSelfRel(),
	        linkTo(methodOn(DetallePedidoControllador.class).editarDetallePedido(detallePedido.getDetallePedidoId(), detallePedido)).withRel("editar"),
	        linkTo(methodOn(DetallePedidoControllador.class).detallesPorPedido(detallePedido.getPedidoId())).withRel("detalles-por-pedido")
	    );
	}
	
	public CollectionModel<EntityModel<DetallePedido>> modelToCollection(List<DetallePedido> detallePedido){
		List<EntityModel<DetallePedido>> detallePedidoModel = detallePedido.stream().map(this::toModel).toList();
		return CollectionModel.of(
		        detallePedidoModel,
		        linkTo(methodOn(DetallePedidoControllador.class).obtenerDetallesPedidos()).withSelfRel()
		    );
	}
	

}
