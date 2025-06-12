package com.Sucursal_perfulandia.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.Sucursal_perfulandia.configuracion.PerfulandiaConfig;
import com.Sucursal_perfulandia.dto.BoletaDTO;
import com.Sucursal_perfulandia.dto.DetalleBoletaDTO;
import com.Sucursal_perfulandia.dto.DetallePedidoDTO;
import com.Sucursal_perfulandia.dto.EmpleadoDTO;
import com.Sucursal_perfulandia.dto.InventarioDTO;
import com.Sucursal_perfulandia.dto.PedidoDTO;
import com.Sucursal_perfulandia.dto.ProductoDTO;
import com.Sucursal_perfulandia.dto.UsuarioDTO;
import com.Sucursal_perfulandia.entidad.Sucursal;
import com.Sucursal_perfulandia.repository.SucursalRepository;


@Service
public class ServiceSucursal {
    @Autowired
    private SucursalRepository sucursalRepository;
    
    @Autowired
    private PerfulandiaConfig perfumelandiaConfig;
    
    public Sucursal agregarsuSucursal(Sucursal sucursal) {
        
        return sucursalRepository.save(sucursal);
    }
    public List<Sucursal> GetSucursal() {
    	List<Sucursal> sucursales = sucursalRepository.findAll();
        return sucursales.isEmpty() ? null : sucursales;
        
    }
    public Sucursal buscarIdSucu(int id){
       return sucursalRepository.findById(id).orElse(null);
    }
    public void eliminarSucursal(int idSucursal) {
        sucursalRepository.deleteById(idSucursal);
    }

    public Sucursal actualizarSucursal(Sucursal sucursal, Sucursal sucuBuscar) {
        if(sucursal.equals(null)){
            return null;
        }
        sucuBuscar.setDireccion(sucursal.getDireccion());
        sucuBuscar.setNumeroTelefono(sucursal.getNumeroTelefono());
        return sucursalRepository.save(sucuBuscar);

    }
    
    public List<BoletaDTO> boletasSurcursal(int sucursalId){
    	try {
    		String urlBoletas = "http://localhost:8083/boleta/sucursal/" + sucursalId;
    		
    		BoletaDTO[] boletas = perfumelandiaConfig.restTemplate().getForObject(urlBoletas, BoletaDTO[].class);
    		for(BoletaDTO boleta : boletas) {
    			DetalleBoletaDTO[] detalles = perfumelandiaConfig.restTemplate().getForObject("http://localhost:8088/api/detalle_boleta/boleta/" + boleta.getBoletaId(), DetalleBoletaDTO[].class);
    			boleta.setDetalleBoletas(Arrays.asList(detalles));
    			
    			UsuarioDTO usuario = perfumelandiaConfig.restTemplate().getForObject("http://localhost:8088/api/usuario/" + boleta.getUsuarioId(),UsuarioDTO.class);
    			boleta.setUsuario(usuario);
    			
    			EmpleadoDTO empleado = perfumelandiaConfig.restTemplate().getForObject("http://localhost:8088/api/empleado/" + boleta.getEmpleadoId(), EmpleadoDTO.class);
    			boleta.setEmpleado(empleado);
    		}
    		return boletas != null ? Arrays.asList(boletas) : null;
    	} catch (HttpClientErrorException e) {
			System.out.println("Error al encontrar las boletas de sucursal: " + sucursalId);
			return null;
		} catch(Exception e) {
			System.out.println("Erro al acceder al servicio: " + e.getMessage());
			return null;
		}
    }
    
    
    public List<InventarioDTO> inventariosSucursal(int sucursalId){
    	try {
    		InventarioDTO[] inventarios = perfumelandiaConfig.restTemplate().getForObject("http://localhost:8088/api/inventario " + sucursalId, InventarioDTO[].class);
    		for(InventarioDTO inventario : inventarios) {
    			ProductoDTO producto = perfumelandiaConfig.restTemplate().getForObject("http://localhost:8088/api/producto" + inventario.getProductoId(), ProductoDTO.class);
    			inventario.setProducto(producto);
    		}
    		return inventarios == null ? null : Arrays.asList(inventarios);
    		
    	} catch(HttpClientErrorException e){
    		System.out.println("Error al encontrar los inventarios de la sucursal: " + sucursalId);
    		return null;
    	} catch (Exception e){
    		System.out.println("Error al acceder al servicio: " + e.getMessage());
    		return null;
    	}
    }
    
    public List<EmpleadoDTO> empleadosSucursal(int sucursalId){
    	try {
    		String urlEmpleado = "http://localhost:8086/empleado/" + sucursalId + "/sucursal";
    		EmpleadoDTO[] empleados = perfumelandiaConfig.restTemplate().getForObject(urlEmpleado, EmpleadoDTO[].class);
    		return empleados != null ? Arrays.asList(empleados) : null;
    	} catch(HttpClientErrorException e) {
    		System.out.println("Error al encontrar los empleados de la sucursal: " + sucursalId);
    		return null;
    	} catch(Exception e){
    		System.out.println("Error al acceder al servicio: " + e.getMessage());
    		return null;
    	}
    }
   
    
    public List<PedidoDTO> pedidosPorSucursal(int sucursalId){
    	PedidoDTO[] pedidos = perfumelandiaConfig.restTemplate().getForObject("http://localhost:8087/pedido/sucursal/" + sucursalId, PedidoDTO[].class);
    	for(PedidoDTO pedido : pedidos) {
    		DetallePedidoDTO[] detalles = perfumelandiaConfig.restTemplate().getForObject("http://localhost:8085/detalle_pedido/pedido/" + pedido.getPedidoId(), DetallePedidoDTO[].class);
    		for(DetallePedidoDTO detalle : detalles) {
    			ProductoDTO producto = perfumelandiaConfig.restTemplate().getForObject("http://localhost:8088/api/producto/" + detalle.getProductoId(), ProductoDTO.class);
    			detalle.setProducto(producto);
    		}
    		pedido.setDetalles(Arrays.asList(detalles));
    	}
    	return pedidos != null ? Arrays.asList(pedidos) : null;
    }
    

}
