package com.kritagya.recipebook.controller;

import com.kritagya.recipebook.dto.request.RecipeRequest;
import com.kritagya.recipebook.dto.response.RecipeResponse;
import com.kritagya.recipebook.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private RecipeService recipeService;

    @GetMapping
    public List<RecipeResponse> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<String> tags
    ){
        return recipeService.getAll(search, tags);
    }

    @PostMapping("/create")
    public RecipeResponse create(RecipeRequest request){
        return recipeService.create(request);
    }
}
