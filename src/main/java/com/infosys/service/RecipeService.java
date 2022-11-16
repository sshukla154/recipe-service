package com.infosys.service;

import com.infosys.model.Recipe;
import com.infosys.request.CreateRecipeRequest;
import com.infosys.request.RecipeSearchRequest;
import com.infosys.request.UpdateRecipeRequest;
import com.infosys.response.RecipeResponse;

import java.util.List;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */

public interface RecipeService {
    Recipe createRecipe(CreateRecipeRequest recipe);

    Recipe updateRecipe(int id, UpdateRecipeRequest recipe);

    List<Recipe> getAllRecipes(int page, int size);

    Recipe getRecipeById(int id) throws Exception;

    void deleteRecipe(int id);

    List<RecipeResponse> findBySearchCriteria(RecipeSearchRequest recipeSearchRequest, int page, int size);

}
