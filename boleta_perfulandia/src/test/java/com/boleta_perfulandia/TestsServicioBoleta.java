package com.boleta_perfulandia;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

import com.boleta_perfulandia.entidades.Boleta;
import com.boleta_perfulandia.repositorio.BoletaRepositorio;
import com.boleta_perfulandia.servicio.BoletaServicio;

@ExtendWith(MockitoExtension.class)
public class TestsServicioBoleta {
	@Mock
    private BoletaRepositorio boletaRepository;

    @InjectMocks
    private BoletaServicio boletaService;
    private Boleta boleta;
    @BeforeEach
    public void setup(){
       boleta = new Boleta();
       boleta.setBoletaId(1);
       boleta.setNombreUsuario("Andrea");
       boleta.setNombreSucursal("Alameda");
       boleta.setNombreEmpleado("Maria");
       boleta.setUsuarioId(1);
       boleta.setSucursalId(1);
       boleta.setEmpleadoId(1);
       boleta.setPedidoId(1);
       boleta.setTotal(10000);
      
        
    }
    @Test//Agregar bopleta
    void agregarBoleta() {
    	when(boletaRepository.save(any(Boleta.class))).thenReturn(boleta);
    	Boleta crearBoleta = boletaService.crearBoleta(boleta);
    	assertNotNull(crearBoleta, "El objeto no puede ser null");
    	assertEquals(1, crearBoleta.getBoletaId());
    }
    @Test //Buscar boleta
    void buscarBoletaId() {
    	when(boletaRepository.findById(1)).thenReturn(Optional.of(boleta));
    	Boleta encBoleta = boletaService.boletaById(1);
    	assertNotNull(encBoleta,"El objeto no puede ser nulo");
    	assertEquals(1, encBoleta.getBoletaId());
    }
    @Test //Boleta no existente
    void boletaNoEncontrada() {
    	when(boletaRepository.findById(3)).thenReturn(Optional.empty());
    	Boleta boletaNoEnc = boletaService.boletaById(3);
    	assertNull(boletaNoEnc,"El objeto no puede ser null");
    	
    }
    @Test 
    void listarBoletas() {
    	List<Boleta> boletas = Arrays.asList(boleta, new Boleta());
    	when(boletaRepository.findAll()).thenReturn(boletas);
    	List<Boleta> listBoleta = boletaService.obtenerBoletas();
    	assertEquals(2, listBoleta.size());
    	
    }
    @Test 
    void editarBoleta() {
    	when(boletaRepository.findById(boleta.getBoletaId())).thenReturn(Optional.of(boleta));
    	Boleta encBoleta = boletaService.boletaById(boleta.getBoletaId());
    	encBoleta.setNombreUsuario("Jose");
    	when(boletaRepository.save(encBoleta)).thenReturn(encBoleta);
    	Boleta editBoleta = boletaService.editarBoletaById(encBoleta.getBoletaId(), encBoleta);
    	assertNotNull(editBoleta,"El objeto no puede ser nulo");
    	assertEquals(editBoleta.getBoletaId(), boleta.getBoletaId());
    	assertEquals("Jose", boleta.getNombreUsuario()); 
    	
    	
    }
    @Test//Test de eliminar una boleta
    void EliminarEmpleado() {
    	doNothing().when(boletaRepository).deleteById(1); //Simulamos que en el repositorio se haya borrado una boleta
    	
    	boletaService.borrarBoletaById(1); //Ejecutamnos la simulacion
    	
    	Boleta elimBoleta = boletaService.boletaById(1);//Buscamos la boleta borrada
    	assertNull(elimBoleta,"Empleado borrado correctamente");//Aqui verificamos si ña boleta se haya borrado
    	verify(boletaRepository, times(1)).deleteById(1);;//El metodo test debe de haber sido solo una vez
    	
    }
}
