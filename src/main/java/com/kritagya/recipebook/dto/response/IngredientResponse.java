package com.kritagya.recipebook.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngredientResponse {

    private Long id;

    private String name;

    private BigDecimal quantity;

    private String unit;
}
