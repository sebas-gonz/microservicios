package com.envio_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "DTO que representa una sucursal disponible en el sistema", example = "144")
public class SucursalDTO {
	@Schema(description = "sucursal registrada en el inventario", example = "171")
	private int sucursalId;
	@Schema(description = "numero de telefono registrado en cada sucursal", example = "111")
    private String numeroTelefono;
	@Schema(description = "direccion de cada sucursal en cada inventario", example = "151")
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
