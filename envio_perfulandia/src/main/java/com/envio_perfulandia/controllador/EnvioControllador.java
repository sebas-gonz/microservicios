package com.envio_perfulandia.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.envio_perfulandia.dto.EnvioDTO;
import com.envio_perfulandia.entidad.Envio;
import com.envio_perfulandia.servicio.EnvioServicio;

@RestController
@RequestMapping("/envio")
public class EnvioControllador {
	
	@Autowired
	private EnvioServicio servicio;
	
	@GetMapping("/")
	public ResponseEntity<List<Envio>> Envios(){
		List<Envio> envios = servicio.envios();
		return envios == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(envios);
	}
	
	@PostMapping("/pedido")
	public ResponseEntity<Envio> crearEnvio(@RequestBody EnvioDTO envioDTO){
		Envio envio = servicio.crearEnvio(envioDTO);
		return envio == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(envio);
	}
	
	@PostMapping("/")
	public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio){
		return ResponseEntity.ok(servicio.crearEnvio(envio));
	}
	
	@GetMapping("/{envioid}")
	public ResponseEntity<Envio> envioById(@PathVariable("envioid")int envioId){
		Envio envio = servicio.envioPorId(envioId);
		return envio == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(envio);
	}
	
	@PostMapping("/{envioid}")
	public ResponseEntity<Envio> editarEnvio(@PathVariable("envioid")int envioId,@RequestBody Envio envioAct){
		Envio envio = servicio.editarEnvio(envioId, envioAct);
		return envio == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(envio);
	}
}
