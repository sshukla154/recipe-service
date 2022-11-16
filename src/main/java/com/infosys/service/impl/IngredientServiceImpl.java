package com.infosys.service.impl;

import com.infosys.exception.ResourceNotFoundException;
import com.infosys.model.Ingredient;
import com.infosys.repo.IngredientRepository;
import com.infosys.request.CreateIngredientRequest;
import com.infosys.service.IngredientService;
import com.infosys.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient createIngredient(CreateIngredientRequest request) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(request.getName());
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Set<Ingredient> getIngredientsByIds(List<Integer> ingredientIds) {
        return ingredientIds.stream()
                .map(this::getIngredientById)
                .collect(Collectors.toSet());
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.INGREDIENT_MESSAGE_NOT_FOUND + id));
    }

    @Override
    public List<Ingredient> getAllIngredients(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Ingredient> findAll = ingredientRepository.findAll(pageRequest);
        return findAll.getContent();
    }

    @Override
    public void deleteIngredientById(int id) {
        if (!ingredientRepository.existsById(id)) {
            throw new ResourceNotFoundException(Constants.INGREDIENT_MESSAGE_NOT_FOUND + id);
        }
        ingredientRepository.deleteById(id);

    }
}
