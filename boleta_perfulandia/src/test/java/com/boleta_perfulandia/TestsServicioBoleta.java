package com.boleta_perfulandia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.boleta_perfulandia.entidades.Boleta;
import com.boleta_perfulandia.repositorio.BoletaRepositorio;
import com.boleta_perfulandia.servicio.BoletaServicio;
public class TestsServicioBoleta {
	@Mock
    private BoletaRepositorio boletaRepository;

    @InjectMocks
    private BoletaServicio boletaService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBoletas(){
        Boleta b1 = new Boleta();
        b1.setBoletaId(1);
        b1.setNombreUsuario("Juan");

        Boleta b2 = new Boleta();
        b2.setBoletaId(2);
        b2.setNombreUsuario("Ana");

        when(boletaRepository.findAll()).thenReturn(Arrays.asList(b1, b2));

        List<Boleta> resultado = boletaService.obtenerBoletas();
        assertEquals(2, resultado.size());
    }

    @Test
    public void testGetBoletaById(){
        Boleta b = new Boleta();
        b.setBoletaId(1);
        b.setNombreUsuario("Pedro");

        when(boletaRepository.findById(1)).thenReturn(Optional.of(b));

        Boleta resultado = boletaService.boletaById(1);
        assertEquals("Pedro", resultado.getNombreUsuario());
    }

    @Test
    public void testCreateBoleta(){
        Boleta b = new Boleta();
        b.setBoletaId(1);
        b.setNombreUsuario("Maria");

        when(boletaRepository.save(b)).thenReturn(b);

        Boleta resultado = boletaService.crearBoleta(b);
        assertEquals("Maria", resultado.getNombreUsuario());
    }

    @Test
    public void testUpdateBoleta(){
        Boleta existente = new Boleta();
        existente.setBoletaId(1);
        existente.setNombreUsuario("Luis");

        Boleta actualizado = new Boleta();
        actualizado.setNombreUsuario("Carlos");

        when(boletaRepository.findById(1)).thenReturn(Optional.of(existente));
        when(boletaRepository.save(existente)).thenReturn(existente);

        Boleta resultado = boletaService.editarBoletaById(1, actualizado);
        assertEquals("Luis", resultado.getNombreUsuario());
    }

    @Test
    public void testDeleteBoleta(){
        int boletaId = 5;

        boletaService.borrarBoletaById(boletaId);

        verify(boletaRepository).deleteById(boletaId);
    }

    

}
