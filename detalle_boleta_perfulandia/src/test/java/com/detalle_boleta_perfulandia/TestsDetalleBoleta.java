package com.detalle_boleta_perfulandia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.detalle_boleta_perfulandia.servicio.DetalleBoletaServicio;
import com.detalle_boleta_perfulandia.entidades.DetalleBoleta;
import com.detalle_boleta_perfulandia.repositorio.DetalleBoletaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestsDetalleBoleta {
	 @Mock
	    private DetalleBoletaRepositorio detalleBoletaRepository;

	    @InjectMocks
	    private DetalleBoletaServicio detalleBoletaService;

	    @BeforeEach
	    public void setup(){
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetAllDetalles(){
	        DetalleBoleta d1 = new DetalleBoleta();
	        d1.setDetalleBoletaId(1);
	        d1.setNombreProducto("Perfume");

	        DetalleBoleta d2 = new DetalleBoleta();
	        d2.setDetalleBoletaId(2);
	        d2.setNombreProducto("Shampoo");

	        when(detalleBoletaRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

	        List<DetalleBoleta> resultado = detalleBoletaService.obtenerDetalleBoletas();
	        assertEquals(2, resultado.size());
	    }

	    @Test
	    public void testGetDetalleById(){
	        DetalleBoleta d = new DetalleBoleta();
	        d.setDetalleBoletaId(1);
	        d.setNombreProducto("Desodorante");

	        when(detalleBoletaRepository.findById(1)).thenReturn(Optional.of(d));

	        DetalleBoleta resultado = detalleBoletaService.obtenerDetalleBoletaById(1);
	        assertEquals("Desodorante", resultado.getNombreProducto());
	    }

	    @Test
	    public void testCreateDetalle(){
	        DetalleBoleta d = new DetalleBoleta();
	        d.setDetalleBoletaId(3);
	        d.setNombreProducto("Gel");

	        when(detalleBoletaRepository.save(d)).thenReturn(d);

	        DetalleBoleta resultado = detalleBoletaService.crearDetalleBoleta(d);
	        assertEquals("Gel", resultado.getNombreProducto());
	    }


	    @Test
	    public void testDeleteDetalle(){
	        int id = 6;

	        detalleBoletaService.eliminarDetalleBoletaById(id);

	        verify(detalleBoletaRepository).deleteById(id);
	    }

	    @Test
	    public void testGetDetallesByBoletaId(){
	        DetalleBoleta d = new DetalleBoleta();
	        d.setBoletaId(10);
	        d.setNombreProducto("Jabón");

	        when(detalleBoletaRepository.findByBoletaId(10)).thenReturn(Arrays.asList(d));

	        List<DetalleBoleta> resultado = detalleBoletaService.obtenerDetalleBoletasByIdBoleta(10);
	        assertEquals(1, resultado.size());
	        assertEquals("Jabón", resultado.get(0).getNombreProducto());
	    }
}
