package com.nutribox.NutriBox.repository;

import com.nutribox.NutriBox.entity.PreferenciasCliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface PreferenciasClienteRepository extends MongoRepository<PreferenciasCliente, String> {
    Optional<PreferenciasCliente> findByUsuarioId(Long usuarioId);
}