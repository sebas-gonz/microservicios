package com.Sucursal_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "DTO que representa una sucursal")
public class SucursalDTO {
	
	@Schema(description = "Número telefónico de contacto de la sucursal.", example = "+56 9 1234 5678")
    private String numeroTelefono;
	
	@Schema(description = "Dirección física donde se ubica la sucursal.", example = "Av. Siempre Viva 742, Santiago, Chile")
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







    

}
