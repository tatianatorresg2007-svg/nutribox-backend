package com.nutribox.NutriBox.controller;

import com.nutribox.NutriBox.entity.Menu;
import com.nutribox.NutriBox.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Menu> listarTodos() {
        return menuService.listarTodos();
    }

    @GetMapping("/disponibles")
    public List<Menu> listarDisponibles() {
        return menuService.listarDisponibles();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Menu> listarPorCategoria(@PathVariable Menu.CategoriaMenu categoria) {
        return menuService.listarPorCategoria(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Menu> crear(@RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.crear(menu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> actualizar(@PathVariable Long id, @RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.actualizar(id, menu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        menuService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}