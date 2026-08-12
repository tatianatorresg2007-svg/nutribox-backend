package com.nutribox.NutriBox.service;

import com.nutribox.NutriBox.entity.PreferenciasCliente;
import com.nutribox.NutriBox.repository.PreferenciasClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferenciasClienteService {

    @Autowired
    private PreferenciasClienteRepository preferenciasClienteRepository;

    public PreferenciasCliente obtenerPorUsuario(Long usuarioId) {
        return preferenciasClienteRepository.findByUsuarioId(usuarioId)
                .orElse(null); // puede que un cliente todavía no tenga preferencias guardadas, no es un error
    }

    public PreferenciasCliente guardar(PreferenciasCliente preferencias) {
        // Si ya existían preferencias para este usuario, las reemplaza en vez de duplicar
        PreferenciasCliente existente = preferenciasClienteRepository.findByUsuarioId(preferencias.getUsuarioId())
                .orElse(null);

        if (existente != null) {
            preferencias.setId(existente.getId());
        }

        return preferenciasClienteRepository.save(preferencias);
    }
}