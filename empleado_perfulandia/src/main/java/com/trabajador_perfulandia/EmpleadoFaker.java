package com.trabajador_perfulandia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.trabajador_perfulandia.entidad.Empleado;
import com.trabajador_perfulandia.repositorio.EmpleadoRepositorio;

import net.datafaker.Faker;


@Component
public class EmpleadoFaker implements CommandLineRunner{
	@Value("${dataloader.habilitado:false}")
	private boolean activado;
	
	@Autowired
	private EmpleadoRepositorio empleadoRepositorio;
	@Override
	public void run(String... args) throws Exception {
		if(activado == false) {
			return;
		}
		
		Faker faker = new Faker();
		
		for(int i = 0; i < 30; i++) {
			Empleado emp = new Empleado();
			emp.setNombre(faker.name().fullName());
			emp.setCorreo(faker.internet().emailAddress());
			emp.setCargo(faker.job().position());
			emp.setSucursalId(faker.number().numberBetween(1, 32));
			emp.setRut(faker.idNumber().valid());
			empleadoRepositorio.save(emp);
		}
		
	}
}
