package com.kritagya.recipebook.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RecipeResponse {
    private Long id;
    private String title;
    private String description;
    private String instructions;
    private Integer prepTimeMins;
    private Integer cookTimeMins;
    private Integer servings;
    private String imageUrl;
    private List<IngredientResponse> ingredients;
    private List<String> tags;
    private LocalDateTime createdAt;
}
