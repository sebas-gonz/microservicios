package com.usuario_perfulandia.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa una boleta con sus detalles")
public class BoletaDTO {

    @Schema(description = "ID único de la boleta", example = "101")
    private int boletaId;

    @Schema(description = "ID del usuario asociado", example = "3")
    private int usuarioId;
    
    @Schema(description = "ID de la sucursal", example = "2")
    private int sucursalId;
    
    @Schema(description = "Informacion de la sucursal.")
    private SucursalDTO sucursal;

    @Schema(description = "ID del empleado que atendió", example = "5")
    private int empleadoId;
    
    @Schema(description = "Informacion del empleado")
    private EmpleadoDTO empleado;

    @Schema(description = "Monto total de la boleta", example = "15230")
    private int total;

    @Schema(description = "Lista de detalles de productos incluidos en la boleta")
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

	public List<DetalleBoletaDTO> getDetalleBoletas() {
		return detalleBoletas;
	}
	public void setDetalleBoletas(List<DetalleBoletaDTO> detalleBoletas) {
		this.detalleBoletas = detalleBoletas;
	}
	public int getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}
	public SucursalDTO getSucursal() {
		return sucursal;
	}
	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}
	public EmpleadoDTO getEmpleado() {
		return empleado;
	}
	public void setEmpleado(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}
	
	
}
