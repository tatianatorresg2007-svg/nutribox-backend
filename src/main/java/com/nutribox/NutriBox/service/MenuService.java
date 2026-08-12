package com.nutribox.NutriBox.service;

import com.nutribox.NutriBox.entity.Menu;
import com.nutribox.NutriBox.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> listarTodos() {
        return menuRepository.findAll();
    }

    public List<Menu> listarDisponibles() {
        return menuRepository.findByDisponibleTrue();
    }

    public List<Menu> listarPorCategoria(Menu.CategoriaMenu categoria) {
        return menuRepository.findByCategoria(categoria);
    }

    public Menu obtenerPorId(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menú no encontrado con id: " + id));
    }

    public Menu crear(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu actualizar(Long id, Menu menuActualizado) {
        Menu menu = obtenerPorId(id);
        menu.setNombre(menuActualizado.getNombre());
        menu.setDescripcion(menuActualizado.getDescripcion());
        menu.setPrecio(menuActualizado.getPrecio());
        menu.setCategoria(menuActualizado.getCategoria());
        menu.setDisponible(menuActualizado.getDisponible());
        menu.setImagenUrl(menuActualizado.getImagenUrl());
        return menuRepository.save(menu);
    }

    public void eliminar(Long id) {
        menuRepository.deleteById(id);
    }
}