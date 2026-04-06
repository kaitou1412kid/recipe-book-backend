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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @Transactional
    public RecipeResponse create(RecipeRequest request){
        Recipe recipe = recipeMapper.toCreate(request);
        return recipeMapper.toResponse(recipeRepository.save(recipe));
    }

    @Transactional
    public RecipeResponse update(Long id, RecipeRequest request){
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found."));

        recipeMapper.toUpdate(request, recipe);
        return recipeMapper.toResponse(recipeRepository.save(recipe));
    }

    public void delete(Long id){
        recipeRepository.deleteById(id);
    }

    public void uploadImage(Long id, MultipartFile image) throws IOException {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found."));

        Path uploadPath = Paths.get("uploads/");
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        String fileName = image.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        recipe.setImageURL(filePath.toString());

        recipeRepository.save(recipe);
    }
}
