package com.nutribox.NutriBox.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "beneficios_plan")
@Getter
@Setter
public class BeneficiosPlan {

    @Id
    private String id;

    private Long planId; // referencia al Plan en PostgreSQL

    private List<String> beneficios; // ej. ["Menú diario desde S/15.5", "Envíos gratis ilimitados", "3% cashback"]
}