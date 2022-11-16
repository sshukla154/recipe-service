package com.infosys.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@Valid
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SearchCriteriaRequest {

    @Schema(description = "The name of the column you want to search on available fields are " +
            "name, " +
            "numberOfServings, " +
            "type, " +
            "instructions, " +
            "ingredientName)", example = "name")
    private String filterKey;


    @Schema(description = "The actual phrase you want to do search on", example = "Pasta")
    private Object value;

    @Schema(description = "The operation type you wanted to search (cn - contains, " +
            "nc - doesn't contain, " +
            "eq - equals, " +
            "ne - not equals", example = "cn")
    private String operation;

    @Schema(hidden = true)
    private String dataOption;

}
