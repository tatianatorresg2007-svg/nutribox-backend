package com.nutribox.NutriBox.service;

import com.nutribox.NutriBox.entity.Plan;
import com.nutribox.NutriBox.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public List<Plan> listarTodos() {
        return planRepository.findAll();
    }

    public List<Plan> listarActivos() {
        return planRepository.findByActivoTrue();
    }

    public Plan obtenerPorId(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado con id: " + id));
    }

    public Plan crear(Plan plan) {
        return planRepository.save(plan);
    }

    public Plan actualizar(Long id, Plan planActualizado) {
        Plan plan = obtenerPorId(id);
        plan.setNombre(planActualizado.getNombre());
        plan.setDescripcion(planActualizado.getDescripcion());
        plan.setPrecio(planActualizado.getPrecio());
        plan.setVigenciaDias(planActualizado.getVigenciaDias());
        plan.setSaldoExtra(planActualizado.getSaldoExtra());
        plan.setComidasAproximadas(planActualizado.getComidasAproximadas());
        plan.setDestacado(planActualizado.getDestacado());
        plan.setActivo(planActualizado.getActivo());
        return planRepository.save(plan);
    }

    public void eliminar(Long id) {
        planRepository.deleteById(id);
    }
}