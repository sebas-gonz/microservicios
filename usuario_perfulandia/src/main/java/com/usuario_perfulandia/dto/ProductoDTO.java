package com.usuario_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa un producto disponible en el sistema")
public class ProductoDTO {

    @Schema(description = "Nombre del producto", example = "Ergonomic Copper Shirt")
    private String nombreProducto;

    @Schema(description = "Categoría del producto", example = "Small Steel Bag")
    private String categoria;

    @Schema(description = "Precio del producto", example = "12990")
    private int precio;

	    
		public ProductoDTO() {
			super();
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
