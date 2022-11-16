package com.infosys.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RecipeSearchRequest {
    @JsonProperty("criteria")
    @Schema(description = "Search criteria you want to search recipe with")
    @Valid
    private List<SearchCriteriaRequest> searchCriteriaRequests;

    @Schema(description = "If you want all or just one criteria is enough for filter to work", example = "all")
    private String dataOption;

}
