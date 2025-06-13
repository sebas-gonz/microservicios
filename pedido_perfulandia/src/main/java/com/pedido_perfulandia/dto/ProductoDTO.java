package com.pedido_perfulandia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProductoDTO", description = "DTO que representa la información de un producto en una sucursal con cantidad disponible")
public class ProductoDTO {

    @Schema(description = "ID único del producto", example = "101")
    private int idproducto;

    @Schema(description = "Nombre del producto", example = "Camiseta deportiva")
    private String nombreproducto;

    @Schema(description = "Categoría a la que pertenece el producto", example = "Ropa deportiva")
    private String categoria;

    @Schema(description = "ID de la sucursal donde está disponible el producto", example = "5")
    private int sucursalId;

    @Schema(description = "Cantidad disponible del producto en la sucursal", example = "50")
    private int cantidad;

    @Schema(description = "Precio unitario del producto", example = "15000")
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

		public int getSucursalId() {
			return sucursalId;
		}

		public void setSucursalId(int sucursalId) {
			this.sucursalId = sucursalId;
		}

}	
