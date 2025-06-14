package com.pedido_perfulandia.servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.pedido_perfulandia.dto.BoletaDTO;
import com.pedido_perfulandia.dto.DetalleBoletaDTO;
import com.pedido_perfulandia.dto.DetallePedidoDTO;
import com.pedido_perfulandia.dto.EmpleadoDTO;
import com.pedido_perfulandia.dto.EnvioDTO;
import com.pedido_perfulandia.dto.InventarioDTO;
import com.pedido_perfulandia.dto.PedidoDTO;
import com.pedido_perfulandia.dto.ProductoDTO;
import com.pedido_perfulandia.dto.SucursalDTO;
import com.pedido_perfulandia.dto.UsuarioDTO;
import com.pedido_perfulandia.entidad.Pedido;
import com.pedido_perfulandia.repositorio.PedidoRespositorio;

@Service
public class PedidoServicio {
	
	@Autowired
	private PedidoRespositorio respositorio;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Pedido crearPedido(PedidoDTO pedidoDTO) {
	    try {
	        Pedido pedido = new Pedido();
	        pedido.setUsuarioId(pedidoDTO.getUsuarioId());
	        pedido.setSucursalId(pedidoDTO.getSucursalId());
	        respositorio.save(pedido);
	        int pedidoId = pedido.getPedidoId();
	        List<DetallePedidoDTO> detallesPedidos = pedidoDTO.getDetalles();
	        for (DetallePedidoDTO detalle : detallesPedidos) {
	            detalle.setPedidoId(pedidoId);
	            
	            String urlInventario = "http://localhost:8090/inventario/" + pedido.getSucursalId() + "/" + detalle.getProductoId();
	            InventarioDTO[] inventarios = restTemplate.getForObject(urlInventario, InventarioDTO[].class);
	            if(inventarios == null) {
	            	return null;
	            }
	            for(InventarioDTO inventario : inventarios) {
	            	if(inventario.getCantidadDisponible() < detalle.getCantidad()) {
	            		return null;
	            	}
	            	inventario.setCantidadDisponible(inventario.getCantidadDisponible() - detalle.getCantidad());
	            }
	            restTemplate.postForObject("http://localhost:8090/inventario/pedido", Arrays.asList(inventarios), Void.class);
	        }	    
	        respositorio.save(pedido);
	        String urlDetalle = "http://localhost:8088/api/detalle_pedido/pedido";
	        restTemplate.postForObject(urlDetalle, detallesPedidos, Void.class);
             
	        String urlUsuario = "http://localhost:8088/api/usuario/" + pedido.getUsuarioId();
	        UsuarioDTO usuario = restTemplate.getForObject(urlUsuario, UsuarioDTO.class);

	        String urlSucursal = "http://localhost:8088/api/sucursal/" + pedido.getSucursalId();
	        SucursalDTO sucursal = restTemplate.getForObject(urlSucursal, SucursalDTO.class);

	        crearBoleta(pedido, usuario, sucursal, detallesPedidos);

	        return pedido;

	    } catch (HttpClientErrorException e) {
	        System.out.println("Error al crear pedido: " + e.getMessage());
	        return null;
	    } catch (Exception e) {
	        System.out.println("Error general: " + e.getMessage());
	        return null;
	    }
	}
	
	private void crearBoleta(Pedido pedido, UsuarioDTO usuario, SucursalDTO sucursal, List<DetallePedidoDTO> detallesPedidos) {
	    try {
	        String urlEmpleado = "http://localhost:8088/api/sucursal/" + pedido.getSucursalId() + "/empleados";
	        EmpleadoDTO[] empleados = restTemplate.getForObject(urlEmpleado, EmpleadoDTO[].class);

	        EmpleadoDTO empleadoSelec = null;
	        if (empleados != null && empleados.length > 0) {
	            Random random = new Random();
	            empleadoSelec = empleados[random.nextInt(empleados.length)];
	        }

	        BoletaDTO boleta = new BoletaDTO();
	        boleta.setPedidoId(pedido.getPedidoId());
	        boleta.setUsuarioId(pedido.getUsuarioId());
	        boleta.setSucursalId(pedido.getSucursalId());
	        boleta.setEmpleadoId(empleadoSelec != null ? empleadoSelec.getEmpleadoId() : 0);
	        boleta.setNombreEmpleado(empleadoSelec != null ? empleadoSelec.getNombre() : null);
	        boleta.setNombreUsuario(usuario.getNombre() + " " + usuario.getApellido());
	        boleta.setNombreSucursal(sucursal.getDireccion());

	        int total = 0;
	        for (DetallePedidoDTO detalle : detallesPedidos) {
	            String urlProducto = "http://localhost:8088/api/producto/" + detalle.getProductoId();
	            ProductoDTO producto = restTemplate.getForObject(urlProducto, ProductoDTO.class);
	            total += producto.getPrecio() * detalle.getCantidad();
	        }
	        boleta.setTotal(total);

	        String urlCrearBoleta = "http://localhost:8088/api/boleta/pedido";
	        restTemplate.postForObject(urlCrearBoleta, boleta, Void.class);

	        String urlBoleta = "http://localhost:8088/api/boleta/pedido/" + pedido.getPedidoId();
	        BoletaDTO boletaGenerada = restTemplate.getForObject(urlBoleta, BoletaDTO.class);

	        crearDetallesBoleta(boletaGenerada, detallesPedidos);
	        
	        crearEnvio(boletaGenerada,pedido,usuario);
	        
	    } catch (Exception e) {
	        System.out.println("Error al crear boleta: " + e.getMessage());
	    }
	}

	private void crearEnvio(BoletaDTO boleta,Pedido pedido,UsuarioDTO usuario ) {
		try {
	        EnvioDTO envio = new EnvioDTO();
	        envio.setBoletaId(boleta.getBoletaId());
	        envio.setPedidoId(pedido.getPedidoId());
	        envio.setSucursalId(pedido.getSucursalId());
	        envio.setUsuarioId(pedido.getUsuarioId());
	        envio.setDireccionEnvio(usuario.getDireccion());
	        envio.setEstado("Pendiente");
	        envio.setFechaEnvio(pedido.getFechaPedido());
	        envio.setFechaEntrega(pedido.getFechaPedido().plusDays(3));

	        String urlCrearEnvio = "http://localhost:8089/envio/pedido";
	        restTemplate.postForObject(urlCrearEnvio, envio, Void.class);
	        
		} catch(Exception e) {
			System.out.println("Error al crear el envio: " + e.getMessage());
		}
	}
	
	private void crearDetallesBoleta(BoletaDTO boleta, List<DetallePedidoDTO> detallesPedidos) {
	    try {
	        List<DetalleBoletaDTO> detallesBoleta = new ArrayList<>();

	        for (DetallePedidoDTO detallePedido : detallesPedidos) {
	            String urlProducto = "http://localhost:8088/api/producto/" + detallePedido.getProductoId();
	            ProductoDTO producto = restTemplate.getForObject(urlProducto, ProductoDTO.class);

	            DetalleBoletaDTO detalleBoleta = new DetalleBoletaDTO();
	            detalleBoleta.setBoletaId(boleta.getBoletaId());
	            detalleBoleta.setProductoId(detallePedido.getProductoId());
	            detalleBoleta.setNombreProducto(producto.getNombreproducto());
	            detalleBoleta.setCantidad(detallePedido.getCantidad());
	            detalleBoleta.setSubtotal(detallePedido.getCantidad() * producto.getPrecio());

	            detallesBoleta.add(detalleBoleta);
	        }

	        String urlCrearDetallesBoleta = "http://localhost:8088/api/detalle_boleta/pedido";
	        restTemplate.postForObject(urlCrearDetallesBoleta, detallesBoleta, Void.class);

	    } catch (Exception e) {
	        System.out.println("Error al crear detalles boleta: " + e.getMessage());
	    }
	}
	
	
	public Pedido pedidoById(int pedidoId) {
		Pedido pedido = respositorio.findById(pedidoId).orElse(null);
		return pedido;
	}
	
	public List<Pedido> pedidos(){
		List<Pedido> pedidos = respositorio.findAll();
		if(pedidos.isEmpty()) {
			return null;
		}
		return pedidos;
	}
	
	public void eliminarPedido(int pedidoId) {
		respositorio.deleteById(pedidoId);
	}
	
	public Pedido editarPedido(int pedidoId,Pedido pedidoAct) {
		Pedido pedido = respositorio.findById(pedidoId).orElse(null);
		pedido.setEstado(pedidoAct.getEstado());
		pedido.setFechaPedido(LocalDateTime.now());
		pedido.setSucursalId(pedidoAct.getSucursalId());
		pedido.setUsuarioId(pedidoAct.getUsuarioId());
		
		return pedido;
	}
	
	public List<Pedido> pedidoPorSucursalId(int sucursalId){
		List<Pedido> pedidos = respositorio.findBySucursalId(sucursalId);
		return pedidos.isEmpty() ? null : pedidos;
	}
	
	public List<Pedido> pedidoPorUsuarioId(int usuarioId){
		List<Pedido> pedidos = respositorio.findByUsuarioId(usuarioId);
		return pedidos.isEmpty() ? null : pedidos;
	}
	
}
