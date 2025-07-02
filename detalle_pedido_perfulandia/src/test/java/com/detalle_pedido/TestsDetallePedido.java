package com.detalle_pedido;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.detalle_pedido.entidad.DetallePedido;
import com.detalle_pedido.controllador.DetallePedidoControllador;
import com.detalle_pedido.servicio.DetallePedidoServicio;
import com.detalle_pedido.repositorio.DetallePedidoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestsDetallePedido {
	 @Mock
	    private DetallePedidoRepositorio detallePedidoRepositorio;

	    @InjectMocks
	    private DetallePedidoServicio detallePedidoServicio;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void ObetenerTodosLosDetallesPedidos() {
	        DetallePedido d1 = new DetallePedido();
	        DetallePedido d2 = new DetallePedido();
	        when(detallePedidoRepositorio.findAll()).thenReturn(Arrays.asList(d1, d2));

	        List<DetallePedido> resultado = detallePedidoServicio.detallePedidos();

	        assertEquals(2, resultado.size());
	        verify(detallePedidoRepositorio, times(1)).findAll();
	    }

	    @Test
	    public void TestGuardarDetallePedido() {
	        DetallePedido detalle = new DetallePedido();
	        when(detallePedidoRepositorio.save(detalle)).thenReturn(detalle);
	        DetallePedido resultado = detallePedidoServicio.crearDetallePedido(detalle);
	        assertNotNull(resultado);
	        verify(detallePedidoRepositorio, times(1)).save(detalle);
	    }

	    @Test
	    public void obetenPorId() {
	        DetallePedido detalle = new DetallePedido();
	        detalle.setDetallePedidoId(1);
	        when(detallePedidoRepositorio.findById(1)).thenReturn(Optional.of(detalle));

	        DetallePedido resultado = detallePedidoServicio.detallePedidoById(1);

	        assertNotNull(resultado);
	        assertEquals(1, resultado.getDetallePedidoId());
	        verify(detallePedidoRepositorio, times(1)).findById(1);
	    }

	    @Test
	    public void testEliminarDetallePedido() {
	        int id = 1;

	        detallePedidoServicio.eliminarDetallePedido(id);

	        verify(detallePedidoRepositorio, times(1)).deleteById(id);
	    }

	    @Test
	    public void testActualizarDetallePedido() {
	        DetallePedido detalleExistente = new DetallePedido();
	        detalleExistente.setDetallePedidoId(1);
	        detalleExistente.setCantidad(2);

	        DetallePedido detalleNuevo = new DetallePedido();
	        detalleNuevo.setCantidad(5);

	        when(detallePedidoRepositorio.findById(1)).thenReturn(Optional.of(detalleExistente));
	        when(detallePedidoRepositorio.save(any(DetallePedido.class))).thenReturn(detalleExistente);

	        DetallePedido resultado = detallePedidoServicio.editarDetallePedido(1, detalleNuevo);

	        assertEquals(5, resultado.getCantidad());
	        verify(detallePedidoRepositorio, times(1)).findById(1);
	        verify(detallePedidoRepositorio, times(1)).save(detalleExistente);
	    }
	    
	    @Test
	    void testNoEncontrado() {
		     when(detallePedidoRepositorio.findById(50)).thenReturn(Optional.empty());
		     
		     DetallePedido encontrado = detallePedidoServicio.detallePedidoById(50);
		     
		     assertNull(encontrado,"El detalle pedido debe ser nulo");
		     verify(detallePedidoRepositorio).findById(50);
		     
	    }
	    

}
