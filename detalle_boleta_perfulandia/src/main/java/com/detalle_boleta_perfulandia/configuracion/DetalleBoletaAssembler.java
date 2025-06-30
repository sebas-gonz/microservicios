package com.detalle_boleta_perfulandia.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import com.detalle_boleta_perfulandia.controllador.DetalleBoletaControllador;
import com.detalle_boleta_perfulandia.entidades.DetalleBoleta;
@Configuration
public class DetalleBoletaAssembler implements RepresentationModelAssembler<DetalleBoleta, EntityModel<DetalleBoleta>>{

	@Override
	public EntityModel<DetalleBoleta> toModel(DetalleBoleta detalleBoleta) {
		// TODO Auto-generated method stub
		return EntityModel.of(detalleBoleta,
				linkTo(methodOn(DetalleBoletaControllador.class).obtenerBoletaPorId(detalleBoleta.getDetalleBoletaId())).withSelfRel(),
				linkTo(methodOn(DetalleBoletaControllador.class).actualizarDetalleBoleta(detalleBoleta, detalleBoleta.getDetalleBoletaId())).withRel("Editar detalle boleta"),
				linkTo(methodOn(DetalleBoletaControllador.class).eliminarDetalleBoleta(detalleBoleta.getDetalleBoletaId())).withRel("Eliminar detalle boleta"),
				linkTo(methodOn(DetalleBoletaControllador.class).obtenerDetalleBoleta()).withRel("Detalle boletas")
				);
	}
	
	
	public CollectionModel<EntityModel<DetalleBoleta>> toCollection(List<DetalleBoleta> detalleBoleta){
		List<EntityModel<DetalleBoleta>> detallesBoletasModel = detalleBoleta.stream().map(this::toModel).toList();
		return CollectionModel.of(detallesBoletasModel,
				linkTo(methodOn(DetalleBoletaControllador.class).obtenerDetalleBoleta()).withSelfRel());
	}
}
