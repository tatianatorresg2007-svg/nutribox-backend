package com.nutribox.NutriBox.service;

import com.nutribox.NutriBox.entity.*;
import com.nutribox.NutriBox.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MenuRepository menuRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return pedidoRepository.findByUsuario(usuario);
    }

    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    public Pedido crear(Long usuarioId, List<ItemPedidoRequest> items) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);

        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedidoRequest item : items) {
            Menu menu = menuRepository.findById(item.getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menú no encontrado: " + item.getMenuId()));

            if (!menu.getDisponible()) {
                throw new RuntimeException("El menú '" + menu.getNombre() + "' no está disponible");
            }

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setMenu(menu);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(menu.getPrecio()); // precio real de la base, no el que mande el cliente
            BigDecimal subtotal = menu.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad()));
            detalle.setSubtotal(subtotal);

            pedido.getDetalles().add(detalle);
            total = total.add(subtotal);
        }

        pedido.setTotal(total);
        return pedidoRepository.save(pedido); // gracias al cascade, guarda pedido + detalles juntos
    }

    public Pedido actualizarEstado(Long id, Pedido.EstadoPedido nuevoEstado) {
        Pedido pedido = obtenerPorId(id);
        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }
}