package com.inventario_perfulandia;

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

import com.envio_perfulandia.entidad.Inventario;
import com.envio_perfulandia.repositorio.InventarioRepository;
import com.envio_perfulandia.servicio.InventarioServicio;

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
	
	    @Test
	    void testCrearinventario() {
	    	
	        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventario);

	        
	        Inventario creado = inventarioServicio.crearInventario(inventario);
	        assertNotNull(creado);
	        
	        
	        assertEquals(1, creado.getInventarioId());
	        verify(inventarioRepository, times(1)).save(inventario);
	    } 
	  
	    @Test
	    void testObtenerInventarioPorId() {
	    	
	        when(inventarioRepository.findById(1)).thenReturn(Optional.of(inventario));
	        
	        Inventario encontrado = inventarioServicio.inventarioById(1);
	        assertNotNull(encontrado);
	        
	        
	        assertEquals(1, encontrado.getInventarioId());
	        
	       
	        verify(inventarioRepository).findById(1);
	    
	 }
	 @Test
	 void testInventarioNoEncontrado() {
		 when(inventarioRepository.findById(50)).thenReturn(Optional.empty());
		 Inventario inventario = inventarioServicio.inventarioById(50);
		 assertNull(inventario,"Se esperaba null cuando el Inventario no existe");
	 }
	 @Test
	 void testListarInventario() {
		 List<Inventario> inventarios = Arrays.asList(inventario, new Inventario());
		 when(inventarioRepository.findAll()).thenReturn(inventarios);
		 List<Inventario> invEnc = inventarioServicio.obtenerInventarios();
		 assertEquals(2, invEnc.size());
		 verify(inventarioRepository).findAll();
	 }
	 @Test
	 void testActualizarInventario() {
		 when(inventarioRepository.findById(1)).thenReturn(Optional.of(inventario));
		 Inventario invEnc = inventarioServicio.inventarioById(inventario.getInventarioId());
		 invEnc.setCantidadDisponible(200);
		 when(inventarioRepository.findById(invEnc.getInventarioId())).thenReturn(Optional.of(invEnc));
		 when(inventarioRepository.save(any(Inventario.class))).thenReturn(invEnc);
		 Inventario invEdit = inventarioServicio.editarInventario(invEnc.getInventarioId(),invEnc);
		 assertEquals(200, inventario.getCantidadDisponible());
		 assertEquals(invEdit.getInventarioId(), inventario.getInventarioId());
		 verify(inventarioRepository, times(2)).findById(1);
		 verify(inventarioRepository).save(invEnc);																							
	 }
	 @Test
	 void testEliminarInventario() {
		 inventarioServicio.eliminarInventario(1);
		 Inventario inveBorrar = inventarioServicio.inventarioById(1);
		 assertNull(inveBorrar, "Se esperaba null cuando el inventario se haya borrado");
		 verify(inventarioRepository).deleteById(1);
	 }
	 
	 
}

	

