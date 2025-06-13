package com.detalle_boleta_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa un usuarioDTO del sistema.")
public class UsuarioDTO {
	@Schema(description = "id del usuarioDTO.", example = "1")
	private int usuario_id;
	@Schema(description = "nombre del usuarioDTO.", example = "juan")
	private String nombre;
	@Schema(description = "apellido del usuarioDTO.", example = "perez")
	private String apellido;
	@Schema(description = "direccion del usuarioDTO.", example = "jose joaquin perez")
	private String direccion;
	@Schema(description = "correo del usuarioDTO.", example = "juan@perez.com")
	private String correo;
	@Schema(description = "contraseña del usuarioDTO.", example = "1234..")
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
