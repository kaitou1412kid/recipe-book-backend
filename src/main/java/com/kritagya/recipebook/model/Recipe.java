package com.kritagya.recipebook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Recipes")
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String instruction;

    private Integer prepTimeMins;
    private Integer cookTimeMins;
    private Integer servings;
    private String imageURL;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Helper methods to keep both sides of the relationship in sync
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.setRecipe(this);
    }

    public void clearIngredients() {
        ingredients.forEach(i -> i.setRecipe(null));
        ingredients.clear();
    }

    public void clearTags() {
        tags.forEach(t -> t.setRecipe(null));
        tags.clear();
    }
}
