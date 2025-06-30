package com.pedido_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProductoDTO", description = "DTO que representa la entidad producto")
public class ProductoDTO {
	    @Schema(description = "Representa el id del producto DTO", example="1")
	    private int idproducto;
	    @Schema(description = "Representa el nombre del producto DTO", example="Gio pefum")
	    private String nombreProducto;
	    @Schema(description = "Representa la categoria del producto DTO", example="Perfume")
	    private String categoria;
	    @Schema(description = "Representa el precio del producto DTO", example="25000")
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

		public String getNombreProducto() {
			return nombreProducto;
		}

		public void setNombreProducto(String nombreproducto) {
			this.nombreProducto = nombreproducto;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		

		public int getPrecio() {
			return precio;
		}

		public void setPrecio(int precio) {
			this.precio = precio;
		}


}	
