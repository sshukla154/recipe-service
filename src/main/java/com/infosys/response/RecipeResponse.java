package com.infosys.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.infosys.model.Recipe;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */

@Getter
public class RecipeResponse {
    @Schema(description = "The id of the returned recipe", example = "1")
    private int id;

    @Schema(description = "The name of the returned recipe", example = "Pasta")
    private String name;

    @Schema(description = "The type of the returned recipe", example = "VEGETARIAN")
    private String foodType;

    @Schema(description = "Number of servings", example = "1")
    private int noOfServings;

    @JsonIgnoreProperties("ingredients")
    private Set<IngredientResponse> ingredients;

    @Schema(description = "The instructions of the returned recipe", example = "Chop the onion, add to pasta and boil it")
    private String instructions;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    public RecipeResponse() {
    }

    public RecipeResponse(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.foodType = recipe.getFoodType();
        this.instructions = recipe.getInstructions();
        this.createdAt = recipe.getCreatedAt();
        this.updatedAt = recipe.getUpdatedAt();
        this.noOfServings = recipe.getNoOfServings();

        this.ingredients = recipe.getIngredientSet() != null ?
                recipe.getIngredientSet().stream()
                        .map(IngredientResponse::new)
                        .collect(Collectors.toSet())
                : null;
    }

}
