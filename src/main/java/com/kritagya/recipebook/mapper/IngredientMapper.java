package com.kritagya.recipebook.mapper;

import com.kritagya.recipebook.dto.request.IngredientRequest;
import com.kritagya.recipebook.dto.response.IngredientResponse;
import com.kritagya.recipebook.model.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientResponse toResponse(Ingredient ingredient){
        IngredientResponse response = new IngredientResponse();

        response.setId(ingredient.getId());
        response.setName(ingredient.getName());
        response.setUnit(ingredient.getUnit());
        response.setQuantity(ingredient.getQuantity());

        return response;
    }

    public Ingredient toCreate(IngredientRequest createRequest){
        Ingredient ingredient = new Ingredient();

        ingredient.setName(createRequest.getName());
        ingredient.setUnit(createRequest.getUnit());
        ingredient.setQuantity(createRequest.getQuantity());

        return ingredient;
    }
}
