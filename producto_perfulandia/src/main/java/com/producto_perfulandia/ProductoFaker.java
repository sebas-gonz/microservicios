package com.producto_perfulandia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.producto_perfulandia.entidad.Producto;
import com.producto_perfulandia.repositorio.ProductoRepository;

import net.datafaker.Faker;


@Component
public class ProductoFaker implements CommandLineRunner{
	@Value("${dataloader.habilitado:false}")
	private boolean activado;
	
	@Autowired
	private ProductoRepository repositorio;
	@Override
	public void run(String... args) throws Exception {
		if(activado == false) {
			return;
		}
		
		Faker faker = new Faker();
		
		for(int i = 0; i < 30; i++) {
			Producto producto = new Producto();
			producto.setNombreProducto(faker.commerce().productName());
			producto.setCategoria(faker.commerce().department());
			producto.setPrecio(faker.number().numberBetween(100, 1001));
			repositorio.save(producto);
		}
		
	}
}
