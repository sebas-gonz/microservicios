package com.sucursal_perfulandia;

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

import com.Sucursal_perfulandia.Services.ServiceSucursal;
import com.Sucursal_perfulandia.entidad.Sucursal;
import com.Sucursal_perfulandia.repository.SucursalRepository;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
@ExtendWith(MockitoExtension.class)
public class TestServicioSucursal {
	@Mock
	private SucursalRepository sucursalRepository;

	@InjectMocks
	private ServiceSucursal sucursalServicio;

	private Sucursal sucursal;

	@BeforeEach
	void setup() {
		
	    sucursal = new Sucursal();
	    sucursal.setSucursalId(1);
	    sucursal.setNumeroTelefono("+56 9 1234 5678");
	    sucursal.setDireccion("Av. Siempre Viva 742, Santiago, Chile");
	}

	@Test //Este test verifica que se puedan crear y guardar sucursales en el servicio de Usuario.
	void testCrearSucursal() {
		//Simulacion de la maqueta del repositorio de sucursal. Debe guardar una clase sucursal y retornar el objeto sucursal
	    when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursal);
	    
	    //Injectamos la maqueta en el servicio de la sucursal
	    Sucursal creada = sucursalServicio.agregarsuSucursal(sucursal);
	    
	    //Coincidencia si la sucursal creada es distinta de null;
	    assertNotNull(creada);
	    
	    //Buscamos la coincidencia del numero de la sucursal creada
	    assertEquals("+56 9 1234 5678", creada.getNumeroTelefono());
	    
	    //Verificamos que el metodo "save" se haya usado una vez en el proceso
	    verify(sucursalRepository, times(1)).save(sucursal);
	}

	@Test //Sobre obtener una sucursal por el id
	void testObtenerSucursalPorId() {
		
		//Simulacion donde se obtiene una sucursal por el id 1, en este caso debe retornal la sucursal creada
	    when(sucursalRepository.findById(1)).thenReturn(Optional.of(sucursal));

	    //Injectamos la maqueta en el servicio de la sucursal
	    Sucursal encontrada = sucursalServicio.buscarIdSucu(1);
	    //Coincidencia si la sucursal creada es distinta de null;
	    assertNotNull(encontrada);
	    //Buscamos la coincidencia de la direccion de la sucursal creada
	    assertEquals("Av. Siempre Viva 742, Santiago, Chile", encontrada.getDireccion());
	    //Verificamos que el metodo "findById" se haya usado una vez en el proceso
	    verify(sucursalRepository).findById(1);
	}

	@Test //Para buscar una sucursal que no ha sido creada
	void testSucursalNoEncontrada() {
	    
	    when(sucursalRepository.findById(50)).thenReturn(Optional.empty()); //Simulamos la busqueda de una sucursal con el id 50
	
	    Sucursal resultado = sucursalServicio.buscarIdSucu(50); //Inyeccion de la maqueta en el servicio. Obtenemos el resultado

	    
	    assertNull(resultado, "Se esperaba null cuando no existe la sucursal"); //verificamos que el objeto sea nulo porque esa sucursal no ha sido creada

	
	}

	@Test //Para obtener todas las sucursales
	void testListarSucursales() {
	    List<Sucursal> sucursales = Arrays.asList(sucursal, new Sucursal()); //Creamos una lista con 2 sucursales para la simulacion
	    when(sucursalRepository.findAll()).thenReturn(sucursales); //Simulacion donde el repositorio debe obtener una lista igual a la sucursales

	    List<Sucursal> resultado = sucursalServicio.GetSucursal(); // Inyeccion de la maqueta en el servicio de sucursal, debe devolver la misma lista que surcursales

	    assertEquals(2, resultado.size()); //La coincidencia debe ser una lista de un tamaño de 2
	    verify(sucursalRepository, times(1)).findAll(); //Verificamos que el metodo debe haber sido llamado 1 vez en el proceso
	}

	@Test //Para simular una eliminacion de sucursal
	void testEliminarSucursal() {
		doNothing().when(sucursalRepository).deleteById(1);//Simulamos que la maqueta del repositorio elimine la sucursal con el id 1
		
	    sucursalServicio.eliminarSucursal(1); //ejecutamos la simulacion
	    
	    Sucursal eliminada = sucursalServicio.buscarIdSucu(1);//Obtenemos la sucursal que ha sido eliminada
	    assertNull(eliminada, "La sucursal no se ha eliminado"); //verificamos que la sucursal ha sido eliminada
	    
	    verify(sucursalRepository, times(1)).deleteById(1); //El metodo debe haber sido invocado 1 vez
	}

	@Test //Para simular la modificacion de una sucursal
	void testActualizarSucursal() {
	    when(sucursalRepository.findById(1)).thenReturn(Optional.of(sucursal)); //Simulamos que se busque una sucursal con el id 1 en el repositorio
	    
	    Sucursal encontrada = sucursalServicio.buscarIdSucu(1); //Obtenemos la sucursal buscada por el id 1
	    assertNotNull(encontrada, "Se debia haber encontrado la sucursal con el id 1"); //Verificamos que la sucursal encontrada no sea null
	    encontrada.setDireccion("direccion nueva"); //Modificamos la direccion de la sucursal
	    
	    when(sucursalRepository.findById(encontrada.getSucursalId())).thenReturn(Optional.of(encontrada)); // Simulamos nuevamente que se busque la sucursal con el id 1
	    when(sucursalRepository.save(any(Sucursal.class))).thenReturn(encontrada); // Simulamos que se guarde la sucursal encontrada
	    
	    Sucursal editada = sucursalServicio.actualizarSucursal(encontrada, encontrada.getSucursalId()); // Ejecutamos el metodo actualizar 


	    assertNotNull(editada); //Verficamos que la sucursal editada no sea null
	    assertEquals(editada.getSucursalId(), sucursal.getSucursalId());//Verificamos que la sucursal editada es la misma que la original;
	    assertEquals("direccion nueva", sucursal.getDireccion()); // Comprobamos que la sucursal original fue editada
	    // Verificamos que los metodos se hayan invocado las veces necesarias para el proceso
	    verify(sucursalRepository, times(1)).save(any(Sucursal.class)); //
	    verify(sucursalRepository,times(2)).findById(1);
	}
}
