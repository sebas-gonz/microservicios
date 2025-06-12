package com.producto_perfulandia.servicio;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.producto_perfulandia.configuracion.PerfulandiaConfig;
import com.producto_perfulandia.dto.DetalleBoletaDTO;
import com.producto_perfulandia.dto.InventarioDTO;
import com.producto_perfulandia.dto.SucursalDTO;
import com.producto_perfulandia.entidad.Producto;
import com.producto_perfulandia.repositorio.ProductoRepository;

@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired 
    private PerfulandiaConfig perfumelandiaConfig;

    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> GetProductos(){
        return productoRepository.findAll();
    }

    public Producto getProductoIdProducto(int id){
        return productoRepository.findById(id).orElse(null);
    }
    
    public void borrarProductoId(int id){
        productoRepository.deleteById(id);
    }
    
    public Producto actualizarProducto(Producto producto, Producto producbuscar) {
    	if(producto != null) {
    		producbuscar.setNombreProducto(producto.getNombreProducto());
    		producbuscar.setCategoria(producto.getCategoria());
    		producbuscar.setPrecio(producto.getPrecio());
    		return productoRepository.save(producbuscar);
    	}
    	return null;
    	
    }
    
    public List<DetalleBoletaDTO> boletasProducto(int productoId){
    	try {
    		String urlDetalleBoleta = "http://localhost:8084/detalle_boleta/producto/" + productoId;
    		DetalleBoletaDTO[] detalles = perfumelandiaConfig.restTemplate().getForObject(urlDetalleBoleta, DetalleBoletaDTO[].class);
    		return detalles == null ? null : Arrays.asList(detalles);
    		
    	} catch(HttpClientErrorException e) {
    		System.out.println("Error al obtener las boletas del producto: " + productoId);
    		return null;
    	} catch(Exception e) {
    		System.out.println("Errol al acceder al servicio: " + e.getMessage());
    		return null;
    	}
    }
    
    public List<InventarioDTO> inventarioPorProducto(int productoId){
    	
    	InventarioDTO[] inventarios = perfumelandiaConfig.restTemplate().getForObject("http://localhost:8090/inventario/producto/" + productoId, InventarioDTO[].class);
    	for(InventarioDTO inventario : inventarios) {
    		SucursalDTO sucursal = perfumelandiaConfig.restTemplate().getForObject("http://localhost:8082/sucursal/" + inventario.getSucursalId(), SucursalDTO.class);
    		inventario.setSucursal(sucursal);
    	}
    	return inventarios != null ? Arrays.asList(inventarios) : null;
    }
}
