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

    private final PerfulandiaConfig perfulandiaConfig;
	
	@Autowired
	private InventarioRepository repositorioInventario;
    boolean Activo = false;


    InventarioFaker(PerfulandiaConfig perfulandiaConfig) {
        this.perfulandiaConfig = perfulandiaConfig;
    }
    
	
	@Override
	public void run(String... args) throws Exception {
		if(Activo != true) {
			return;
			
		}
		else {
			Faker faker = new Faker();
			for(int i = 0; i < 21; i++) {
				Inventario inventario = new Inventario();
				inventario.setProductoId(faker.number().numberBetween(1, 25));
				inventario.setCantidadDisponible(faker.number().numberBetween(1, 200));
				inventario.setSucursalId(faker.number().numberBetween(1, 25));
				repositorioInventario.save(inventario);
			}
			
		}
		
	}
	
	

}
