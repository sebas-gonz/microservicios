package com.envio_perfulandia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envio_perfulandia.entidad.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer>{
	List<Inventario> findByProductoId(int productoId);;
	List<Inventario> findBySucursalId(int sucursalId);
	List<Inventario> findBySucursalIdAndInventarioId(int sucursalId, int inventarioId);
	
}
