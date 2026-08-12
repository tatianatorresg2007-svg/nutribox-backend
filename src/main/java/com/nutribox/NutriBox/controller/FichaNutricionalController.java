package com.nutribox.NutriBox.controller;

import com.nutribox.NutriBox.entity.FichaNutricional;
import com.nutribox.NutriBox.service.FichaNutricionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fichas-nutricionales")
public class FichaNutricionalController {

    @Autowired
    private FichaNutricionalService fichaNutricionalService;

    @PostMapping
    public ResponseEntity<FichaNutricional> crear(@RequestBody FichaNutricional ficha) {
        return ResponseEntity.ok(fichaNutricionalService.crear(ficha));
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<FichaNutricional> obtenerPorMenuId(@PathVariable Long menuId) {
        return ResponseEntity.ok(fichaNutricionalService.obtenerPorMenuId(menuId));
    }
}