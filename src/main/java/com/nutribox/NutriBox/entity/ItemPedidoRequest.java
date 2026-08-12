package com.nutribox.NutriBox.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoRequest {
    private Long menuId;
    private Integer cantidad;
}