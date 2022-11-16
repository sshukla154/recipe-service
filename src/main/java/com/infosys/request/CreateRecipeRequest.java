package com.infosys.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeRequest {

    @NotBlank(message = "Recipe name is mandatory.")
    @Schema(description = "The name of the recipe", example = "Pasta")
    private String name;

    @Schema(description = "The type of the recipe", example = "VEGETARIAN")
    private String foodType;

    @NotNull(message = "Number of servings can't be null")
    @Positive(message = "Number of servings can't be negative")
    @Schema(description = "The number of servings per recipe", example = "5")
    private int noOfServings;

    @Schema(description = "Ingredient ids", example = "[1,2]")
    private List<Integer> ingredientIds;

    @NotBlank(message = "Instructions can't be blank")
    @Schema(description = "Instructions to create the recipe", example = "Spicy, less salt, hot and grilled chicken")
    private String instructions;

}
