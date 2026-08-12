package com.nutribox.NutriBox.repository;

import com.nutribox.NutriBox.entity.FichaNutricional;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface FichaNutricionalRepository extends MongoRepository<FichaNutricional, String> {
    Optional<FichaNutricional> findByMenuId(Long menuId);
}