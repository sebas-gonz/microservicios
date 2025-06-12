package com.pedido_perfulandia.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedido_perfulandia.entidad.Pedido;

@Repository
public interface PedidoRespositorio extends JpaRepository<Pedido, Integer>{
	List<Pedido> findBySucursalId(int sucursalId);
	List<Pedido> findByUsuarioId(int usuarioId);
}
