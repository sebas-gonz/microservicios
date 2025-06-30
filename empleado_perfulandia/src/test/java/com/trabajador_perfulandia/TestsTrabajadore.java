package com.trabajador_perfulandia;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.trabajador_perfulandia.entidad.Empleado;
import com.trabajador_perfulandia.repositorio.EmpleadoRepositorio;
import com.trabajador_perfulandia.servicio.empleadoServicio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
public class TestsTrabajadore {
	
	@Mock
    private EmpleadoRepositorio repositorio;

    @InjectMocks
    private empleadoServicio servicio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void crearEmpleado_debeRetornarEmpleadoGuardado() {
        Empleado empleado = new Empleado();
        empleado.setNombre("Juan");

        when(repositorio.save(any(Empleado.class))).thenReturn(empleado);

        Empleado resultado = servicio.crearEmpleado(empleado);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(repositorio).save(empleado);
    }

    @Test
    public void eliminarEmpleado_debeLlamarDeletePorId() {
        int empleadoId = 1;

        servicio.eliminarEmpleado(empleadoId);

        verify(repositorio, times(1)).deleteById(empleadoId);
    }

    @Test
    public void Empleados_debeRetornarListaDeEmpleados() {
        List<Empleado> lista = Arrays.asList(new Empleado(), new Empleado());

        when(repositorio.findAll()).thenReturn(lista);

        List<Empleado> resultado = servicio.Empleados();

        assertEquals(2, resultado.size());
    }

    @Test
    public void EmpleadoPorId_debeRetornarEmpleadoSiExiste() {
        Empleado emp = new Empleado();
        emp.setEmpleadoId(1);

        when(repositorio.findById(1)).thenReturn(Optional.of(emp));

        Empleado resultado = servicio.EmpleadoPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getEmpleadoId());
    }


}
