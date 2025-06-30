package com.producto_perfulandia.entidad;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
@Schema(description = "Entidad que representa un producto disponible en el sistema")
public class Producto {

    @Id
    @Schema(description = "ID único del producto", example = "101")
    private int idproducto;

    @Schema(description = "Nombre del producto", example = "Perfume Aromax 50ml")
    private String nombreProducto;

    @Schema(description = "Categoría a la que pertenece el producto", example = "Perfumería")
    private String categoria;

    @Schema(description = "Precio unitario del producto en pesos chilenos", example = "14990")
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
