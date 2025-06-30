package com.detalle_pedido.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProductoDTO", description = "DTO que representa un producto con información básica y cantidad disponible")
public class ProductoDTO {

    @Schema(description = "ID único del producto", example = "101")
    private int idproducto;

    @Schema(description = "Nombre del producto", example = "Audífonos inalámbricos")
    private String nombreProducto;

    @Schema(description = "Categoría del producto", example = "Electrónica")
    private String categoria;

    @Schema(description = "Cantidad disponible del producto", example = "45")
    private int cantidad;

    @Schema(description = "Precio del producto", example = "19990")
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
