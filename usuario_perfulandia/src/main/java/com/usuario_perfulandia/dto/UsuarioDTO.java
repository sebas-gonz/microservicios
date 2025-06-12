package com.usuario_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "DTO  que representa un usuario")
public class UsuarioDTO {
	
    @Schema(description = "Nombre del usuario.", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del usuario.", example = "Pérez")
    private String apellido;

    @Schema(description = "Dirección física del usuario.", example = "Av. Siempre Viva 742")
    private String direccion;

    @Schema(description = "Correo electrónico del usuario.", example = "juan.perez@example.com")
    private String correo;

    @Schema(description = "Contraseña del usuario (cifrada o protegida).", example = "********", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String contraseña;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public UsuarioDTO() {
		super();
	}
    
    
}
