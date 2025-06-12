package com.trabajador_perfulandia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabajador_perfulandia.entidad.Empleado;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer>{
	
	List<Empleado> findBySucursalId(int sucursalId);
}
