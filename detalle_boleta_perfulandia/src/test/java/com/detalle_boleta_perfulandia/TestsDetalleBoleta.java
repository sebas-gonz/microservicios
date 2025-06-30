package com.detalle_boleta_perfulandia;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.detalle_boleta_perfulandia.entidades.DetalleBoleta;
import com.detalle_boleta_perfulandia.repositorio.DetalleBoletaRepositorio;
import com.detalle_boleta_perfulandia.servicio.DetalleBoletaServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestsDetalleBoleta {
	@Mock
    private DetalleBoletaRepositorio detalleBoletaRepositorio;

    @InjectMocks
    private DetalleBoletaServicio detalleBoletaServicio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void crearDetalleBoleta_debeGuardarYRetornarDetalle() {
        DetalleBoleta detalle = new DetalleBoleta();
        when(detalleBoletaRepositorio.save(detalle)).thenReturn(detalle);

        DetalleBoleta resultado = detalleBoletaServicio.crearDetalleBoleta(detalle);

        assertNotNull(resultado);
        verify(detalleBoletaRepositorio, times(1)).save(detalle);
    }

    @Test
    public void eliminarDetalleBoleta_debeLlamarRepositorio() {
        int id = 1;

        detalleBoletaServicio.eliminarDetalleBoletaById(id);

        verify(detalleBoletaRepositorio, times(1)).deleteById(id);
    }

    @Test
    public void detalleBoletas_debeRetornarLista() {
        DetalleBoleta d1 = new DetalleBoleta();
        DetalleBoleta d2 = new DetalleBoleta();
        List<DetalleBoleta> lista = Arrays.asList(d1, d2);
        when(detalleBoletaRepositorio.findAll()).thenReturn(lista);

        List<DetalleBoleta> resultado = detalleBoletaServicio.obtenerDetalleBoletas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    public void detalleBoletaById_debeRetornarDetalleSiExiste() {
        int id = 1;
        DetalleBoleta detalle = new DetalleBoleta();
        when(detalleBoletaRepositorio.findById(id)).thenReturn(Optional.of(detalle));

        DetalleBoleta resultado = detalleBoletaServicio.obtenerDetalleBoletaById(id);

        assertNotNull(resultado);
        assertEquals(detalle, resultado);
    }

    @Test
    public DetalleBoleta editarDetalleBoletaById(int id, DetalleBoleta nuevo) {
        DetalleBoleta existente = detalleBoletaRepositorio.findById(id).orElse(null);
        if (existente == null) return null;

        existente.setCantidad(nuevo.getCantidad());
        existente.setSubtotal(nuevo.getSubtotal());

        return detalleBoletaRepositorio.save(existente);
    }
    public List<DetalleBoleta> boletasProducto(int productoId) {
        List<DetalleBoleta> detalleBoletas = detalleBoletaRepositorio.findByProductoId(productoId);
        return detalleBoletas.isEmpty() ? null : detalleBoletas;
    }



	    
	   
}
