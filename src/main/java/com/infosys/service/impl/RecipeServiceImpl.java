package com.infosys.service.impl;

import com.infosys.exception.ResourceNotFoundException;
import com.infosys.model.Ingredient;
import com.infosys.model.Recipe;
import com.infosys.repo.RecipeRepository;
import com.infosys.request.CreateRecipeRequest;
import com.infosys.request.RecipeSearchRequest;
import com.infosys.request.SearchCriteriaRequest;
import com.infosys.request.UpdateRecipeRequest;
import com.infosys.response.RecipeResponse;
import com.infosys.search.RecipeSpecificationBuilder;
import com.infosys.search.SearchCriteria;
import com.infosys.service.IngredientService;
import com.infosys.service.RecipeService;
import com.infosys.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */

@Service
@Transactional
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final IngredientService ingredientService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public Recipe createRecipe(CreateRecipeRequest createRecipeRequest) {
        Set<Ingredient> ingredients = Optional.ofNullable(createRecipeRequest.getIngredientIds())
                .map(ingredientService::getIngredientsByIds)
                .orElse(null);

        Recipe recipe = new Recipe();
        recipe.setName(createRecipeRequest.getName());
        recipe.setInstructions(createRecipeRequest.getInstructions());
        recipe.setFoodType(createRecipeRequest.getFoodType());
        recipe.setNoOfServings(createRecipeRequest.getNoOfServings());
        recipe.setIngredientSet(ingredients);
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(int id, UpdateRecipeRequest updateRecipeRequest) {
        log.info("RecipeServiceImpl.updateRecipe : Recipe - {}", updateRecipeRequest);
        Recipe recipeById = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Constants.RECIPE_MESSAGE_NOT_FOUND + id));
        Set<Ingredient> ingredients = Optional.ofNullable(updateRecipeRequest.getIngredientIds())
                .map(ingredientService::getIngredientsByIds)
                .orElse(null);

        recipeById.setName(updateRecipeRequest.getName());
        recipeById.setFoodType(updateRecipeRequest.getFoodType());
        recipeById.setNoOfServings(updateRecipeRequest.getNoOfServings());
        recipeById.setInstructions(updateRecipeRequest.getInstructions());

        if (Optional.ofNullable(ingredients).isPresent()) recipeById.setIngredientSet(ingredients);

        return recipeRepository.save(recipeById);
    }

    @Override
    public List<Recipe> getAllRecipes(int page, int size) {
        log.info("RecipeServiceImpl.getAllRecipes : ");
        Pageable pageRequest = PageRequest.of(page, size);
        return new ArrayList<>(recipeRepository.findAll(pageRequest).getContent());
    }

    @Override
    public Recipe getRecipeById(int id) {
        log.info("RecipeServiceImpl.getRecipeById : Recipe Id - {}", id);
        return recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Constants.RECIPE_MESSAGE_NOT_FOUND + id));
    }

    @Override
    public void deleteRecipe(int id) {
        log.info("RecipeServiceImpl.deleteRecipe : Recipe Id - {}", id);
        if (!recipeRepository.existsById(id))
            throw new ResourceNotFoundException(Constants.RECIPE_MESSAGE_NOT_FOUND + id);
        recipeRepository.deleteById(id);
    }

    @Override
    public List<RecipeResponse> findBySearchCriteria(RecipeSearchRequest recipeSearchRequest, int page, int size) {
        List<SearchCriteria> searchCriterionRequests = new ArrayList<>();
        RecipeSpecificationBuilder builder = new RecipeSpecificationBuilder(searchCriterionRequests);
        Pageable pageRequest = PageRequest.of(page, size, Sort.by("name")
                .ascending());

        Specification<Recipe> recipeSpecification = createRecipeSpecification(recipeSearchRequest, builder);
        Page<Recipe> filteredRecipes = recipeRepository.findAll(recipeSpecification, pageRequest);

        return filteredRecipes.toList().stream()
                .map(RecipeResponse::new)
                .collect(Collectors.toList());
    }

    private Specification<Recipe> createRecipeSpecification(RecipeSearchRequest recipeSearchRequest,
                                                            RecipeSpecificationBuilder builder) {
        List<SearchCriteriaRequest> searchCriteriaRequests = recipeSearchRequest.getSearchCriteriaRequests();

        if (Optional.ofNullable(searchCriteriaRequests).isPresent()) {
            List<SearchCriteria> searchCriteriaList = searchCriteriaRequests.stream()
                    .map(SearchCriteria::new)
                    .collect(Collectors.toList());

            if (!searchCriteriaList.isEmpty()) searchCriteriaList.forEach(criteria -> {
                criteria.setDataOption(recipeSearchRequest.getDataOption());
                builder.with(criteria);
            });
        }

        return builder
                .build()
                .orElseThrow(() -> new ResourceNotFoundException(Constants.CRITERIA_MESSAGE_NOT_FOUND));
    }

}
