package com.kritagya.recipebook.service;

import com.kritagya.recipebook.dto.request.RecipeRequest;
import com.kritagya.recipebook.dto.response.RecipeResponse;
import com.kritagya.recipebook.exception.RecipeNotFoundException;
import com.kritagya.recipebook.mapper.RecipeMapper;
import com.kritagya.recipebook.model.Recipe;
import com.kritagya.recipebook.repository.RecipeRepository;
import com.kritagya.recipebook.repository.RecipeSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public List<RecipeResponse> getAll(String search, List<String> tags){
        Specification<Recipe> spec = RecipeSpecification.build(search, tags);
        return recipeRepository.findAll(spec)
                .stream()
                .map(recipeMapper::toResponse)
                .toList();
    }

    public RecipeResponse getById(Long id){
        return recipeRepository.findById(id)
                .map(recipeMapper::toResponse)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found."));
    }

    public RecipeResponse create(RecipeRequest request){
        Recipe recipe = recipeMapper.toCreate(request);
        return recipeMapper.toResponse(recipeRepository.save(recipe));
    }

}
