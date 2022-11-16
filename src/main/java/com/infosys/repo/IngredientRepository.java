package com.infosys.repo;

import com.infosys.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 'Seemant Shukla' on '15/11/2022'
 */

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
