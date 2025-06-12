package com.envio_perfulandia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.envio_perfulandia.entidad.Envio;

@Repository
public interface EnvioRepositorio extends JpaRepository<Envio, Integer>{

}
