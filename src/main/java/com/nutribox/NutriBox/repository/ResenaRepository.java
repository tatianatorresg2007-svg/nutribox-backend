package com.nutribox.NutriBox.repository;

import com.nutribox.NutriBox.entity.Resena;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ResenaRepository extends MongoRepository<Resena, String> {
    List<Resena> findByMenuId(Long menuId);
    List<Resena> findByUsuarioId(Long usuarioId);
}