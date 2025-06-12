package com.usuario_perfulandia.config;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.usuario_perfulandia.controllador.UsuarioControllador;
import com.usuario_perfulandia.entidad.Usuario;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
@Component
public class UsuarioAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{

	@Override
	public EntityModel<Usuario> toModel(Usuario usuario) {
				return EntityModel.of(usuario,
						linkTo(methodOn(UsuarioControllador.class).obtenerUsuario(usuario.getUsuario_id())).withSelfRel(),
						linkTo(methodOn(UsuarioControllador.class).listarUsuarios()).withRel("usuarios"),
						linkTo(methodOn(UsuarioControllador.class).boletasUsuario(usuario.getUsuario_id())).withRel("boletas"),
						linkTo(methodOn(UsuarioControllador.class).pedidosPorUsuario(usuario.getUsuario_id())).withRel("pedidos")
			        );
	}
	
	public CollectionModel<EntityModel<Usuario>> modelToCollection(List<Usuario> usuarios){
		List<EntityModel<Usuario>> usuariosModel = usuarios.stream().map(this::toModel).toList();
		
		return CollectionModel.of(usuariosModel,linkTo(methodOn(UsuarioControllador.class).listarUsuarios()).withSelfRel());
	}
}
