package com.Sucursal_perfulandia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Sucursal_perfulandia.entidad.Sucursal;
import com.Sucursal_perfulandia.repository.SucursalRepository;

import net.datafaker.Faker;


@Component
public class SucursalFaker implements CommandLineRunner{
	@Value("${dataloader.habilitado:false}")
	private boolean activado;
	
	@Autowired
	private SucursalRepository repositorio;
	@Override
	public void run(String... args) throws Exception {
		if(activado == false) {
			return;
		}
		
		Faker faker = new Faker();
		
		for(int i = 0; i < 31; i++) {
			Sucursal sucursal= new Sucursal();
			sucursal.setDireccion(faker.address().fullAddress());
			sucursal.setNumeroTelefono(faker.phoneNumber().phoneNumber());
			repositorio.save(sucursal);
		}
		
	}
}
