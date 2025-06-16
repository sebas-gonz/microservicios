package com.envio_perfulandia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.envio_perfulandia.config.PerfulandiaConfig;
import com.envio_perfulandia.entidad.Inventario;
import com.envio_perfulandia.repositorio.InventarioRepository;

import net.datafaker.Faker;

@Component
public class InventarioFaker implements CommandLineRunner{

	@Autowired
	private InventarioRepository repositorioInventario;
    boolean Activo = false;


	
	@Override
	public void run(String... args) throws Exception {
		if(Activo != true) {
			return;
			
		}
		else {
			Faker faker = new Faker();
			for (int productoId = 1; productoId <= 30; productoId++) {
			    for (int sucursalId = 1; sucursalId <= 30; sucursalId++) {
			        Inventario inventario = new Inventario();
			        inventario.setProductoId(productoId);
			        inventario.setSucursalId(sucursalId);
			        inventario.setCantidadDisponible(faker.number().numberBetween(100, 200));
			        repositorioInventario.save(inventario);
			    }
			}
			
		}
		
	}
	
	

}
