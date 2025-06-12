package com.usuario_perfulandia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.usuario_perfulandia.entidad.Usuario;
import com.usuario_perfulandia.repositorio.UsuarioRepository;

import net.datafaker.Faker;


@Component
public class UsuarioFaker implements CommandLineRunner{
	@Value("${dataloader.habilitado:false}")
	private boolean activado;
	
	@Autowired
	private UsuarioRepository repositorio;
	@Override
	public void run(String... args) throws Exception {
		if(activado == false) {
			return;
		}
		
		Faker faker = new Faker();
		
		for(int i = 0; i < 30; i++) {
			Usuario usuario = new Usuario();
			usuario.setNombre(faker.name().firstName());
			usuario.setApellido(faker.name().lastName());
			usuario.setContraseña(faker.internet().password());
			usuario.setDireccion(faker.address().fullAddress());
			usuario.setCorreo(faker.internet().emailAddress());
			
			repositorio.save(usuario);
		}
		
	}
}
