package com.usuario_perfulandia.servicio;

import java.util.Arrays;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario_perfulandia.config.PerfulandiaConfig;
import com.usuario_perfulandia.dto.BoletaDTO;
import com.usuario_perfulandia.dto.DetalleBoletaDTO;
import com.usuario_perfulandia.dto.DetallePedidoDTO;
import com.usuario_perfulandia.dto.EmpleadoDTO;
import com.usuario_perfulandia.dto.PedidoDTO;
import com.usuario_perfulandia.dto.ProductoDTO;
import com.usuario_perfulandia.dto.SucursalDTO;
import com.usuario_perfulandia.entidad.Usuario;
import com.usuario_perfulandia.repositorio.UsuarioRepository;

@Service
public class UsuarioServicio {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfulandiaConfig perfulandiaConfig;
	
	public Usuario crearUsuario(Usuario usuario) {
		String hashed = BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt());
		usuario.setContraseña(hashed);
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> getUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	public void deleteUsuarioById(int id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario editarUsuario(Usuario actualizarUsuario,int id) {
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		if(usuario == null) {
			return null;
		}
		String hashed = BCrypt.hashpw(actualizarUsuario.getContraseña(), BCrypt.gensalt());
		usuario.setNombre(actualizarUsuario.getNombre());
		usuario.setApellido(actualizarUsuario.getApellido());
		usuario.setCorreo(actualizarUsuario.getCorreo());
		usuario.setDireccion(actualizarUsuario.getDireccion());
		usuario.setContraseña(hashed);
		return usuarioRepository.save(usuario);
	}
	
	public List<BoletaDTO> boletasUsuario(int id){
		String url = "http://localhost:8083/boleta/usuario/" + id;
		
		BoletaDTO[] boletas = perfulandiaConfig.restTemplate().getForObject(url, BoletaDTO[].class);
		
		for(BoletaDTO boleta : boletas) {
			
			DetalleBoletaDTO[] detalles = perfulandiaConfig.restTemplate().
					getForObject("http://localhost:8088/api/detalle_boleta/boleta/" + boleta.getBoletaId(), DetalleBoletaDTO[].class);
			for(DetalleBoletaDTO detalle : detalles) {
    			ProductoDTO producto = perfulandiaConfig.restTemplate().getForObject("http://localhost:8088/api/producto/" + detalle.getProductoId(), ProductoDTO.class);
    			detalle.setProducto(producto);
    		}
			
			SucursalDTO sucursal = perfulandiaConfig.restTemplate().getForObject("http://localhost:8088/api/sucursal/"+ boleta.getSucursalId(), SucursalDTO.class);
			boleta.setSucursal(sucursal);
			
			EmpleadoDTO empleado = perfulandiaConfig.restTemplate().getForObject("http://localhost:8088/api/empleado/" + boleta.getEmpleadoId(), EmpleadoDTO.class);
			boleta.setEmpleado(empleado);
			
			boleta.setDetalleBoletas(Arrays.asList(detalles));
		}
		
		
		return boletas == null ? null : Arrays.asList(boletas);
	}
	public List<PedidoDTO> pedidosPorUsuario(int usuarioId){
		PedidoDTO[] pedidos = perfulandiaConfig.restTemplate().getForObject("http://localhost:8087/pedido/usuario/" + usuarioId, PedidoDTO[].class);
    	for(PedidoDTO pedido : pedidos) {
    		DetallePedidoDTO[] detalles = perfulandiaConfig.restTemplate().
    				getForObject("http://localhost:8085/detalle_pedido/pedido/" + pedido.getPedidoId(), DetallePedidoDTO[].class);
    		
    		for(DetallePedidoDTO detalle : detalles) {
    			ProductoDTO producto = perfulandiaConfig.restTemplate().
    					getForObject("http://localhost:8088/api/producto/" + detalle.getProductoId(), ProductoDTO.class);
    			detalle.setProducto(producto);
    		}
    		SucursalDTO sucursal = perfulandiaConfig.restTemplate().getForObject("http://localhost:8088/api/sucursal/" + pedido.getSucursalId(), SucursalDTO.class);
    		pedido.setSucursal(sucursal);
    		
    		pedido.setDetalles(Arrays.asList(detalles));
    	}
    	return pedidos != null ? Arrays.asList(pedidos) : null;
	}
}
