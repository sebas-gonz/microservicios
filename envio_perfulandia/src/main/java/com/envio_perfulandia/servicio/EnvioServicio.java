package com.envio_perfulandia.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.envio_perfulandia.dto.EnvioDTO;
import com.envio_perfulandia.entidad.Envio;
import com.envio_perfulandia.repositorio.EnvioRepositorio;

@Service
public class EnvioServicio {
	
	@Autowired
	private EnvioRepositorio repostiorio;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Envio crearEnvio(Envio envio) {
		return repostiorio.save(envio);
	}
	
	public Envio crearEnvio(EnvioDTO envioDTO) {
		Envio envio = new Envio();
		
		envio.setBoletaId(envioDTO.getBoletaId());
		envio.setPedidoId(envioDTO.getPedidoId());
		envio.setSucursalId(envioDTO.getSucursalId());
		envio.setUsuarioId(envioDTO.getUsuarioId());
		
		envio.setDireccionEnvio(envioDTO.getDireccionEnvio());
		envio.setFechaEntrega(envioDTO.getFechaEntrega());
		envio.setFechaEnvio(envioDTO.getFechaEnvio());
		envio.setEstado(envioDTO.getEstado());
		return repostiorio.save(envio);
	}
	
	public List<Envio> envios(){
		List<Envio> envios = repostiorio.findAll();
		if(envios.isEmpty()) {
			return null;
		}
		return envios;
	}
	
	public Envio envioPorId(int envioId) {
		Envio envio = repostiorio.findById(envioId).orElse(null);
		return envio;
	}
	
	public void eliminarEnvio(int envioId) {
		repostiorio.deleteById(envioId);
	}
	
	public Envio editarEnvio(int envioId,Envio envioAct) {
		Envio envio = repostiorio.findById(envioId).orElse(null);
		
		envio.setBoletaId(envioAct.getBoletaId());
		envio.setPedidoId(envioAct.getPedidoId());
		envio.setSucursalId(envioAct.getSucursalId());
		envio.setUsuarioId(envioAct.getUsuarioId());
		
		envio.setDireccionEnvio(envioAct.getDireccionEnvio());
		envio.setFechaEntrega(envioAct.getFechaEntrega());
		envio.setFechaEnvio(envioAct.getFechaEnvio());
		envio.setEstado(envioAct.getEstado());
		
		return repostiorio.save(envio);
	}
}
