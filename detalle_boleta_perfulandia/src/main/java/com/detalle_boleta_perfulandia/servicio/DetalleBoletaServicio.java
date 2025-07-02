package com.detalle_boleta_perfulandia.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detalle_boleta_perfulandia.configuracion.PerfulandiaConfig;
import com.detalle_boleta_perfulandia.dto.DetalleBoletaDTO;
import com.detalle_boleta_perfulandia.dto.ProductoDTO;
import com.detalle_boleta_perfulandia.entidades.DetalleBoleta;
import com.detalle_boleta_perfulandia.repositorio.DetalleBoletaRepositorio;

@Service
public class DetalleBoletaServicio {
	@Autowired
	private DetalleBoletaRepositorio detalleBoletaRepository;
	
	@Autowired
	private PerfulandiaConfig perfulandiaConfig;
	
	public DetalleBoleta crearDetalleBoleta(DetalleBoleta detalleBoleta) {
		return detalleBoletaRepository.save(detalleBoleta);
	}
	
	public List<DetalleBoleta> crearDetalleBoleta(List<DetalleBoletaDTO> detallesBoletaDTO) {
		List<DetalleBoleta> detallesboleta = new ArrayList<>();
		
		for(DetalleBoletaDTO detalle : detallesBoletaDTO) {
			DetalleBoleta nueva = new DetalleBoleta();
			nueva.setBoletaId(detalle.getBoletaId());
			nueva.setProductoId(detalle.getProductoId());
			nueva.setCantidad(detalle.getCantidad());
			nueva.setSubtotal(detalle.getCantidad());
			nueva.setNombreProducto(detalle.getNombreProducto());
			nueva.setSubtotal(detalle.getSubtotal());
			detallesboleta.add(nueva);
		}
		return detalleBoletaRepository.saveAll(detallesboleta);
		
	}
	public DetalleBoleta obtenerDetalleBoletaById(int id) {
		return detalleBoletaRepository.findById(id).orElse(null);
	}
	public List<DetalleBoleta> obtenerDetalleBoletas(){
		return detalleBoletaRepository.findAll();
	}
	public  void eliminarDetalleBoletaById(int id) {
		detalleBoletaRepository.deleteById(id);
	}
	public DetalleBoleta editarDetalleBoletaById(int id,DetalleBoleta d) {
		DetalleBoleta detalleBoleta = detalleBoletaRepository.findById(id).orElse(null);
		if(detalleBoleta != null) {

			detalleBoleta.setCantidad(d.getCantidad());

			detalleBoleta.setProductoId(d.getProductoId());
			detalleBoleta.setSubtotal(d.getSubtotal());
			detalleBoleta.setBoletaId(d.getBoletaId());
			detalleBoleta.setNombreProducto(d.getNombreProducto());
			detalleBoletaRepository.save(detalleBoleta);
			return detalleBoleta;
		}
		return detalleBoleta;
	}
	
	public List<DetalleBoleta> obtenerDetalleBoletasByIdBoleta(int id){
		List<DetalleBoleta> detalles = detalleBoletaRepository.findByBoletaId(id);
		if(detalles.isEmpty()) {
			return null;
		}
		return detalles;
	}
	
	public List<DetalleBoleta> boletasProducto(int productoId){
		List<DetalleBoleta> detalleBoletas = detalleBoletaRepository.findByProductoId(productoId);
		return detalleBoletas.isEmpty() ? null : detalleBoletas;
	}
	
	public void actualizarBoleta(DetalleBoleta detalleBoleta,int empleadoId) {
		
	    Map<String, Object> crearBoleta = new HashMap<>();
	    crearBoleta.put("boletaId", detalleBoleta.getBoletaId());
	    crearBoleta.put("subtotal", detalleBoleta.getSubtotal());
	    crearBoleta.put("empleadoId", empleadoId);

	    perfulandiaConfig.restTemplate().postForObject("http://localhost:8083/boleta/actualizar_total", crearBoleta, Void.class);
	}
}
