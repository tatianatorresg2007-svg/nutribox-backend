package com.nutribox.NutriBox.controller;

import com.nutribox.NutriBox.entity.BeneficiosPlan;
import com.nutribox.NutriBox.service.BeneficiosPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beneficios-plan")
public class BeneficiosPlanController {

    @Autowired
    private BeneficiosPlanService beneficiosPlanService;

    @GetMapping("/plan/{planId}")
    public ResponseEntity<BeneficiosPlan> obtenerPorPlan(@PathVariable Long planId) {
        return ResponseEntity.ok(beneficiosPlanService.obtenerPorPlan(planId));
    }

    @PostMapping
    public ResponseEntity<BeneficiosPlan> crear(@RequestBody BeneficiosPlan beneficios) {
        return ResponseEntity.ok(beneficiosPlanService.crear(beneficios));
    }
}