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
public class UpdateRecipeRequest {

    @NotNull(message = "Id cannot be Null or blank or empty")
    @Positive(message = "Id should be position and greater than zero")
    @Schema(description = "Id attribute", example = "1")
    private Integer id;

    @NotBlank(message = "Name of the recipe can't be blank")
    @Schema(description = "The name of the ingredient", example = "Cumin")
    private String name;

    @Schema(description = "The type of the recipe", example = "VEGETARIAN")
    private String foodType;

    @NotNull(message = "Number of servings cannot be Null or blank or empty")
    @Positive(message = "Number of servings should be position and greater than zero")
    @Schema(description = "The number of servings", example = "5")
    private int noOfServings;

    @Schema(description = "New ingredients for recipe", example = "[4]")
    private List<Integer> ingredientIds;

    @NotBlank(message = "Instructions cannot be Null or blank or empty")
    @Schema(description = "Updated instruction of recipe", example = "Deep Fry and less spicy")
    private String instructions;

}
