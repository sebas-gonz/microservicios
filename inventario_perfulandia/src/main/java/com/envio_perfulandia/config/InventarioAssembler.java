package com.envio_perfulandia.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;

import com.envio_perfulandia.controllador.InventarioControllador;
import com.envio_perfulandia.entidad.Inventario;
@Configuration
public class InventarioAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>>{
	@Override
    public EntityModel<Inventario> toModel(Inventario inventario) {
        return EntityModel.of(inventario,
            linkTo(methodOn(InventarioControllador.class).obtenerInventario(inventario.getInventarioId())).withSelfRel(),
            linkTo(methodOn(InventarioControllador.class).Inventarios()).withRel("inventarios"),
            linkTo(methodOn(InventarioControllador.class).inventarioPorSucursal(inventario.getSucursalId())).withRel("porSucursal"),
            linkTo(methodOn(InventarioControllador.class).inventarioPorProducto(inventario.getProductoId())).withRel("porProducto"),
            linkTo(methodOn(InventarioControllador.class).inventariosPorSucursalYProducto(inventario.getSucursalId(), inventario.getProductoId()))
                .withRel("porSucursalYProducto")
        );
    }

    public CollectionModel<EntityModel<Inventario>> toCollectionModel(List<Inventario> inventarios) {
        List<EntityModel<Inventario>> modelos = inventarios.stream()
            .map(this::toModel).toList();
        return CollectionModel.of(modelos,
            linkTo(methodOn(InventarioControllador.class).Inventarios()).withSelfRel()
        );
    }
}
