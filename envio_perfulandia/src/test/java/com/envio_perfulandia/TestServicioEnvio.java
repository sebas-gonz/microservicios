package com.envio_perfulandia;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.envio_perfulandia.entidad.Envio;
import com.envio_perfulandia.repositorio.EnvioRepositorio;
import com.envio_perfulandia.servicio.EnvioServicio;

public class TestServicioEnvio {
	 @Mock
	    private EnvioRepositorio repositorio;

	    @InjectMocks
	    private  EnvioServicio servicio;
	    private Envio envio;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void ObetenerTodosLosEnvios() {
	        envio = new Envio();
	        Envio envio2 = new Envio();
	        when(repositorio.findAll()).thenReturn(Arrays.asList(envio, envio2));

	        List<Envio> resultado = servicio.envios();

	        assertEquals(2, resultado.size());
	        verify(repositorio, times(1)).findAll();
	    }

	    @Test
	    public void TestGuardarEnvio() {
	        envio = new Envio();
	        when(repositorio.save(envio)).thenReturn(envio);
	        Envio resultado = servicio.crearEnvio(envio);
	        assertNotNull(resultado);
	        verify(repositorio, times(1)).save(envio);
	    }

	    @Test
	    public void obetenPorId() {
	        envio = new Envio();
	        envio.setEnvioId(1);
	        when(repositorio.findById(1)).thenReturn(Optional.of(envio));

	        Envio resultado = servicio.envioPorId(1);

	        assertNotNull(resultado);
	        assertEquals(1, resultado.getEnvioId());
	        verify(repositorio, times(1)).findById(1);
	    }

	    @Test
	    public void testEliminarEnvio() {
	        int id = 1;

	        servicio.eliminarEnvio(id);

	        verify(repositorio, times(1)).deleteById(id);
	    }

	    @Test
	    public void testActualizarEnvio() {
	        envio = new Envio();
	        envio.setEnvioId(1);
	        envio.setUsuarioId(1);

	        when(repositorio.findById(1)).thenReturn(Optional.of(envio));
	        
	        Envio envioencontrado = servicio.envioPorId(1);
	        envioencontrado.setUsuarioId(5);
	        
	        when(repositorio.findById(1)).thenReturn(Optional.of(envio));
	        when(repositorio.save(envio)).thenReturn(envio);
	        Envio resultado = servicio.editarEnvio(envio.getEnvioId(), envioencontrado);
	        
	        
	        assertEquals(5, envio.getUsuarioId());
	        assertEquals(envio.getEnvioId(),resultado.getEnvioId());
	        verify(repositorio, times(2)).findById(1);
	        verify(repositorio, times(1)).save(envio);
	    }
	    @Test
	     void testEnvioNoEncontrado() {
	         when(repositorio.findById(50)).thenReturn(Optional.empty());
	         Envio inventario = servicio.envioPorId(50);
	         assertNull(inventario,"Se esperaba null cuando el Inventario no existe");
	     }
	    

}
