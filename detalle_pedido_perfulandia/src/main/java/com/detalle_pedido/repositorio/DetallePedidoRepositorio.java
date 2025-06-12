package com.detalle_pedido.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.detalle_pedido.entidad.DetallePedido;
@Repository
public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Integer>{
	List<DetallePedido> findByPedidoId(int pedidoId);
}
