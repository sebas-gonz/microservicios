package com.pedido_perfulandia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.pedido_perfulandia.dto.DetallePedidoDTO;
import com.pedido_perfulandia.dto.InventarioDTO;
import com.pedido_perfulandia.dto.PedidoDTO;
import com.pedido_perfulandia.dto.SucursalDTO;
import com.pedido_perfulandia.dto.UsuarioDTO;
import com.pedido_perfulandia.entidad.Pedido;
import com.pedido_perfulandia.repositorio.PedidoRespositorio;
import com.pedido_perfulandia.servicio.PedidoServicio;

@ExtendWith(MockitoExtension.class)
public class TestPedido {
	@Mock
	private PedidoRespositorio repositorio;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private PedidoServicio servicio; 

	
	private PedidoDTO pedidoDto;
	
	private Pedido pedido;
	
	private Pedido crearPedido(PedidoDTO pedidoDto) {
		pedido = new Pedido();
		pedido.setPedidoId(pedidoDto.getPedidoId());
		pedido.setSucursalId(pedidoDto.getSucursalId());
		pedido.setUsuarioId(pedidoDto.getUsuarioId());
		
		return pedido;
	}
	
	@BeforeEach
	void setUp() {
		
		pedidoDto = new PedidoDTO();
		pedidoDto.setPedidoId(1);
		pedidoDto.setSucursalId(1);
		pedidoDto.setUsuarioId(1);
		List<DetallePedidoDTO> detalles = new ArrayList<>();
		DetallePedidoDTO detalle1 = new DetallePedidoDTO();
		DetallePedidoDTO detalle2 = new DetallePedidoDTO();
		
		detalle1.setPedidoId(pedidoDto.getPedidoId());
		detalle1.setProductoId(1);
		detalle1.setCantidad(10);
		detalles.add(detalle1); detalles.add(detalle2);
		detalle2.setPedidoId(pedidoDto.getPedidoId());
		detalle2.setCantidad(2);
		detalle2.setProductoId(1);
		
		pedidoDto.setDetalles(detalles);
	}
	
	@Test
	void testCrearPedido() {
		InventarioDTO inventarioDto = new InventarioDTO();
		inventarioDto.setProductoId(1);
	    inventarioDto.setCantidadDisponible(100);
		
	    when(restTemplate.getForObject(
	            eq("http://localhost:8088/api/inventario/1/1"),
	            eq(InventarioDTO[].class))
	    ).thenReturn(
	        new InventarioDTO[]{inventarioDto},
	        new InventarioDTO[]{inventarioDto}
	    );
		
	    when(repositorio.save(any(Pedido.class))).thenAnswer(invoc -> {
	        Pedido pedido = invoc.getArgument(0);
	        pedido.setPedidoId(1);
	        return pedido;
	    	});
	    when(restTemplate.getForObject(eq("http://localhost:8088/api/usuario/1"), eq(UsuarioDTO.class)))
        	.thenReturn(new UsuarioDTO());

	    when(restTemplate.getForObject(eq("http://localhost:8088/api/sucursal/1"), eq(SucursalDTO.class)))
        	.thenReturn(new SucursalDTO());
	    
	    
	    when(restTemplate.postForObject(anyString(), any(), eq(Void.class)))
        .thenReturn(null);
	    
		Pedido creado = servicio.crearPedido(pedidoDto);
		
		assertEquals(1, creado.getPedidoId());
		assertNotNull(creado);
		
		verify(repositorio).save(any(Pedido.class));
	    verify(restTemplate).postForObject(eq("http://localhost:8088/api/detalle_pedido/pedido"), any(), eq(Void.class));
		
	}
	
	@Test
	void testBuscarPedido() {
		Pedido creado = crearPedido(pedidoDto);
		when(repositorio.findById(1)).thenReturn(Optional.of(creado));
		
		Pedido pedEncon = servicio.pedidoById(1);
		
		assertEquals(1, pedEncon.getPedidoId());
		assertEquals(1, pedEncon.getUsuarioId());
		
		verify(repositorio).findById(1);
	}
	
	@Test
	void testBuscarPedidoNoEncontrado() {
		when(repositorio.findById(5)).thenReturn(Optional.empty());
		Pedido encontrado = servicio.pedidoById(5);
		assertNull(encontrado);
		verify(repositorio).findById(5);
	}
	
	@Test
	void testObtenerPedidos() {
		Pedido p1 = crearPedido(pedidoDto);
		Pedido p2 = new Pedido();
		p2.setSucursalId(1);
		List<Pedido> pedidos = List.of(p1,p2);
		when(repositorio.findAll()).thenReturn(pedidos);
		List<Pedido> resultado = repositorio.findAll();
		for(Pedido pedido : resultado) {
			assertEquals(1, pedido.getSucursalId());
		}
		verify(repositorio).findAll();
	}
	
	@Test
	void testEditarPedido() {
		Pedido original = crearPedido(pedidoDto);
		
		when(repositorio.findById(1)).thenReturn(Optional.of(original));
		Pedido encontrado = servicio.pedidoById(original.getPedidoId());
		
		encontrado.setFechaPedido(LocalDateTime.of(2030, 6, 3, 5, 50));
		
		when(repositorio.findById(encontrado.getPedidoId())).thenReturn(Optional.of(original));
		when(repositorio.save(encontrado)).thenReturn(encontrado);
		
		Pedido resultado = servicio.editarPedido(encontrado.getPedidoId(), encontrado);
		assertNotNull(resultado,"El resultado no es nulo");
		assertEquals(resultado.getFechaPedido(), LocalDateTime.of(2030, 6, 3, 5, 50));
		verify(repositorio,times(2)).findById(original.getPedidoId());
	}
	
	@Test
	void testEliminar() {
		Pedido original = crearPedido(pedidoDto);
		doNothing().when(repositorio).deleteById(original.getPedidoId());
		
		servicio.eliminarPedido(original.getPedidoId());
		
		Pedido eliminado = servicio.pedidoById(original.getPedidoId());
		
		assertNull(eliminado, "El objeto debe ser nulo");
		verify(repositorio).deleteById(1);
		
	}
}
