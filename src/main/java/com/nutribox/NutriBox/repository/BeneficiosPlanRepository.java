package com.nutribox.NutriBox.repository;

import com.nutribox.NutriBox.entity.BeneficiosPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface BeneficiosPlanRepository extends MongoRepository<BeneficiosPlan, String> {
    Optional<BeneficiosPlan> findByPlanId(Long planId);
}