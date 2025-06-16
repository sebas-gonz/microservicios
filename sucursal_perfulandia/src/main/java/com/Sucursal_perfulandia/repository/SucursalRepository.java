package com.Sucursal_perfulandia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sucursal_perfulandia.entidad.Sucursal;
@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
    

}
