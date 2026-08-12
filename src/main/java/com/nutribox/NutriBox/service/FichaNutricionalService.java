package com.nutribox.NutriBox.service;

import com.nutribox.NutriBox.entity.FichaNutricional;
import com.nutribox.NutriBox.entity.Menu;
import com.nutribox.NutriBox.repository.FichaNutricionalRepository;
import com.nutribox.NutriBox.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FichaNutricionalService {

    @Autowired
    private FichaNutricionalRepository fichaNutricionalRepository;

    @Autowired
    private MenuRepository menuRepository;

    public FichaNutricional crear(FichaNutricional ficha) {
        // 1. Guarda el documento en MongoDB
        FichaNutricional guardada = fichaNutricionalRepository.save(ficha);

        // 2. Actualiza el Menu en PostgreSQL con la referencia al documento recién creado
        Menu menu = menuRepository.findById(ficha.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menú no encontrado con id: " + ficha.getMenuId()));
        menu.setFichaNutricionalId(guardada.getId());
        menuRepository.save(menu);

        return guardada;
    }

    public FichaNutricional obtenerPorMenuId(Long menuId) {
        return fichaNutricionalRepository.findByMenuId(menuId)
                .orElseThrow(() -> new RuntimeException("Ficha nutricional no encontrada para el menú: " + menuId));
    }
}