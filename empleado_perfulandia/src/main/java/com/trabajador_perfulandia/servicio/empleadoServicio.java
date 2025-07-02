package com.trabajador_perfulandia.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import com.trabajador_perfulandia.config.PerfulandiaConfig;
import com.trabajador_perfulandia.dto.BoletaDTO;
import com.trabajador_perfulandia.dto.DetalleBoletaDTO;
import com.trabajador_perfulandia.entidad.Empleado;
import com.trabajador_perfulandia.repositorio.EmpleadoRepositorio;

@Service
public class EmpleadoServicio {

	@Autowired
    private PerfulandiaConfig perfulandiaConfig;
	
	@Autowired
	private EmpleadoRepositorio repositorio;

	
	public Empleado crearEmpleado(Empleado empleado) {
		return repositorio.save(empleado);
	}
	
	public void eliminarEmpleado(int empleadoId) {
		repositorio.deleteById(empleadoId);
	}
	
	public List<Empleado> Empleados(){
		return repositorio.findAll();
	}
	
	public Empleado EmpleadoPorId(int empleadoId) {
		return repositorio.findById(empleadoId).orElse(null);
	}
	public Empleado empleadoPorRut(String rut){
		return (Empleado) repositorio.findByrut(rut);
	}
	public Empleado editarEmpleado(Empleado empleadoActualizado, int empleadoId) {
		Empleado empleado = repositorio.findById(empleadoId).orElse(null);
		if(empleado != null) {
			empleado.setNombre(empleadoActualizado.getNombre());
			empleado.setCargo(empleadoActualizado.getCargo());
			empleado.setCorreo(empleadoActualizado.getCorreo());
			empleado.setRut(empleadoActualizado.getRut());
			empleado.setSucursalId(empleadoActualizado.getSucursalId());
			return repositorio.save(empleado);
		}
		return empleado;
	}
	
	public List<BoletaDTO> boletasEmpleado(int empleadoId){
		try {
			String urlBoleta = "http://localhost:8088/api/boleta/empleado/" + empleadoId;
			BoletaDTO[] boletas= perfulandiaConfig.restTemplate().getForObject(urlBoleta, BoletaDTO[].class);
			for(BoletaDTO boleta : boletas) {
				
				String urlDetalle = "http://localhost:8088/api/detalle_boleta/boleta/" + boleta.getBoletaId();
				DetalleBoletaDTO[] detalles = perfulandiaConfig.restTemplate().getForObject(urlDetalle,  DetalleBoletaDTO[].class);
				boleta.setDetalleBoletas(Arrays.asList(detalles));
			}
			return boletas == null ? null : Arrays.asList(boletas);
		} catch (HttpClientErrorException e) {
			System.out.println("Error al encontrar las boletas del empleado:" + empleadoId);
			return null;
		} catch (Exception e) {
			System.out.println("Error al consumir el servicio: " + e.getMessage());
			return null;
		}
		

	}
	
	public List<Empleado> empleadosSucursal(int sucursalId){
		List<Empleado> empleados = repositorio.findBySucursalId(sucursalId);
		if(empleados.isEmpty()) {
			return null;
		}
		return empleados;
	}
	
	
}
