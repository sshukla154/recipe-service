package com.infosys.controller;

import com.infosys.model.Ingredient;
import com.infosys.request.CreateIngredientRequest;
import com.infosys.service.IngredientService;
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

@Tag(name = "Ingredient Controller", description = "The Ingredient API for performing CRUD operations")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/v1/ingredient")
@Slf4j
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Operation(description = "Create an ingredient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ingredient created"),
            @ApiResponse(responseCode = "400", description = "Bad input"),
    })
    @PostMapping("/")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody CreateIngredientRequest request) {
        log.info("IngredientController.createIngredient : Id : {}", request);
        Ingredient createdIngredient = ingredientService.createIngredient(request);
        return new ResponseEntity<>(createdIngredient, HttpStatus.CREATED);
    }

    @Operation(description = "Delete ingredient by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Ingredient not found by the given ID")
    })
    @DeleteMapping("/")
    public void deleteIngredient(@RequestParam(name = "id") Integer id) {
        log.info("IngredientController.deleteIngredient : Id : {}", id);
        ingredientService.deleteIngredientById(id);
    }

    @Operation(description = "List all ingredients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
    })
    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<List<Ingredient>> getAllIngredients(@PathVariable(name = "page") int page,
                                                              @PathVariable(name = "size") int size) {
        log.info("IngredientController.getAllIngredients : Page - {}, Size - {}", page, size);
        List<Ingredient> list = ingredientService.getAllIngredients(page, size);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(description = "Get Ingredient By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Ingredient not found by the given Id")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable(name = "id") Integer id) {
        log.info("IngredientController.getIngredientById : Id : {}", id);
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }
}
