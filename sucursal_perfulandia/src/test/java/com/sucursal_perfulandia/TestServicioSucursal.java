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
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.Sucursal_perfulandia.Services.ServiceSucursal;
import com.Sucursal_perfulandia.entidad.Sucursal;
import com.Sucursal_perfulandia.repository.SucursalRepository;

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

	@Test
	void testCrearSucursal() {
	    when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursal);

	    Sucursal creada = sucursalServicio.agregarsuSucursal(sucursal);

	    assertNotNull(creada);
	    assertEquals("+56 9 1234 5678", creada.getNumeroTelefono());
	    verify(sucursalRepository, times(1)).save(sucursal);
	}

	@Test
	void testObtenerSucursalPorId() {
	    when(sucursalRepository.findById(1)).thenReturn(Optional.of(sucursal));

	  
	    Sucursal encontrada = sucursalServicio.buscarIdSucu(1);

	    assertNotNull(encontrada);
	    assertEquals("Av. Siempre Viva 742, Santiago, Chile", encontrada.getDireccion());
	    verify(sucursalRepository).findById(1);
	}

	@Test
	void testSucursalNoEncontrada() {
	    when(sucursalRepository.findById(50)).thenReturn(Optional.empty());

	
	    Sucursal resultado = sucursalServicio.buscarIdSucu(50);

	  
	    assertNull(resultado, "Se esperaba null cuando no existe la sucursal");

	
	}

	@Test
	void testListarSucursales() {
	    List<Sucursal> sucursales = Arrays.asList(sucursal, new Sucursal());
	    when(sucursalRepository.findAll()).thenReturn(sucursales);

	    List<Sucursal> resultado = sucursalServicio.GetSucursal();

	    assertEquals(2, resultado.size());
	    verify(sucursalRepository, times(1)).findAll();
	}

	@Test
	void testEliminarSucursal() {

	    doNothing().when(sucursalRepository).deleteById(1);

	    sucursalServicio.eliminarSucursal(1);
	    verify(sucursalRepository, times(1)).deleteById(1);
	}

	@Test
	void testActualizarSucursal() {
	    when(sucursalRepository.findById(1)).thenReturn(Optional.of(sucursal));
	    when(sucursalRepository.save(any(Sucursal.class))).thenAnswer(i -> i.getArgument(0));

	    Sucursal datosActualizados = new Sucursal();
	    datosActualizados.setDireccion("Nueva dirección 123");
	    datosActualizados.setNumeroTelefono("+56 9 9999 9999");
	    datosActualizados.setSucursalId(1);

	    Sucursal actualizada = sucursalServicio.actualizarSucursal(datosActualizados,datosActualizados.getSucursalId());

	    assertNotNull(actualizada);
	    assertEquals("Nueva dirección 123", actualizada.getDireccion());
	    assertEquals("+56 9 9999 9999", actualizada.getNumeroTelefono());
	    verify(sucursalRepository, times(1)).save(any(Sucursal.class));
	}
}
