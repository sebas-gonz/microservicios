package com.producto_perfulandia.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import com.producto_perfulandia.controllador.ControladorProducto;
import com.producto_perfulandia.entidad.Producto;

@Configuration
public class ProductoAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>>{

	@Override
	public EntityModel<Producto> toModel(Producto producto) {
		
		return EntityModel.of(producto,linkTo(methodOn(ControladorProducto.class).CargarProducto(0)).withSelfRel(),
				linkTo(methodOn(ControladorProducto.class).ActualizarProducto(producto, producto.getIdproducto())).withRel("Editar producto"),
				linkTo(methodOn(ControladorProducto.class).BorrarProducto(producto.getIdproducto())).withRel("Eliminar producto"),
				linkTo(methodOn(ControladorProducto.class).boletasProducto(producto.getIdproducto())).withRel("Detalle boletas del producto"),
				linkTo(methodOn(ControladorProducto.class).inventariosPorProducto(producto.getIdproducto())).withRel("Inventarios del producto")
				);
	}
	
	public CollectionModel<EntityModel<Producto>> modelToCollection(List<Producto> productos){
		List<EntityModel<Producto>> productosModel = productos.stream().map(this::toModel).toList();
		return CollectionModel.of(productosModel,linkTo(methodOn(ControladorProducto.class).listarProductos()).withSelfRel());
	}

}
