package com.nutribox.NutriBox.repository;

import com.nutribox.NutriBox.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCategoria(Menu.CategoriaMenu categoria);
    List<Menu> findByDisponibleTrue();
}