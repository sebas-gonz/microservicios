package com.detalle_boleta_perfulandia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.detalle_boleta_perfulandia.entidades.DetalleBoleta;
import java.util.List;


@Repository
public interface DetalleBoletaRepositorio extends JpaRepository<DetalleBoleta, Integer>{
	List<DetalleBoleta> findByBoletaId(int boleta_id);
	List<DetalleBoleta> findByProductoId(int productoId);
}
