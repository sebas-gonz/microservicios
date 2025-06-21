package com.usuario_perfulandia.entidad;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(description = "Entidad que representa un usuario del sistema.")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuario_id;

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
	
	public Usuario() {
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
