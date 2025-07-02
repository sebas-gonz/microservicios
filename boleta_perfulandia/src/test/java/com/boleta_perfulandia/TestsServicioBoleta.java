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
import org.mockito.MockitoAnnotations;
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
    	when(boletaRepository.save(any(Boleta.class))).thenReturn(boleta);//Simulamos que el repositorio ingrese una boleta
    	Boleta crearBoleta = boletaService.crearBoleta(boleta);//Ejecutamos la simulacion
    	assertNotNull(crearBoleta, "El objeto no puede ser null");//Se verifica que no sea null
    	assertEquals(1, crearBoleta.getBoletaId());//Se confirma si se ingreso la boleta
    }
    @Test //Buscar boleta
    void buscarBoletaId() {
    	when(boletaRepository.findById(1)).thenReturn(Optional.of(boleta));//Creamos una simulacion donde el repositorio busca la boleta
    	Boleta encBoleta = boletaService.boletaById(1);//Se ejecuta la simulacion
    	assertNotNull(encBoleta,"El objeto no puede ser nulo");//Confirmamos que el objeto no sea nulo
    	assertEquals(1, encBoleta.getBoletaId());//Confirmamos que la boleta se haya encontrado
    }
    @Test //Boleta no existente
    void boletaNoEncontrada() {
    	when(boletaRepository.findById(3)).thenReturn(Optional.empty());//Simulamos que el repositorio busque una boleta por una ID
    	Boleta boletaNoEnc = boletaService.boletaById(3);//Se ejecuta el la simulacion
    	assertNull(boletaNoEnc,"El objeto no puede ser null");//Confirmamos que sea null
    	
    }
    @Test //Listar Boletas
    void listarBoletas() {
    	List<Boleta> boletas = Arrays.asList(boleta, new Boleta());
    	when(boletaRepository.findAll()).thenReturn(boletas);
    	List<Boleta> listBoleta = boletaService.obtenerBoletas();
    	assertEquals(2, listBoleta.size());
    	
    }
    @Test //Test Editar Boleta
    void editarBoleta() {
    	when(boletaRepository.findById(boleta.getBoletaId())).thenReturn(Optional.of(boleta));//Simulamos que el repositorio busque una boleta por el id
    	Boleta encBoleta = boletaService.boletaById(boleta.getBoletaId());//Ejecutamos la simulacion
    	encBoleta.setNombreUsuario("Jose");//Actualizamos la boleta
    	when(boletaRepository.save(encBoleta)).thenReturn(encBoleta);//Simulamos que el repositorio guarde la nueva boleta
    	Boleta editBoleta = boletaService.editarBoletaById(encBoleta.getBoletaId(), encBoleta);//Ejecutamos la sumulacion
    	assertNotNull(editBoleta,"El objeto no puede ser nulo");//Confirmamos que el objeto no es nulo
    	assertEquals(editBoleta.getBoletaId(), boleta.getBoletaId());//Comprobamos que la boleta id sea igual
    	assertEquals("Jose", boleta.getNombreUsuario()); //Confirmamos que la boleta se haya editato
    	
    	
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
