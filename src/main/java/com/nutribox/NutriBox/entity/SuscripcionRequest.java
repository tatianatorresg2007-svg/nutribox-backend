package com.nutribox.NutriBox.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuscripcionRequest {
    private Long usuarioId;
    private Long planId;
}