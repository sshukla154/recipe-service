package com.infosys.service;

import com.infosys.model.Ingredient;
import com.infosys.request.CreateIngredientRequest;

import java.util.List;
import java.util.Set;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */
public interface IngredientService {

    Ingredient createIngredient(CreateIngredientRequest request);

    Set<Ingredient> getIngredientsByIds(List<Integer> ingredientIds);

    Ingredient getIngredientById(int id);

    List<Ingredient> getAllIngredients(int page, int size);

    void deleteIngredientById(int id);
}
