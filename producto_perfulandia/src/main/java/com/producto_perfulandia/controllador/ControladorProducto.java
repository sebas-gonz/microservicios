package com.producto_perfulandia.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.producto_perfulandia.dto.DetalleBoletaDTO;
import com.producto_perfulandia.dto.InventarioDTO;
import com.producto_perfulandia.entidad.Producto;
import com.producto_perfulandia.servicio.ProductoServicio;

@RestController
@RequestMapping("/producto")
public class ControladorProducto {
    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/")
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoServicio.GetProductos();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);

    }  
    @GetMapping("/{id}")
    public ResponseEntity<Producto> CargarProducto(@PathVariable("id")int id){
        Producto producto = productoServicio.getProductoIdProducto(id);
        if(producto.equals(null)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(producto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> BorrarProducto(@PathVariable("id")int id){
        Producto producto = productoServicio.getProductoIdProducto(id);
        if(producto.equals(null)){
            return ResponseEntity.notFound().build();

        }
        productoServicio.borrarProductoId(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto){
        Producto NewProducto = productoServicio.agregarProducto(producto);
        return ResponseEntity.ok(NewProducto);  
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<Producto> ActualizarProducto(@RequestBody Producto producto, @PathVariable("id")int id){
    	Producto producbuscar = productoServicio.getProductoIdProducto(id);
    	if(producbuscar == null) {
    		return ResponseEntity.notFound().build();
    	}
    	productoServicio.actualizarProducto(producto, producbuscar);
    	return ResponseEntity.ok(producbuscar);
    }
    
    @GetMapping("/{productoid}/detalle")
    public ResponseEntity<List<DetalleBoletaDTO>> boletasProducto(@PathVariable("productoid")int productoId){
    	List<DetalleBoletaDTO> detalles = productoServicio.boletasProducto(productoId);
    	return detalles == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(detalles);
    }
    
    @GetMapping("/{productoid}/inventario")
    public ResponseEntity<List<InventarioDTO>> inventariosPorProducto(@PathVariable("productoid")int productoId){
    	List<InventarioDTO> inventarios = productoServicio.inventarioPorProducto(productoId);
    	return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.noContent().build();
    }
    
}


