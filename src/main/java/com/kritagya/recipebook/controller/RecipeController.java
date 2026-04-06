package com.kritagya.recipebook.controller;

import com.kritagya.recipebook.dto.request.RecipeRequest;
import com.kritagya.recipebook.dto.response.RecipeResponse;
import com.kritagya.recipebook.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@AllArgsConstructor
public class RecipeController {

    private RecipeService recipeService;

    @GetMapping
    public List<RecipeResponse> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<String> tags
    ){
        return recipeService.getAll(search, tags);
    }

    @GetMapping("/{id}")
    public RecipeResponse getById(@PathVariable Long id){
        return recipeService.getById(id);
    }

    @PostMapping("/create")
    public RecipeResponse create(@RequestBody RecipeRequest request){
        return recipeService.create(request);
    }

    @PutMapping("/{id}/update")
    public RecipeResponse update(@PathVariable Long id, @RequestBody RecipeRequest request){
        return recipeService.update(id, request);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        recipeService.delete(id);
    }

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadImage(@PathVariable Long id, @RequestParam MultipartFile image) throws IOException {
        recipeService.uploadImage(id, image);
    }
}
