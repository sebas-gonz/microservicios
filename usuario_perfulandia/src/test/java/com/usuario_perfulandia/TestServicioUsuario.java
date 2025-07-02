package com.usuario_perfulandia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.usuario_perfulandia.entidad.Usuario;
import com.usuario_perfulandia.repositorio.UsuarioRepository;
import com.usuario_perfulandia.servicio.UsuarioServicio;
@ExtendWith(MockitoExtension.class)
public class TestServicioUsuario {
	
	
	 	@Mock
	    private UsuarioRepository usuarioRepository;

	    @InjectMocks
	    private UsuarioServicio usuarioServicio;

	    private Usuario usuario;

	    @BeforeEach
	    void setup() {

	        usuario = new Usuario();
	        usuario.setUsuario_id(1);
	        usuario.setNombre("Juan");
	        usuario.setApellido("Pérez");
	        usuario.setDireccion("Av. Siempre Viva 742");
	        usuario.setCorreo("juan.perez@example.com");
	        usuario.setContraseña("1234");
	    }
	    //Este test verifica que se puedan crear y guardar usuarios en el servicio de Usuario.
	    @Test
	    void testCrearUsuario() {
	    	//Este metodo pone a prueba que si el usuarioRepositorio guarda una clase de tipo Usuario, debe retornar un objeto de la clase usuario.
	        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

	        //Se pone a prueba que el usuarioServicio haya crado y guardado el objeto usuario, comprobando que sea distinto de nulo.
	        Usuario creado = usuarioServicio.crearUsuario(usuario);
	        assertNotNull(creado);
	        
	        //Se comprueba la coincidencia entre el usuario creado y el nombre de "Juan". Despues se verifica que el metodo ".save" del repositorio de haya usado una vez.
	        assertEquals("Juan", creado.getNombre());
	        verify(usuarioRepository, times(1)).save(usuario);
	    }
	    //Este test pone a prueba que se pueda obtener un usuario por el ID 
	    @Test
	    void testObtenerUsuarioPorId() {
	    	//prueba el metodo "findById", debe retornar el objeto usuario.
	        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
	        //Se encuentra el usuario con ID: 1 y se verifica que sea distinto a nulo.
	        Usuario encontrado = usuarioServicio.getUsuarioById(1);
	        assertNotNull(encontrado);
	        
	        //Coincidencia entre el apellido del usuario encontrado, y el apellido perez.
	        assertEquals("Pérez", encontrado.getApellido());
	        
	        //verificacion de que el metodo findById se haya llamado 1 vez
	        verify(usuarioRepository).findById(1);
	    }
	    
	    //Test para un usuario no encontrado.
	    @Test
	    void testUsuarioNoEncontrado() {
	    	//Aqui debe devolver optional.empty porque no existe el usuario con el ID 50
	        when(usuarioRepository.findById(50)).thenReturn(Optional.empty());
	        
	        //Se busca un usuario con el id 50.
	        Usuario usuario = usuarioServicio.getUsuarioById(50);
	        //Aqui se espera nulo porque el usuario no existe.
	        assertNull(usuario, "Se esperaba null cuando el usuario no existe");
	    }
	    //Test para obtener todos los usuarios del repositorio.
	    @Test
	    void testListarUsuarios() {
	    	//Se simula una llamada al metodo "findAll()" con una lista de usuarios. 
	        List<Usuario> usuarios = Arrays.asList(usuario, new Usuario());
	        when(usuarioRepository.findAll()).thenReturn(usuarios);
	        //La lista resultado debe tener un tamaño de 2 y se verifica que el metodo "findAll()" se haya llamado una vez.
	        List<Usuario> resultado = usuarioServicio.getUsuarios();

	        assertEquals(2, resultado.size());
	        verify(usuarioRepository).findAll();
	    }

	    
	    
	    @Test //Para editar un usuario
	    void editarUsuario() {

	    	    when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));// Simulacion donde el repositorio encuentra un usuario por el id
	    	   
	    	    Usuario encontrado = usuarioServicio.getUsuarioById(1); // Obtenemos el usuario
	    	    assertNotNull(encontrado, "Se esperaba un usuario");
	    	    assertEquals("Juan", encontrado.getNombre());
	    	   
	    	    encontrado.setNombre("test"); // Editamos el usuario

	    	    
	    	    when(usuarioRepository.findById(encontrado.getUsuario_id())).thenReturn(Optional.of(encontrado));// Simulamos nuevamente la búsqueda al editar

	    	 
	    	    when(usuarioRepository.save(any(Usuario.class))).thenReturn(encontrado); // Simulacion de guardar.

	    	    
	    	    Usuario editado = usuarioServicio.editarUsuario(encontrado, encontrado.getUsuario_id());// Ejecutamos la edición

	    	    
	    	    assertEquals("test", usuario.getNombre());// Verificamos que el nombre fue cambiado
	    	    assertEquals(editado.getUsuario_id(), usuario.getUsuario_id()); //Verificamos si el usuario editado es el mismo que el usuario original
	    	    // Verificamos que los metodos se hayan invocado las veces necesarias para el proceso
	    	    verify(usuarioRepository, times(2)).findById(1);
	    	    verify(usuarioRepository).save(encontrado);
	    }
	    
	    @Test //Para eliminar un usuario 
	    void testEliminarUsuario() {
	        usuarioServicio.deleteUsuarioById(1); //Elimnamos el usuario
	        Usuario eliminado = usuarioServicio.getUsuarioById(1); //Buscamos el usuario
	        assertNull(eliminado, "Se esperaba null cuando el usuario no se encuentra");
	        verify(usuarioRepository).deleteById(1); // Se verifica el metodo se haya usado una vez
	    }
}
