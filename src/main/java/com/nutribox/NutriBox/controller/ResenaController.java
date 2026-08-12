package com.nutribox.NutriBox.controller;

import com.nutribox.NutriBox.entity.Resena;
import com.nutribox.NutriBox.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping("/menu/{menuId}")
    public List<Resena> listarPorMenu(@PathVariable Long menuId) {
        return resenaService.listarPorMenu(menuId);
    }

    @PostMapping
    public ResponseEntity<Resena> crear(@RequestBody Resena resena) {
        return ResponseEntity.ok(resenaService.crear(resena));
    }
}