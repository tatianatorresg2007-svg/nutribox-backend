package com.nutribox.NutriBox.controller;

import com.nutribox.NutriBox.entity.PreferenciasCliente;
import com.nutribox.NutriBox.service.PreferenciasClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preferencias")
public class PreferenciasClienteController {

    @Autowired
    private PreferenciasClienteService preferenciasClienteService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<PreferenciasCliente> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(preferenciasClienteService.obtenerPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<PreferenciasCliente> guardar(@RequestBody PreferenciasCliente preferencias) {
        return ResponseEntity.ok(preferenciasClienteService.guardar(preferencias));
    }
}