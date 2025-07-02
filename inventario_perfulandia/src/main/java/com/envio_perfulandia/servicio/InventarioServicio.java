package com.envio_perfulandia.servicio;

import java.util.ArrayList;
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
	
	public Inventario inventarioById(int inventarioId) {
		Inventario inventario = repositorio.findById(inventarioId).orElse(null);
		return inventario;
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
	
	public List<Inventario> inventariosPorSucursalYProductos(int sucursalId,int producotId){
		List<Inventario> inventarios = repositorio.findBySucursalIdAndProductoId(sucursalId, producotId);
		return inventarios.isEmpty() ? null : inventarios;
	}
	 public List<Inventario> editarInventario(List<InventarioDTO> inventariosDTO){
		 List<Inventario> inventarios = new ArrayList<>();
		for(InventarioDTO inventarioDTO : inventariosDTO) {
			Inventario inventario = repositorio.findById(inventarioDTO.getInventarioId()).orElse(null);
			if(inventario == null) {
				return null;
			}
			inventario.setCantidadDisponible(inventarioDTO.getCantidadDisponible());
			repositorio.save(inventario);
			inventarios.add(inventario);
		}
		return inventarios;
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