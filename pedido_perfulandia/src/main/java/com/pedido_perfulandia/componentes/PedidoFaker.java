package com.pedido_perfulandia.componentes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pedido_perfulandia.dto.DetallePedidoDTO;
import com.pedido_perfulandia.dto.PedidoDTO;
import com.pedido_perfulandia.entidad.Pedido;
import com.pedido_perfulandia.repositorio.PedidoRespositorio;
import com.pedido_perfulandia.servicio.PedidoServicio;

import net.datafaker.Faker;
@Component
public class PedidoFaker implements CommandLineRunner{
	
	
	@Autowired
	private PedidoServicio servicio;
	
	@Autowired 
	
	private PedidoRespositorio repositorio;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Boolean activar = false;
		if(activar != true) {
			return;
		}
		
		
		Faker faker = new Faker();
		String[] estadoPedido = {"Pendiente","Preparado","Enviado"};
		for(int i = 0; i < 2; i++) {
			PedidoDTO pedidoDTO = new PedidoDTO();

	        pedidoDTO.setEstado(estadoPedido[faker.number().numberBetween(0, 3)]); 

	        List<DetallePedidoDTO> detalles = new ArrayList<>();

	        for (int j = 0; j < faker.number().numberBetween(1, 11); j++) {
	            DetallePedidoDTO detalle = new DetallePedidoDTO();
	            detalle.setCantidad(faker.number().numberBetween(1, 21)); 
	            detalle.setProductoId(faker.number().numberBetween(1, 31)); 
	
	            detalles.add(detalle);
	        }

	        pedidoDTO.setDetalles(detalles);
	        pedidoDTO.setSucursalId(faker.number().numberBetween(1, 31));
	        pedidoDTO.setUsuarioId(faker.number().numberBetween(1, 31));

	        servicio.crearPedido(pedidoDTO);
			
		}
	}

}
