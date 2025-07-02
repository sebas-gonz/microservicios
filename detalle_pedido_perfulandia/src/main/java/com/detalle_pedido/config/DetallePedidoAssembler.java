package com.detalle_pedido.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;

import com.detalle_pedido.controllador.DetallePedidoControllador;
import com.detalle_pedido.entidad.DetallePedido;

@Configuration
public class DetallePedidoAssembler implements RepresentationModelAssembler<DetallePedido, EntityModel<DetallePedido>>{
	
	@Override
	public EntityModel<DetallePedido> toModel(DetallePedido detallePedido) {
	    return EntityModel.of(detallePedido,
	    		linkTo(methodOn(DetallePedidoControllador.class).detallePedidoById(detallePedido.getDetallePedidoId())).withSelfRel(),
	    		linkTo(methodOn(DetallePedidoControllador.class).editarDetallePedido(detallePedido.getPedidoId(), detallePedido)).withRel("Editar  detalle pedido"),
	    		linkTo(methodOn(DetallePedidoControllador.class).eliminarDetallePedido(detallePedido.getDetallePedidoId())).withRel("eliminar detalle pedido"));
	}
	
	public CollectionModel<EntityModel<DetallePedido>> modelToCollection(List<DetallePedido> detallePedido){
		List<EntityModel<DetallePedido>> detallePedidoModel = detallePedido.stream().map(this::toModel).toList();
		return CollectionModel.of(
		        detallePedidoModel,
		        linkTo(methodOn(DetallePedidoControllador.class).obtenerDetallesPedidos()).withSelfRel()
		    );
	}
	

}
