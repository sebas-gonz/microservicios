package com.envio_perfulandia.entidad;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Schema(description = "Entidad que representa los atributos.")
public class Envio {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Representa el id que tiene un pedido", example ="1")
    private int envioId;
	@Schema(description = "Representa el id de una boleta", example ="1")
    private int boletaId;
	@Schema(description = "Representa el id de pedido", example ="1")
    private int pedidoId;
	@Schema(description = "Representa el id del usuario que solicito una compra ", example ="1")
    private int usuarioId;
	@Schema(description = "Representa el id de la sucursal que realiza el envio", example ="1")
    private int sucursalId;
	@Schema(description = "Representa la direccion del envio", example ="Av.J.J. Perez")
    private String direccionEnvio;
	@Schema(description = "Representa el estado que se encuentra el pedido", example ="proceso")
    private String estado; 
	@Schema(description = "Representa la fecha que se realizo el envio", example ="24-06-2025")
    private LocalDateTime fechaEnvio;
	@Schema(description = "Representa la fecha de entrega del envio", example ="26-06-3035")
    private LocalDateTime fechaEntrega;
    
	public Envio() {
		super();
	}

	public int getEnvioId() {
		return envioId;
	}

	public void setEnvioId(int envioId) {
		this.envioId = envioId;
	}

	public int getBoletaId() {
		return boletaId;
	}

	public void setBoletaId(int boletaId) {
		this.boletaId = boletaId;
	}

	public int getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(LocalDateTime fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
    
    
}
