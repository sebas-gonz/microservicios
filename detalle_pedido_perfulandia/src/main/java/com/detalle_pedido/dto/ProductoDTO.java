package com.detalle_pedido.dto;


public class ProductoDTO {
    private int idproducto;
    private String nombreProducto;
    private String categoria;
    private int cantidad;
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
