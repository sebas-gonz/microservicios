package com.boleta_perfulandia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boleta_perfulandia.entidades.Boleta;
@Repository
public interface BoletaRepositorio extends JpaRepository<Boleta, Integer>{
	List<Boleta> findByUsuarioId(int usuarioId);
	List<Boleta> findBySucursalId(int sucursalId);
	List<Boleta> findByEmpleadoId(int empleadoId);
	Boleta findByPedidoId(int pedidoId);
}
