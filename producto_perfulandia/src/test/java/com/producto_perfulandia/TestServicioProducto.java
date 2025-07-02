package com.producto_perfulandia;

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

import com.producto_perfulandia.entidad.Producto;
import com.producto_perfulandia.repositorio.ProductoRepository;
import com.producto_perfulandia.servicio.ProductoServicio;
@ExtendWith(MockitoExtension.class)
public class TestServicioProducto {
	@Mock
	private ProductoRepository repositorio;
	
	@InjectMocks
	private ProductoServicio servicio;
	
	private Producto producto;
	
	@BeforeEach
	void setUp() {
		producto = new Producto();
		producto.setCategoria("perfume");
		producto.setNombreProducto("Antonio Banderas");
		producto.setPrecio(200);
		producto.setIdproducto(1);
	}
	
	@Test // para crear y guardar un producto
	void testCrearProducto() { 
		/*Simulamos que el repositorio pueda guardar cualquier objeto de la clase Producto y que en este caso retorne el objeto producto */
		when(repositorio.save(any(Producto.class))).thenReturn(producto);
		
		Producto creado = servicio.agregarProducto(producto); //Ejecutamos la simulacion
		
		assertNotNull(creado, "El objeto deberia haberse guardado y retornado"); //Verificamos que el Producto creado sea distinto a null;
		assertEquals(1, creado.getIdproducto()); //Verficamos que el id del Producto creado es 1
	}
	
	@Test // para buscar un producto por el id
	void testBuscarProducto() {
		when(repositorio.findById(1)).thenReturn(Optional.of(producto)); //Simulamos que el repositorio busque un producto con el id 1
		Producto encontrado = servicio.getProductoIdProducto(1); //ejecutamos la somulacion
		
		assertNotNull(producto, "El producto deberia no ser nulo"); //Verificamos que el objeto no sea nulo
		assertEquals(1,encontrado.getIdproducto()); //Vemos si el id del producto es igual a la de la simulacion
	}
	
	@Test // buscar un producto que no ha sido creado
	void testProductoNoEncontrado() {
		when(repositorio.findById(3)).thenReturn(Optional.empty()); //simulamos que el repositorio busque un producto con un id no existente
		Producto encontrado = servicio.getProductoIdProducto(3); // ejecutamos la simulacion
		
		assertNull(encontrado, "El producto no encontrado debe ser nulo"); //Verificamos que el Producto encontrado sea null
		
	}
	
	@Test // obtener lista de todos los productos
	void testObtenerProductos() {
		List<Producto> productos = Arrays.asList(producto, new Producto()); // Creamos una lista con el producto creado inicialmente e instanciamos uno nuevo
		when(repositorio.findAll()).thenReturn(productos); // Simulamos que el repositorio retorne esta lista de productos
		
		List<Producto> productosEncontrados = servicio.GetProductos(); //Ejecutamos la simulacion
		
		assertEquals(2, productosEncontrados.size()); //Comprobamos que la lista contenga 2 elementos
		
	}
	
	@Test // editar un producto
	void testEditarProducto() {
		when(repositorio.findById(producto.getIdproducto())).thenReturn(Optional.of(producto)); //Simulamos que el repositorio encuentre el producto a editar
		Producto encontrado = servicio.getProductoIdProducto(producto.getIdproducto()); //Ejecutamos la simulacion
		
		encontrado.setNombreProducto("test"); // editamos el producto
		
		when(repositorio.save(encontrado)).thenReturn(encontrado); // Simulamos que el repositorio guarde el producto
		Producto editado = servicio.actualizarProducto(encontrado, encontrado.getIdproducto()); //Ejecutamos la actualizacion
		
		assertNotNull(editado,"El producto no ha sido actualizado"); //Verificamos que el Producto editado no sea nulo
		assertEquals(editado.getIdproducto(), producto.getIdproducto()); //Verificamos que el Producto original sea el mismo que el editado
		assertEquals("test", producto.getNombreProducto()); //Verificamos si el Producto original ha sido editado	
	}
	
	@Test //Para simular una eliminacion de producto
	void testEliminarSucursal() {

		doNothing().when(repositorio).deleteById(1);//Simulamos que la maqueta del producto elimine la sucursal con el id 1

	    servicio.borrarProductoId(1); //ejecutamos la simulacion
	    
	    Producto eliminado = servicio.getProductoIdProducto(1);
	    assertNull(eliminado,"El producto no ha sido eliminado"); //Verificamos que el producto haya sido eliminado
	    
	    verify(repositorio, times(1)).deleteById(1); //El metodo debe haber sido invocado 1 vez
	}

	
}
