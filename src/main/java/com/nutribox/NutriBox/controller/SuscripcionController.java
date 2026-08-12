package com.nutribox.NutriBox.controller;

import com.nutribox.NutriBox.entity.Suscripcion;
import com.nutribox.NutriBox.entity.SuscripcionRequest;
import com.nutribox.NutriBox.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/suscripciones")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

    @GetMapping("/usuario/{usuarioId}")
    public List<Suscripcion> listarPorUsuario(@PathVariable Long usuarioId) {
        return suscripcionService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suscripcion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(suscripcionService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Suscripcion> suscribir(@RequestBody SuscripcionRequest request) {
        return ResponseEntity.ok(suscripcionService.suscribir(request.getUsuarioId(), request.getPlanId()));
    }

    @PutMapping("/{id}/pausar")
    public ResponseEntity<Suscripcion> pausar(@PathVariable Long id) {
        return ResponseEntity.ok(suscripcionService.pausar(id));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Suscripcion> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(suscripcionService.cancelar(id));
    }
}