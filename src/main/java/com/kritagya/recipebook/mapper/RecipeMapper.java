package com.kritagya.recipebook.mapper;

import com.kritagya.recipebook.dto.request.RecipeRequest;
import com.kritagya.recipebook.dto.response.RecipeResponse;
import com.kritagya.recipebook.model.Ingredient;
import com.kritagya.recipebook.model.Recipe;
import com.kritagya.recipebook.model.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RecipeMapper {

    private IngredientMapper ingredientMapper;
    private TagMapper tagMapper;

    public RecipeResponse toResponse(Recipe recipe){
        RecipeResponse response = new RecipeResponse();

        response.setId(recipe.getId());
        response.setDescription(recipe.getDescription());
        response.setTitle(recipe.getTitle());
        response.setInstructions(recipe.getInstruction());
        response.setPrepTimeMins(recipe.getPrepTimeMins());
        response.setCookTimeMins(recipe.getCookTimeMins());
        response.setServings(recipe.getServings());
        response.setImageUrl(recipe.getImageURL());
        response.setCreatedAt(recipe.getCreatedAt());
        response.setIngredients(recipe.getIngredients().stream().map(ingredientMapper::toResponse).toList());
        response.setTags(recipe.getTags().stream().map(tag -> tag.getName()).toList());

        return response;
    }

    public Recipe toCreate(RecipeRequest createRequest){
        Recipe recipe = new Recipe();

        recipe.setTitle(createRequest.getTitle());
        recipe.setDescription(createRequest.getDescription());
        recipe.setInstruction(createRequest.getInstructions());
        recipe.setPrepTimeMins(createRequest.getPrepTimeMins());
        recipe.setCookTimeMins(createRequest.getCookTimeMins());
        recipe.setServings(createRequest.getServings());
        recipe.setImageURL(createRequest.getImageURL());

        if (createRequest.getIngredients() != null) {
            createRequest.getIngredients().forEach(i ->
                    recipe.addIngredient(ingredientMapper.toCreate(i))
            );
        }

        if (createRequest.getTags() != null) {
            createRequest.getTags().forEach(r ->
                    recipe.addTag(tagMapper.toCreate(r)));
        }
        return recipe;
    }
}
