package com.detalle_boleta_perfulandia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.detalle_boleta_perfulandia.repositorio.DetalleBoletaRepositorio;
import com.detalle_boleta_perfulandia.servicio.DetalleBoletaServicio;

import net.datafaker.Faker;


@Component
public class DetalleBoletaFaker implements CommandLineRunner{
	@Value("${dataloader.habilitado:false}")
	private boolean activado;
	
	@Autowired
	private DetalleBoletaRepositorio repositorio;
	@Override
	public void run(String... args) throws Exception {
		if(activado == false) {
			return;
		}
		
		Faker faker = new Faker();
		
		for(int i = 1; i < 31; i++) {
			
		}
		
	}
}
