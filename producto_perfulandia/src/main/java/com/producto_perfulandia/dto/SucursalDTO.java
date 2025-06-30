package com.producto_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información básica de una sucursal")
public class SucursalDTO {

    @Schema(description = "ID único de la sucursal", example = "3001")
    private int sucursalId;

    @Schema(description = "Número de teléfono de contacto de la sucursal", example = "+56 9 1234 5678")
    private String numeroTelefono;

    @Schema(description = "Dirección física de la sucursal", example = "Av. Libertador Bernardo O'Higgins 123, Santiago")
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
