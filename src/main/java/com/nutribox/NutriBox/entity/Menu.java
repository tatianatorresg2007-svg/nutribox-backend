package com.nutribox.NutriBox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "menus")
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaMenu categoria;

    @Column(nullable = false)
    private Boolean disponible = true;

    @Column(name = "imagen_url")
    private String imagenUrl;

    // Referencia al documento de ficha nutricional en MongoDB
    @Column(name = "ficha_nutricional_id")
    private String fichaNutricionalId;

    public enum CategoriaMenu {
        DESAYUNO, ALMUERZO, CENA, SNACK
    }
}