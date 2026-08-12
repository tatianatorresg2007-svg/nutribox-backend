package com.nutribox.NutriBox.repository;

import com.nutribox.NutriBox.entity.Pedido;
import com.nutribox.NutriBox.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuario(Usuario usuario);
    List<Pedido> findByEstado(Pedido.EstadoPedido estado);
}