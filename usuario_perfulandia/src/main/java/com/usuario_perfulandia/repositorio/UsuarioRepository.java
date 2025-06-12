package com.usuario_perfulandia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuario_perfulandia.entidad.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
