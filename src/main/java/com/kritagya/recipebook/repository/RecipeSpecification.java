package com.kritagya.recipebook.repository;

import com.kritagya.recipebook.model.Recipe;
import com.kritagya.recipebook.model.Tag;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RecipeSpecification{
    public static Specification<Recipe> build(String search, List<String> tags) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (search != null && !search.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("title")),
                        "%" + search.toLowerCase() + "%"));
            }
            if (tags != null && !tags.isEmpty()) {
                Join<Recipe, Tag> tagJoin = root.join("tags");
                predicates.add(tagJoin.get("name").in(tags));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
