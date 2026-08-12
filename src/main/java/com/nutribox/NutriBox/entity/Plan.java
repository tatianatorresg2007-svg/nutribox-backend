package com.nutribox.NutriBox.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "planes")
@Getter
@Setter
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nombre; // ej. "Inicio", "Ahorro", "Flexible"

    @Column(length = 200)
    private String descripcion; // ej. "Envíos ilimitados para comer bien diario"

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "vigencia_dias", nullable = false)
    private Integer vigenciaDias; // ej. 45, 60, 90

    @Column(name = "saldo_extra", precision = 10, scale = 2)
    private BigDecimal saldoExtra = BigDecimal.ZERO; // ej. +S/12

    @Column(name = "comidas_aproximadas")
    private Integer comidasAproximadas; // ej. ≈ 20 comidas

    @Column(nullable = false)
    private Boolean destacado = false; // para marcar "Más popular" en el frontend

    @Column(nullable = false)
    private Boolean activo = true;
}