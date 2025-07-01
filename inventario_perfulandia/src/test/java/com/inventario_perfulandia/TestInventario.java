package com.inventario_perfulandia;

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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.envio_perfulandia.dto.InventarioDTO;
import com.envio_perfulandia.entidad.Inventario;
import com.envio_perfulandia.repositorio.InventarioRepository;
import com.envio_perfulandia.servicio.InventarioServicio;
import com.usuario_perfulandia.entidad.Usuario;
@ExtendWith(MockitoExtension.class)
public class TestInventario {

	 @Mock
	 private InventarioRepository inventarioRepository ;
	 
	 @InjectMocks
	 private InventarioServicio inventarioServicio ;
	 
	 private Inventario inventario ;
	 
	 @BeforeEach
	 void setup() {
		 inventario= new Inventario();
		 inventario.setCantidadDisponible(1);
		 inventario.setInventarioId(1);
		 inventario.setProductoId(1);
		 inventario.setSucursalId(1); 
	 }
	//Este test verifica que se puedan crear y guardar el inventario para el servicio del Inventario.
	    @Test
	    void testCrearinventario() {
	    	//Este metodo pone a prueba que si el inventarioRepositorio guarda una clase de tipo Inventario, debe retornar un objeto de la clase inventario.
	        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventario);

	        //Se pone a prueba que el InventarioServicio haya crado y guardado el objeto inventario, comprobando que sea distinto de nulo.
	        Inventario creado = inventarioServicio.crearInventario(inventario);
	        assertNotNull(creado);
	        
	        //Se comprueba la coincidencia entre el inventario creado y el id 1. Despues se verifica que el metodo ".save" del repositorio de haya usado una vez.
	        assertEquals(1, creado.getInventarioId());
	        verify(inventarioRepository, times(1)).save(inventario);
	    } 
	  //Este test pone a prueba que se pueda obtener un inventario por el ID 
	    @Test
	    void testObtenerInventarioPorId() {
	    	//prueba el metodo "findById", debe retornar el objeto del inventario.
	        when(inventarioRepository.findById(1)).thenReturn(Optional.of(inventario));
	        //Se encuentra el inventario con ID: 1 y se verifica que sea distinto a nulo.
	        Inventario encontrado = inventarioServicio.inventarioById(1);
	        assertNotNull(encontrado);
	        
	        //Coincidencia entre el id y 1.
	        assertEquals(1, encontrado.getInventarioId());
	        
	        //verificacion de que el metodo findById se haya llamado 1 vez
	        verify(inventarioRepository).findById(1);
	    }
	  //Test para un usuario no encontrado.
	    @Test
	    void testUsuarioNoEncontrado() {
	    	//Aqui debe devolver optional.empty porque no existe el usuario con el ID 50
	        when(inventarioRepository.findById(1)).thenReturn(Optional.empty());
	        
	        //Se busca un inventario con el id 50.
	        InventarioDTO usuario = inventarioServicio.inventarioById(1);
	        //Aqui se espera nulo porque el inventario no existe.
	        assertNull(usuario, "Se esperaba null cuando el inventario no existe");
	    }
	 }

	

