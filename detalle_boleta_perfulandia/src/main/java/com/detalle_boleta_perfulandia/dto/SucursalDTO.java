package com.detalle_boleta_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa sucursalDTO del sistema.")
public class SucursalDTO {
	@Schema(description = "numero de telefono la sucursal sucursalDTO.", example = "+5698887")
    private int numeroTelefono;

    private String direccion;
    public SucursalDTO() {
        super();
    }
    
    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }





    

}
