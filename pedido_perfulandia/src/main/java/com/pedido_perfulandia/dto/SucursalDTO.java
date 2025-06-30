package com.pedido_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SucursalDTO", description = "DTO que representa la información básica de una sucursal")
public class SucursalDTO {
	@Schema(description = "Identificador unico de la sucursal", example = "120")
	private int sucursalId;

    @Schema(description = "Número de teléfono de la sucursal", example = "123456789")
    private String numeroTelefono;

    @Schema(description = "Dirección física de la sucursal", example = "Av. Principal 1234, Ciudad")
    private String direccion;

    public SucursalDTO() {
        super();
    }
    
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}





    

}
