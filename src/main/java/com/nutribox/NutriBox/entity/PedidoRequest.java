package com.nutribox.NutriBox.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PedidoRequest {
    private Long usuarioId;
    private List<ItemPedidoRequest> items;
}