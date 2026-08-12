package com.nutribox.NutriBox.repository;

import com.nutribox.NutriBox.entity.Suscripcion;
import com.nutribox.NutriBox.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
    List<Suscripcion> findByUsuario(Usuario usuario);
    List<Suscripcion> findByUsuarioAndEstado(Usuario usuario, Suscripcion.EstadoSuscripcion estado);
}