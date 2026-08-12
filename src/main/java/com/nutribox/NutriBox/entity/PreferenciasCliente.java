package com.nutribox.NutriBox.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "preferencias_cliente")
@Getter
@Setter
public class PreferenciasCliente {

    @Id
    private String id;

    private Long usuarioId;

    private List<String> restricciones; // ej. ["vegano", "sin gluten"]
    private List<String> alergiasEvitar; // ej. ["maní", "mariscos"]
    private String notasAdicionales; // texto libre opcional
}