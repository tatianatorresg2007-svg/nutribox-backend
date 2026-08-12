package com.nutribox.NutriBox.service;

import com.nutribox.NutriBox.entity.BeneficiosPlan;
import com.nutribox.NutriBox.repository.BeneficiosPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiosPlanService {

    @Autowired
    private BeneficiosPlanRepository beneficiosPlanRepository;

    public BeneficiosPlan obtenerPorPlan(Long planId) {
        return beneficiosPlanRepository.findByPlanId(planId)
                .orElseThrow(() -> new RuntimeException("Beneficios no encontrados para el plan: " + planId));
    }

    public BeneficiosPlan crear(BeneficiosPlan beneficios) {
        return beneficiosPlanRepository.save(beneficios);
    }
}