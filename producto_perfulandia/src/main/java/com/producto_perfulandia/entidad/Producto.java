package com.producto_perfulandia.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Producto {
    private int idproducto;
    private String nombreProducto;
    private String categoria;
    private int precio;
    
	public Producto() {
		super();
	}
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
