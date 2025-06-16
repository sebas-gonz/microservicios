package com.usuario_perfulandia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.usuario_perfulandia.entidad.Usuario;
import com.usuario_perfulandia.repositorio.UsuarioRepository;
import com.usuario_perfulandia.servicio.UsuarioServicio;

public class TestServicioUsuario {
	
	
	 @Mock
	    private UsuarioRepository usuarioRepository;

	    @InjectMocks
	    private UsuarioServicio usuarioServicio;

	    private Usuario usuario;

	    @BeforeEach
	    void setup() {
	        MockitoAnnotations.openMocks(this);

	        usuario = new Usuario();
	        usuario.setUsuario_id(1);
	        usuario.setNombre("Juan");
	        usuario.setApellido("Pérez");
	        usuario.setDireccion("Av. Siempre Viva 742");
	        usuario.setCorreo("juan.perez@example.com");
	        usuario.setContraseña("1234");
	    }
    
	    @Test
	    void testCrearUsuario() {
	        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

	        Usuario creado = usuarioServicio.crearUsuario(usuario);

	        assertNotNull(creado);
	        assertEquals("Juan", creado.getNombre());
	        verify(usuarioRepository, times(1)).save(usuario);
	    }
    
	    @Test
	    void testObtenerUsuarioPorId() {
	        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

	        Usuario encontrado = usuarioServicio.getUsuarioById(1);

	        assertNotNull(encontrado);
	        assertEquals("Pérez", encontrado.getApellido());
	        verify(usuarioRepository).findById(1);
	    }

	    @Test
	    void testUsuarioNoEncontrado() {
	        when(usuarioRepository.findById(50)).thenReturn(Optional.empty());

	        Usuario usuario = usuarioServicio.getUsuarioById(50);

	        assertNull(usuario, "Se esperaba null cuando el usuario no existe");
	    }

	    @Test
	    void testListarUsuarios() {
	        List<Usuario> usuarios = Arrays.asList(usuario, new Usuario());
	        when(usuarioRepository.findAll()).thenReturn(usuarios);

	        List<Usuario> resultado = usuarioServicio.getUsuarios();

	        assertEquals(2, resultado.size());
	        verify(usuarioRepository).findAll();
	    }

	    @Test
	    void testEliminarUsuario() {
	        usuarioServicio.deleteUsuarioById(1);
	        verify(usuarioRepository).deleteById(1);
	    }
}
