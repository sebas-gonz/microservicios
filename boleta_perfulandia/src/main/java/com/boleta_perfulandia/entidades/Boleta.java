package com.boleta_perfulandia.entidades;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(description = "Entidad que representa una boleta del sistema.")
public class Boleta {
	
	@Schema(description = "Identificador único de una boleta.", example = "1")
	private int boletaId;
	@Schema(description = "Nombre del usuario que le pertenece a la boleta.", example = "juan")
	private String nombreUsuario;
	@Schema(description = "Nombre de la sucursal que le pertenece a la boleta.", example = "patito")
	private String nombreSucursal;
	@Schema(description = "Nombre del empleado que le pertenece a la boleta.", example = "franco")
	private String nombreEmpleado;
	@Schema(description = "id del usuario.", example = "2")
	private int usuarioId;
	@Schema(description = "id de la sucursal.", example = "3")
	private int sucursalId;
	@Schema(description = "id del empleado.", example = "4")
	private int empleadoId;
	@Schema(description = "id del pedido.", example = "1")
	private int pedidoId;
	@Schema(description = "aqui se muestra el total de la boleta.", example = "$500")
	private int total;
	
	
	
	public Boleta() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public int getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
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
	
	
}
