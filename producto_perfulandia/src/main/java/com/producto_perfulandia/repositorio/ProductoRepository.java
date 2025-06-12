package com.producto_perfulandia.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.producto_perfulandia.entidad.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
}
