package com.nutribox.NutriBox.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "resenas")
@Getter
@Setter
public class Resena {

    @Id
    private String id;

    private Long menuId;       // qué menú se reseña
    private Long usuarioId;    // quién la escribió
    private String nombreUsuario; // para mostrar sin tener que consultar Postgres cada vez

    private Integer calificacion; // 1 a 5 estrellas
    private String comentario;

    private LocalDateTime fecha = LocalDateTime.now();
}