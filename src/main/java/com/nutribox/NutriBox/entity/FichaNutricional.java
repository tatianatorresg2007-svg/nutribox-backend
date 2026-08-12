package com.nutribox.NutriBox.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Document(collection = "fichas_nutricionales")
@Getter
@Setter
public class FichaNutricional {

    @Id
    private String id;

    private Long menuId; // referencia al Menu en PostgreSQL

    private Integer calorias;
    private Double proteinas;
    private Double carbohidratos;
    private Double grasas;

    private List<String> ingredientes;
    private List<String> alergenos;
    private List<String> etiquetas; // ej. "vegano", "sin gluten", "keto", "bajo en sodio"

    // Campos extra opcionales que varían según el tipo de menú
    // (ej. "indice_glucemico" solo aplica a algunos, "porcion_gramos" a otros)
    private Map<String, Object> datosAdicionales;
}