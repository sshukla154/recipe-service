package com.infosys.search.filter;

import com.infosys.model.Recipe;
import com.infosys.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchFilterNotEqual implements SearchFilter {

    public static final String INGREDIENT_KEY = "ingredient";

    @Override
    public boolean couldBeApplied(SearchOperation opt) {
        return opt == SearchOperation.NOT_EQUAL;
    }

    @Override
    public Predicate apply(CriteriaBuilder cb, String filterKey, String filterValue, Root<Recipe> root, Join<Object, Object> subRoot) {
        if (filterKey.equals(INGREDIENT_KEY))
            return cb.notEqual(subRoot.get(filterKey).as(String.class), filterValue);

        return cb.notEqual(root.get(filterKey).as(String.class), filterValue);
    }
}
