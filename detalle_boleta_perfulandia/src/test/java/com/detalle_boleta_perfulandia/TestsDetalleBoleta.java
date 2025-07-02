package com.detalle_boleta_perfulandia;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.detalle_boleta_perfulandia.entidades.DetalleBoleta;
import com.detalle_boleta_perfulandia.repositorio.DetalleBoletaRepositorio;
import com.detalle_boleta_perfulandia.servicio.DetalleBoletaServicio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class TestsDetalleBoleta {
	@Mock
    private DetalleBoletaRepositorio detalleBoletaRepositorio;

    @InjectMocks
    private DetalleBoletaServicio detalleBoletaServicio;
    private DetalleBoleta detalleBoleta;
    @BeforeEach
    void setUp() {
        detalleBoleta = new DetalleBoleta();
        detalleBoleta.setBoletaId(1);
        detalleBoleta.setCantidad(100);
        detalleBoleta.setDetalleBoletaId(1);
        detalleBoleta.setNombreProducto("Perfume");
        detalleBoleta.setProductoId(1);
        detalleBoleta.setSubtotal(1000);
    }

    @Test
    void crearDetalleBoleta() {
        when(detalleBoletaRepositorio.save(any(DetalleBoleta.class))).thenReturn(detalleBoleta);
        
        DetalleBoleta resultado = detalleBoletaServicio.crearDetalleBoleta(detalleBoleta);

        assertNotNull(resultado);
        
        verify(detalleBoletaRepositorio, times(1)).save(detalleBoleta);
    }

    @Test
    void elimnarDetalleBoleta() {
    	
        detalleBoletaServicio.eliminarDetalleBoletaById(1);
        DetalleBoleta detBoleElim = detalleBoletaServicio.obtenerDetalleBoletaById(1);
        assertNull(detBoleElim, "Se esperaba null si se borra el detalle boleta");
        verify(detalleBoletaRepositorio).deleteById(1);
    }
    
    @Test
    void listarDetalleBoleta() {
        List<DetalleBoleta> detallesBoletas = Arrays.asList(detalleBoleta, new DetalleBoleta());
        when(detalleBoletaRepositorio.findAll()).thenReturn(detallesBoletas);
        List<DetalleBoleta> Listas = detalleBoletaServicio.obtenerDetalleBoletas();
        assertEquals(2,Listas.size());
        verify(detalleBoletaRepositorio).findAll();
        
    }

    @Test
    void testBuscarDetalleBoletaPorId() {
    	when(detalleBoletaRepositorio.findById(1)).thenReturn(Optional.of(detalleBoleta));
    	DetalleBoleta detBoleEnco = detalleBoletaServicio.obtenerDetalleBoletaById(1);
    	assertNotNull(detBoleEnco);
    	assertEquals("Perfume", detBoleEnco.getNombreProducto());
    	verify(detalleBoletaRepositorio).findById(1);
    }
    @Test
    void testDetalleBoletaNoBoleta() {
    	when(detalleBoletaRepositorio.findById(50)).thenReturn(Optional.empty());
    	DetalleBoleta detalleBoleta = detalleBoletaServicio.obtenerDetalleBoletaById(50);
    	assertNull(detalleBoleta, "Se espera nulo si no exites el detalle boleta");
    }
    @Test
    void editarDetalleBoleta() {
    	when(detalleBoletaRepositorio.findById(1)).thenReturn(Optional.of(detalleBoleta));
    	DetalleBoleta detalleEncontrada = detalleBoletaServicio.obtenerDetalleBoletaById(1);
    	assertNotNull(detalleEncontrada,"Se esperaba un Detalle Boleta");
    	assertEquals("Perfume", detalleEncontrada.getNombreProducto());
    	
    	detalleEncontrada.setNombreProducto("Colonia del raio makuin");
    	
    	when(detalleBoletaRepositorio.findById(detalleEncontrada.getDetalleBoletaId())).thenReturn(Optional.of(detalleEncontrada));
    	when(detalleBoletaRepositorio.save(any(DetalleBoleta.class))).thenReturn(detalleEncontrada);
    	
    	DetalleBoleta DetalleBoletaEdit = detalleBoletaServicio.editarDetalleBoletaById(detalleEncontrada.getDetalleBoletaId(), detalleEncontrada);
    	
    	assertEquals("Colonia del raio makuin", detalleEncontrada.getNombreProducto());
    	assertEquals(DetalleBoletaEdit.getDetalleBoletaId(), detalleBoleta.getDetalleBoletaId());
    	
    	verify(detalleBoletaRepositorio, times(2)).findById(1);
	    verify(detalleBoletaRepositorio).save(detalleEncontrada);
    }




	    
	   
}
