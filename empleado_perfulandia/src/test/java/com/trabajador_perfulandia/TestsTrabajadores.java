package com.trabajador_perfulandia;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.trabajador_perfulandia.entidad.Empleado;
import com.trabajador_perfulandia.repositorio.EmpleadoRepositorio;
import com.trabajador_perfulandia.servicio.EmpleadoServicio;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class TestsTrabajadores {
	
	@Mock
    private EmpleadoRepositorio repositorio;

    @InjectMocks
    private EmpleadoServicio servicio;
    
    private Empleado empleado;

    @BeforeEach
    public void setUp() {
    	empleado = new Empleado();
    	empleado.setEmpleadoId(1);
    	empleado.setNombre("Martin");
    	empleado.setRut("22147000");
    	empleado.setCargo("Vendedor");
    	empleado.setSucursalId(1);
    	empleado.setCorreo("CorreoAleatorio@Gmail.com");
    }
    @Test //Test para ingresar Empleados
    void agregarEmpleado(){
    	//Simulamos el registro del repositorio que hacepta cualquier objeto de la entidad Empleado, en este caso retornara el objeto empleado
    	when(repositorio.save(any(Empleado.class))).thenReturn(empleado);
    	
    	Empleado crearEmp = servicio.crearEmpleado(empleado); //Se ejecuta la simulacion
    	
    	assertNotNull(crearEmp,"El objeto se deberia haber guradado y tambien se debio retornar");//Se verifica si el Empleado no sea nulo
    	assertEquals(1,crearEmp.getEmpleadoId());//Se verifica si el usuario se ha ingresado con el id 1
    	assertEquals("Vendedor", crearEmp.getCargo());
    }
    @Test //Buscar empleado por id
    void buscarEmpPorId() {
    	when(repositorio.findById(1)).thenReturn(Optional.of(empleado)); //Simulamos para que el repositorio busque por el ID 1
    	Empleado empleadoBus = servicio.EmpleadoPorId(1);//Se ejecuta la simulacion
    	assertNotNull(empleadoBus, "El empleado no deberia de ser nulo");//Verificamos que el objeto no sea tipo null
    	assertEquals(1,empleadoBus.getEmpleadoId());//Se confirma si el empleado se haya encontrado
    }
    @Test //Buscar empleado no ingresado
    void BusEmpleadoNoCreado() {
    	when(repositorio.findById(3)).thenReturn(Optional.empty()); //Se simula el repositorio para buscar un empleado con el id 3
    	Empleado noEnc = servicio.EmpleadoPorId(3);//Iniciamos la simulacion
    	assertNull(noEnc, "El empleado no creado deberia ser nulo");//Se confirma si el empleado  sea nulo
    	
    	
    }
    @Test //Listar todos los empleados
    void ListarEmpleados() {
    	List<Empleado> empleados = Arrays.asList(empleado, new Empleado()); //Creamos una lista con el empleado inicial y los instanciamos
    	when(repositorio.findAll()).thenReturn(empleados);//Simulamos para que el repositorio traiga una lista
    	List<Empleado> listaEmpleados = servicio.Empleados();//Se ejecuta la simulacion
    	assertEquals(2,listaEmpleados.size());//Comprobamos que la lista contenga 2 elementos
    	
    }
    @Test //Editar un empleado
    void editarEmpledo() {
    	when(repositorio.findById(empleado.getEmpleadoId())).thenReturn(Optional.of(empleado));//Simulamos que el repositorio encuentre el empleado a editar
    	Empleado encEmpleado = servicio.EmpleadoPorId(empleado.getEmpleadoId());//Ejecutamos la simulacion
    	
    	encEmpleado.setNombre("Andres");//Editamos el empleado
    	
    	when(repositorio.save(encEmpleado)).thenReturn(encEmpleado);//Simulamos que el repositorio guarde el empleado editado
    	
    	Empleado editEmpl = servicio.editarEmpleado(encEmpleado, encEmpleado.getEmpleadoId()); // Se ejecuta la Actualizacion
    	
    	assertNotNull(editEmpl,"Empleado no deberia ser nulo"); //Verificamos que el prodcuto no sea nulo
    	assertEquals(editEmpl.getEmpleadoId(), empleado.getEmpleadoId());//Verificamos que el empleado sea el mismo que editamos
    	assertEquals("Andres", empleado.getNombre());//Verificamos si el producto original se  haya editado
    	
    	
    }
    @Test//Test de eliminar empleado
    void EliminarEmpleado() {
    	doNothing().when(repositorio).deleteById(1); //Simulamos que en el repositorio se haya borrado un empleado
    	
    	servicio.eliminarEmpleado(1); //Ejecutamnos la simulacion
    	
    	Empleado elimEmpleado = servicio.EmpleadoPorId(1);//Buscamos el empleado borrado
    	assertNull(elimEmpleado,"Empleado borrado correctamente");//Aqui verificamos si el empleado se haya borrado
    	verify(repositorio, times(1)).deleteById(1);;//El metodo test debe de haber sido solo una vez
    	
    }

}
