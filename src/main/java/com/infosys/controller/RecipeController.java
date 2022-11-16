package com.infosys.controller;

import com.infosys.model.Recipe;
import com.infosys.request.CreateRecipeRequest;
import com.infosys.request.UpdateRecipeRequest;
import com.infosys.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */

@Tag(name = "Recipe Service", description = "The Recipe API for performing CRUD and Search operations")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/v1/recipes")
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(description = "Create Recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recipe is created"),
            @ApiResponse(responseCode = "400", description = "Bad input request")
    })
    @PostMapping("/")
    public ResponseEntity<Recipe> createRecipe(@RequestBody CreateRecipeRequest recipe) {
        log.info("RecipeController.createRecipe : {}", recipe);
        Recipe savedRecipe = recipeService.createRecipe(recipe);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

    @Operation(description = "Update Recipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ingredient created"),
            @ApiResponse(responseCode = "400", description = "Bad input")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") int id, @RequestBody UpdateRecipeRequest recipe) {
        log.info("RecipeController.updateRecipe : for {} having details {}", id, recipe);
        Recipe updatedRecipe = recipeService.updateRecipe(id, recipe);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }

    @Operation(description = "Get All Recipes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of recipes"),
    })
    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<List<Recipe>> getAllRecipes(@PathVariable(name = "page") int page,
                                                      @PathVariable(name = "size") int size) {
        log.info("RecipeController.getAllRecipes : Page - {}, Size - {}", page, size);
        List<Recipe> getAllRecipes = recipeService.getAllRecipes(page, size);
        return new ResponseEntity<>(getAllRecipes, HttpStatus.OK);
    }

    @Operation(description = "Get Recipe By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Recipe not found by the given ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") int id) throws Exception {
        log.info("RecipeController.getAllRecipes : Id : {}", id);
        Recipe recipeById = recipeService.getRecipeById(id);
        return new ResponseEntity<>(recipeById, HttpStatus.OK);
    }

    @Operation(description = "Delete Recipe By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted Successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Recipe not found by the given ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable("id") int id) {
        log.info("RecipeController.getAllRecipes : Id : {}", id);
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
