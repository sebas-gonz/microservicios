package com.boleta_perfulandia.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa una boletaDTO del sistema.")
public class BoletaDTO {
	
	@Schema(description = "id de la boletaDTO.", example = "1")
	private int boletaId;
	@Schema(description = "nombre de usuario de la boletaDTO.", example = "jose.perez")
	private String nombreUsuario;
	@Schema(description = "nombre de sucursal de la boletaDTO.", example = "perfulandia")
	private String nombreSucursal;
	@Schema(description = "nombre del empleado de la boletaDTO.", example = "jose")
	private String nombreEmpleado;
	@Schema(description = "id de usuario de la boletaDTO.", example = "2")
	private int usuarioId;
	@Schema(description = "id de sucursal de la boletaDTO.", example = "3")
	private int sucursalId;
	@Schema(description = "id del empleado de la boletaDTO.", example = "4")
	private int empleadoId;
	@Schema(description = "id del pedido de la boletaDTO.", example = "5")
	private int pedidoId;
	@Schema(description = "el total de la boletaDTO.", example = "$400")
	private int total;
	@Schema(description = "es la lista de detalle boleta.")
	private List<DetalleBoletaDTO> detalleBoletas;
	
	
	public BoletaDTO() {
		super();
	}
	public int getBoletaId() {
		return boletaId;
	}

	public void setBoletaId(int boleta_id) {
		this.boletaId = boleta_id;
	}


	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuario_id) {
		this.usuarioId = usuario_id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursal_id) {
		this.sucursalId = sucursal_id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	public List<DetalleBoletaDTO> getDetalleBoletas() {
		return detalleBoletas;
	}
	public void setDetalleBoletas(List<DetalleBoletaDTO> detalleBoletas) {
		this.detalleBoletas = detalleBoletas;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public int getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}
	public int getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}
	
	
}
