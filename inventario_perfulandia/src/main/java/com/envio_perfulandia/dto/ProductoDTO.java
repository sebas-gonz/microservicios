package com.envio_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa un producto disponible en el sistema", example = "121")
public class ProductoDTO {
	@Schema(description = "representa el id del producto seleccionado",example ="199" )
	    private int idproducto;
	@Schema(description = "nombre del prodecto seleccionado en el inventario", example = "153")
	    private String nombreproducto;
	@Schema(description = "categorida de producto seleccionado", example = "119")
	    private String categoria;
	@Schema(description = "precio de cada producto en el iventario", example = "137")
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


		public int getPrecio() {
			return precio;
		}

		public void setPrecio(int precio) {
			this.precio = precio;
		}

}	
