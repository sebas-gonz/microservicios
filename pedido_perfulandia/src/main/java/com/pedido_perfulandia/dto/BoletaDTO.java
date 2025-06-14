package com.pedido_perfulandia.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BoletaDTO", description = "DTO que representa la boleta generada para un pedido")
public class BoletaDTO {

    @Schema(description = "ID único de la boleta", example = "5001")
    private int boletaId;

    @Schema(description = "ID del usuario que realizó la boleta", example = "10")
    private int usuarioId;

    @Schema(description = "Nombre del usuario que realizó la boleta", example = "Juan Pérez")
    private String nombreUsuario;

    @Schema(description = "ID de la sucursal asociada a la boleta", example = "3")
    private int sucursalId;

    @Schema(description = "Nombre de la sucursal asociada a la boleta", example = "Sucursal Centro")
    private String nombreSucursal;

    @Schema(description = "ID del empleado que gestionó la boleta", example = "7")
    private int empleadoId;

    @Schema(description = "Nombre del empleado que gestionó la boleta", example = "María Gómez")
    private String nombreEmpleado;

    @Schema(description = "ID del pedido relacionado a la boleta", example = "120")
    private int pedidoId;

    @Schema(description = "Monto total de la boleta", example = "35000")
    private int total;

    @Schema(description = "Lista de detalles asociados a la boleta")
    private List<DetalleBoletaDTO> detalles;
	public BoletaDTO() {
		super();
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DetalleBoletaDTO> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleBoletaDTO> detalles) {
		this.detalles = detalles;
	}

	public int getBoletaId() {
		return boletaId;
	}

	public void setBoletaId(int boletaId) {
		this.boletaId = boletaId;
	}
    
	
    
}
