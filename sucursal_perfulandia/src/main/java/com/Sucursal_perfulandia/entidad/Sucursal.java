package com.Sucursal_perfulandia.entidad;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(description = "Entidad que representa una sucursal del sistema Perfulandia.")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la sucursal.", example = "1")
    private int sucursalId;

    @Schema(description = "Número telefónico de contacto de la sucursal.", example = "+56 9 1234 5678")
    private String numeroTelefono;

    @Schema(description = "Dirección física donde se ubica la sucursal.", example = "Av. Siempre Viva 742, Santiago, Chile")
    private String direccion;
    public Sucursal() {
        super();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(int idSucursal) {
        this.sucursalId = idSucursal;
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
