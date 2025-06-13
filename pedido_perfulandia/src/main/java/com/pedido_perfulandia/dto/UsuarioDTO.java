package com.pedido_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UsuarioDTO", description = "DTO que representa la información de un usuario")
public class UsuarioDTO {

    @Schema(description = "ID único del usuario", example = "1001")
    private int usuario_id;

    @Schema(description = "Nombre del usuario", example = "Juan")
    private String nombre;

    @Schema(description = "Apellido del usuario", example = "Pérez")
    private String apellido;

    @Schema(description = "Dirección del usuario", example = "Calle Falsa 123, Ciudad")
    private String direccion;

    @Schema(description = "Correo electrónico del usuario", example = "juan.perez@email.com")
    private String correo;

    @Schema(description = "Contraseña del usuario", example = "********")
    private String contraseña;

	public UsuarioDTO() {
		super();
	}
	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

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
	
	
}
