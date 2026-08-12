package com.nutribox.NutriBox.repository;

import com.nutribox.NutriBox.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByActivoTrue();
}