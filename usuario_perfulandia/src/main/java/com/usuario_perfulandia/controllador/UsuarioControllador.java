package com.usuario_perfulandia.controllador;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario_perfulandia.config.UsuarioAssembler;
import com.usuario_perfulandia.dto.BoletaDTO;
import com.usuario_perfulandia.dto.PedidoDTO;
import com.usuario_perfulandia.entidad.Usuario;
import com.usuario_perfulandia.servicio.UsuarioServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuario")
@Tag(name = "usuarios", description = "Operaciones relacionados con los usuarios")
public class UsuarioControllador {
	
	@Autowired
	private UsuarioServicio usuarioService;
	
	@Autowired
	private UsuarioAssembler assembler;
	
	@GetMapping("/")
	@Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista de todos los usuarios.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida."),
	    @ApiResponse(responseCode = "204", description = "No hay usuarios registrados.")
	})
	public ResponseEntity<CollectionModel<EntityModel<Usuario>>> listarUsuarios(){
		List<Usuario> usuarios = usuarioService.getUsuarios();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(assembler.modelToCollection(usuarios));
				
	}
	
	
	@GetMapping("/{id}")
	@Operation(
	    summary = "Obtener un usuario por ID",
	    description = "Retorna un usuario específico según su ID. Si no se encuentra, retorna código 404."
	)
	@ApiResponses(value = {
	    @ApiResponse(
	        responseCode = "200",
	        description = "Usuario encontrado exitosamente."),
	    @ApiResponse(
	        responseCode = "404",
	        description = "Usuario no encontrado"
	    )
	})
	@Parameter(
	    name = "id",
	    description = "ID del usuario a obtener",
	    required = true,
	    example = "1"
	)
	public ResponseEntity<EntityModel<Usuario>> obtenerUsuario(@PathVariable("id") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		
		return usuario == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(assembler.toModel(usuario));
	}
	
	@DeleteMapping("/{id}")
	@Operation(
	    summary = "Eliminar un usuario por ID",
	    description = "Elimina un usuario existente por su ID. Retorna 204 si se elimina correctamente, o 404 si no se encuentra el usuario."
	)
	@ApiResponses(value = {
	    @ApiResponse(
	        responseCode = "204",
	        description = "Usuario eliminado."
	    ),
	    @ApiResponse(
	        responseCode = "404",
	        description = "Usuario no encontrado."
	    )
	})
	@Parameter(
	    name = "id",
	    description = "ID del usuario que se desea eliminar",
	    required = true,
	    example = "1"
	)
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.deleteUsuarioById(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PostMapping("/")
	@Operation(
	    summary = "Crear un nuevo usuario",
	    description = "Registra un nuevo usuario"
	)
	@ApiResponses(value = {
	    @ApiResponse(
	        responseCode = "200",
	        description = "Usuario creado"),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Datos del usuario erroneos."
	    )
	})
	public ResponseEntity<EntityModel<Usuario>> crearUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario =  usuarioService.crearUsuario(usuario);
		
		return ResponseEntity.ok(assembler.toModel(nuevoUsuario));
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar un usuario", description = "Actualiza un usuario existente mediante un id.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente."),
			@ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	})
	public ResponseEntity<EntityModel<Usuario>> actualizarUsuario(
			@RequestBody Usuario usuario,
			@Parameter(description = "Id del usuario", required = true)
			@PathVariable("id")int id){
		Usuario actualizarUsuario = usuarioService.editarUsuario(usuario, id);
		return  actualizarUsuario == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(assembler.toModel(actualizarUsuario));
	}
	
	@GetMapping("/{usuarioid}/boleta")
	@Operation(
	    summary = "Obtener todas las boletas de un usuario",
	    description = "Retorna una lista de boletas de un usuario específico, incluyendo los detalles de cada boleta."
	)
	@ApiResponses(value = {
	    @ApiResponse(
	        responseCode = "200",
	        description = "Lista de boletas obtenida correctamente."),
	    @ApiResponse(
	        responseCode = "404",
	        description = "No se encontró un usuario con el ID especificado o no tiene boletas registradas."
	    )
	})
	@Parameter(
	    name = "usuarioid",
	    description = "ID del usuario del cual se quieren obtener las boletas.",
	    required = true,
	    example = "7"
	)
	public ResponseEntity<List<BoletaDTO>> boletasUsuario(@PathVariable("usuarioid") int id){
		List<BoletaDTO> boletas = usuarioService.boletasUsuario(id);
		return boletas != null ? ResponseEntity.ok(boletas) : ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/{usuarioid}/pedido")
	@Operation(
	    summary = "Obtener todos los pedidos de un usuario",
	    description = "Retorna una lista de pedidos realizados por un usuario. Cada pedido incluye sus productos, cantidades, sucursal, estado y fecha del pedido."
	)
	@ApiResponses(value = {
	    @ApiResponse(
	        responseCode = "200",
	        description = "Lista de pedidos obtenida correctamente."),
	    @ApiResponse(
	        responseCode = "404",
	        description = "No se encontró un usuario con el ID especificado o no tiene pedidos registrados."
	    )
	})
	@Parameter(
	    name = "usuarioid",
	    description = "ID del usuario del cual se desean obtener los pedidos.",
	    required = true,
	    example = "5"
	)
	public ResponseEntity<List<PedidoDTO>> pedidosPorUsuario(@PathVariable("usuarioid")int usuarioId){
		List<PedidoDTO> pedidos = usuarioService.pedidosPorUsuario(usuarioId);
		
		return pedidos != null ? ResponseEntity.ok(pedidos) : ResponseEntity.notFound().build();
	}
}
