package com.nutribox.NutriBox.controller;

import com.nutribox.NutriBox.entity.Plan;
import com.nutribox.NutriBox.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/planes")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping
    public List<Plan> listarTodos() {
        return planService.listarTodos();
    }

    @GetMapping("/activos")
    public List<Plan> listarActivos() {
        return planService.listarActivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(planService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Plan> crear(@RequestBody Plan plan) {
        return ResponseEntity.ok(planService.crear(plan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan> actualizar(@PathVariable Long id, @RequestBody Plan plan) {
        return ResponseEntity.ok(planService.actualizar(id, plan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        planService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}