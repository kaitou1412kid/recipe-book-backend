package com.kritagya.recipebook.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class RecipeRequest {
    @NotBlank
    private String title;
    private String description;
    private String instructions;
    private Integer prepTimeMins;
    private Integer cookTimeMins;
    private Integer servings;
    private String imageURL;
    private List<IngredientRequest> ingredients;
    private List<String> tags;
}
