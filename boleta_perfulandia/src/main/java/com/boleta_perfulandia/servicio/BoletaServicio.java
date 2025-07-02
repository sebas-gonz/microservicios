package com.boleta_perfulandia.servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boleta_perfulandia.configuracion.PerfulandiaConfig;
import com.boleta_perfulandia.dto.BoletaDTO;
import com.boleta_perfulandia.dto.DetalleBoletaDTO;
import com.boleta_perfulandia.dto.EmpleadoDTO;
import com.boleta_perfulandia.dto.UsuarioDTO;
import com.boleta_perfulandia.entidades.Boleta;
import com.boleta_perfulandia.repositorio.BoletaRepositorio;

@Service
public class BoletaServicio {
	
	@Autowired
	private BoletaRepositorio boletaRepository;
	
	@Autowired
	private PerfulandiaConfig perfulandiaConfig;
	
	public Boleta crearBoleta(Boleta boleta) {
		return boletaRepository.save(boleta);
	}
	public Boleta crearBoletaPorPedido(BoletaDTO boletaDTO) {
		Boleta nueva = new Boleta();
		nueva.setEmpleadoId(boletaDTO.getEmpleadoId());
		nueva.setPedidoId(boletaDTO.getPedidoId());
		nueva.setSucursalId(boletaDTO.getSucursalId());
		nueva.setUsuarioId(boletaDTO.getUsuarioId());
		
		nueva.setNombreEmpleado(boletaDTO.getNombreEmpleado());
		nueva.setNombreSucursal(boletaDTO.getNombreSucursal());
		nueva.setNombreUsuario(boletaDTO.getNombreUsuario());
		nueva.setTotal(boletaDTO.getTotal());
		
		return boletaRepository.save(nueva);
	}
	
	public List<Boleta> obtenerBoletas(){
		return boletaRepository.findAll();
	}
	
	public Boleta boletaById(int id) {
		return boletaRepository.findById(id).orElse(null);
	}
	
	public void borrarBoletaById(int id) {
		boletaRepository.deleteById(id);
	}
	
	public Boleta editarBoletaById(int id, Boleta b) {
		Boleta boleta = boletaRepository.findById(id).orElse(null);
		if(boleta != null) {
			boleta.setTotal(b.getTotal());
			boleta.setEmpleadoId(b.getEmpleadoId());
			boleta.setNombreEmpleado(b.getNombreEmpleado());
			boleta.setNombreSucursal(b.getNombreSucursal());
			boleta.setNombreUsuario(b.getNombreUsuario());
			boleta.setPedidoId(b.getPedidoId());
			boleta.setSucursalId(b.getSucursalId());
			boleta.setUsuarioId(b.getUsuarioId());
			boletaRepository.save(boleta);
			return boleta;
		}
		
		return boleta;
	}
	
	public List<DetalleBoletaDTO> obtenerDetallesBoleta(int id){
		String url = "http://localhost:8084/detalle_boleta/boleta/" + id;
		DetalleBoletaDTO[] detalleBoletas = perfulandiaConfig.restTemplate().getForObject(url, DetalleBoletaDTO[].class);
		return detalleBoletas == null ? null : Arrays.asList(detalleBoletas);
	}
	
	public List<BoletaDTO> obtenerBoletaUsuario(int usuarioId){
		List<Boleta> boletas = boletaRepository.findByUsuarioId(usuarioId);
		System.out.println("boletas :" + boletas.size());
		List<BoletaDTO> boletasDTO = new ArrayList<>();
		for(Boleta boleta : boletas) {
			BoletaDTO boletaDTO = new BoletaDTO();
			boletaDTO.setBoletaId(boleta.getBoletaId());
			boletaDTO.setSucursalId(boleta.getSucursalId());
			boletaDTO.setUsuarioId(boleta.getUsuarioId());
			boletaDTO.setEmpleadoId(boleta.getEmpleadoId());
			
			boletaDTO.setTotal(boleta.getTotal());
			boletaDTO.setNombreSucursal(boleta.getNombreSucursal());
			boletaDTO.setNombreUsuario(boleta.getNombreUsuario());
			boletaDTO.setNombreEmpleado(boleta.getNombreEmpleado());
			
			String url = "http://localhost:8084/detalle_boleta/boleta/" + boleta.getBoletaId();
			DetalleBoletaDTO[] detalleBoletas = perfulandiaConfig.restTemplate().getForObject(url, DetalleBoletaDTO[].class);
			boletaDTO.setDetalleBoletas(Arrays.asList(detalleBoletas));
			boletasDTO.add(boletaDTO);
		}
		return boletasDTO == null ? null : boletasDTO;
	}
	public List<BoletaDTO> boletaSucursal(int sucursalId){
		List<Boleta> boletas = boletaRepository.findBySucursalId(sucursalId);
		List<BoletaDTO> boletasDTO = new ArrayList<>();
		for(Boleta boleta : boletas) {
			BoletaDTO boletaDTO = new BoletaDTO();
			boletaDTO.setBoletaId(boleta.getBoletaId());
			boletaDTO.setSucursalId(boleta.getSucursalId());
			boletaDTO.setUsuarioId(boleta.getUsuarioId());
			boletaDTO.setTotal(boleta.getTotal());
			boletaDTO.setNombreSucursal(boleta.getNombreSucursal());
			boletaDTO.setNombreUsuario(boleta.getNombreUsuario());
			boletaDTO.setEmpleadoId(boleta.getEmpleadoId());
			boletaDTO.setNombreEmpleado(boleta.getNombreEmpleado());
			String url = "http://localhost:8084/detalle_boleta/boleta/" + boleta.getBoletaId();
			DetalleBoletaDTO[] detalleBoletas = perfulandiaConfig.restTemplate().getForObject(url, DetalleBoletaDTO[].class);
			boletaDTO.setDetalleBoletas(Arrays.asList(detalleBoletas));
			boletasDTO.add(boletaDTO);
		}
		return boletas.isEmpty() ? null : boletasDTO;
	}
	public List<Integer> usuariosSucursal(int sucursalId){
		List<Boleta> boletas = boletaRepository.findBySucursalId(sucursalId);
		List<Integer> usuariosId = boletas.stream().map(Boleta::getBoletaId).distinct().collect(Collectors.toList());
		return boletas == null ? null : usuariosId;
	}
	
	public UsuarioDTO obtenerUsuariosPorId(int id){
		String url = "http://localhost:8080/usuario/" + id;
		UsuarioDTO usuario = perfulandiaConfig.restTemplate().getForObject(url, UsuarioDTO.class);
		return usuario;
	}
	public void actualizarBoleta(Map<String,Object> detalleBoleta) {
		int boletaId = (int) detalleBoleta.get("boletaId");
		int subtotal = (int) detalleBoleta.get("subtotal");
		int sucursalId = (int) detalleBoleta.get("sucursalId");
		int usuarioId = (int) detalleBoleta.get("usuarioId");
		int empleadoId = (int) detalleBoleta.get("empleadoId");
		
		String usuarioNombre = String.valueOf(detalleBoleta.get("usuarioNombre"));
		String sucursalNombre = String.valueOf(detalleBoleta.get("sucursalNombre"));
		
		String urlEmpleado = "http://localhost:8086/empleado/" + empleadoId;
		EmpleadoDTO empleado = perfulandiaConfig.restTemplate().getForObject(urlEmpleado, EmpleadoDTO.class);
		if(empleado == null) {
			throw new RuntimeException("Empleado no encontrado");
		}
		
		Boleta boleta = boletaRepository.findById(boletaId).orElse(null);
		if(boleta == null){
			Boleta nueva = new Boleta();
			
			nueva.setBoletaId(boletaId);
			nueva.setTotal(subtotal);
			nueva.setUsuarioId(usuarioId);
			nueva.setSucursalId(sucursalId);
			nueva.setNombreSucursal(sucursalNombre);
			nueva.setNombreUsuario(usuarioNombre);
			nueva.setEmpleadoId(empleadoId);
			nueva.setNombreEmpleado(empleado.getNombre());
			boletaRepository.save(nueva);
		}
	}
	public void recalcularTotal(int boletaid) {
		Boleta boleta = boletaRepository.findById(boletaid).orElse(null);
		if(boleta != null) {
			String urlDetalle = "http://localhost:8084/detalle_boleta/boleta/" + boletaid;
			DetalleBoletaDTO[] detalles = perfulandiaConfig.restTemplate().getForObject(urlDetalle, DetalleBoletaDTO[].class);
			int nuevoTotal = 0;
	        	if (detalles != null) {
		            for (DetalleBoletaDTO det : detalles) {
		                nuevoTotal += det.getSubtotal();
		            }
		        }
	    	boleta.setTotal(nuevoTotal);
	    	boletaRepository.save(boleta);
		}
	}
	
	public List<Boleta> boletasEmpleado(int empleadoId){
		List<Boleta> boletas = boletaRepository.findByEmpleadoId(empleadoId);
		if(boletas.isEmpty()) {
			return null;
		}
		return boletas;
	}
	
	public Boleta boletaByPedidoId(int pedidoId) {
		Boleta boleta = boletaRepository.findByPedidoId(pedidoId);
		return boleta;
	}
	
}
