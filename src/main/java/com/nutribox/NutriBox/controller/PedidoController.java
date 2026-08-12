package com.nutribox.NutriBox.controller;

import com.nutribox.NutriBox.entity.Pedido;
import com.nutribox.NutriBox.entity.PedidoRequest;
import com.nutribox.NutriBox.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> listarPorUsuario(@PathVariable Long usuarioId) {
        return pedidoService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody PedidoRequest request) {
        return ResponseEntity.ok(pedidoService.crear(request.getUsuarioId(), request.getItems()));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Long id, @RequestParam Pedido.EstadoPedido estado) {
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, estado));
    }
}