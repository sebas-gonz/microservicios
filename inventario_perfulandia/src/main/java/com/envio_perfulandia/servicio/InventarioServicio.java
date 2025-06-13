package com.envio_perfulandia.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.envio_perfulandia.dto.InventarioDTO;
import com.envio_perfulandia.dto.ProductoDTO;
import com.envio_perfulandia.dto.SucursalDTO;
import com.envio_perfulandia.entidad.Inventario;
import com.envio_perfulandia.repositorio.InventarioRepository;

@Service
public class InventarioServicio {
	
	@Autowired
	private InventarioRepository repositorio;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Inventario crearInventario(Inventario inventario) {
		return repositorio.save(inventario);
	}
	
	public List<Inventario> obtenerInventarios(){
		List<Inventario> inventarios = repositorio.findAll();
		return inventarios.isEmpty() ? null : inventarios;
	}
	
	public InventarioDTO inventarioById(int inventarioId) {
		Inventario inventario = repositorio.findById(inventarioId).orElse(null);
		InventarioDTO inventarioDTO = new InventarioDTO();
		
		ProductoDTO producto = restTemplate.getForObject("http://localhost:8081/producto/" + inventario.getProductoId(), ProductoDTO.class);
		SucursalDTO sucursal = restTemplate.getForObject("http://localhost:8082/sucursal/" + inventario.getSucursalId(), SucursalDTO.class);
		inventarioDTO.setInventarioId(inventarioId);
		inventarioDTO.setCantidadDisponible(inventario.getCantidadDisponible());
		inventarioDTO.setProducto(producto);
		inventarioDTO.setSucursal(sucursal);
		return inventarioDTO;
	}
	
	public Inventario editarInventario(int inventarioId, Inventario inventarioAct) {
		Inventario inventario = repositorio.findById(inventarioId).orElse(null);
		if(inventario != null ) {
			inventario.setSucursalId(inventarioAct.getSucursalId());
			inventario.setProductoId(inventarioAct.getProductoId());
			inventario.setCantidadDisponible(inventarioAct.getCantidadDisponible());
			
			return repositorio.save(inventario);
		}
		return inventario;
	}
	 public Inventario editarInventario(int inventarioId, InventarioDTO inventarioDTO){
		 Inventario inventario = repositorio.findById(inventarioId).orElse(null);
		if(inventario != null) {
			inventario.setCantidadDisponible(inventarioDTO.getCantidadDisponible());
			return repositorio.save(inventario);
		}
		return null;
	 }
	public void eliminarInventario(int inventarioId) {
		repositorio.deleteById(inventarioId);
	}
	
	public List<Inventario> inventarioBySucursalId(int sucursalId){
		List<Inventario> inventarios = repositorio.findBySucursalId(sucursalId);
		return inventarios.isEmpty() ? null : inventarios;
	}
	
	public List<Inventario> inventarioByProductoId(int productoId){
		List<Inventario> inventarios = repositorio.findByProductoId(productoId);
		return inventarios.isEmpty() ? null : inventarios;
	}
}