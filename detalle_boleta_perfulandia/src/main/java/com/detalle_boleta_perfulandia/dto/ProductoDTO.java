package com.detalle_boleta_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa un productoDTO del sistema.")
public class ProductoDTO {
	@Schema(description = "id del productoDTO.", example = "1")
    private int idproducto;
	@Schema(description = "nombre del productoDTO.", example = "colonia")
    private String nombreproducto;
	@Schema(description = "categoria del productoDTO", example = "perfume")
    private String categoria;
	@Schema(description = "cantidad del productoDTO.", example = "1")
    private int cantidad;
	@Schema(description = "precio del productoDTO.", example = "$40")
    private int precio;
    
	public ProductoDTO() {
		super();
	}
	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public String getNombreproducto() {
		return nombreproducto;
	}

	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
   
    
    
    


    


}
