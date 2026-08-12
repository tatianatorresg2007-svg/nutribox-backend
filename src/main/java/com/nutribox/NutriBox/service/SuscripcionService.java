package com.nutribox.NutriBox.service;

import com.nutribox.NutriBox.entity.*;
import com.nutribox.NutriBox.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class SuscripcionService {

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlanRepository planRepository;

    public List<Suscripcion> listarPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return suscripcionRepository.findByUsuario(usuario);
    }

    public Suscripcion obtenerPorId(Long id) {
        return suscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada con id: " + id));
    }

    public Suscripcion suscribir(Long usuarioId, Long planId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));

        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setUsuario(usuario);
        suscripcion.setPlan(plan);
        suscripcion.setFechaInicio(LocalDate.now());
        suscripcion.setFechaFin(LocalDate.now().plusDays(plan.getVigenciaDias())); // calculado, no lo manda el cliente
        suscripcion.setEstado(Suscripcion.EstadoSuscripcion.ACTIVA);

        return suscripcionRepository.save(suscripcion);
    }

    public Suscripcion pausar(Long id) {
        Suscripcion suscripcion = obtenerPorId(id);
        suscripcion.setEstado(Suscripcion.EstadoSuscripcion.PAUSADA);
        return suscripcionRepository.save(suscripcion);
    }

    public Suscripcion cancelar(Long id) {
        Suscripcion suscripcion = obtenerPorId(id);
        suscripcion.setEstado(Suscripcion.EstadoSuscripcion.CANCELADA);
        return suscripcionRepository.save(suscripcion);
    }
}