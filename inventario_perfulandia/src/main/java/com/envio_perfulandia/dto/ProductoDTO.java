package com.envio_perfulandia.dto;

public class ProductoDTO {
	    private int idproducto;
	    private String nombreproducto;
	    private String categoria;
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
