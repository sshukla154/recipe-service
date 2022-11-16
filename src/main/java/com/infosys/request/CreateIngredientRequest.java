package com.infosys.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateIngredientRequest {

    @NotBlank(message = "Ingredient name can't be blank")
    @Schema(description = "The name of the ingredient", example = "Tomato")
    private String name;

}
