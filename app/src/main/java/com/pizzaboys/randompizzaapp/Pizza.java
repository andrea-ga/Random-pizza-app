package com.pizzaboys.randompizzaapp;

import java.util.Collection;
import java.util.HashSet;

public class Pizza {
    private String name;
    private Collection<Ingredient> pizza_ingredients = new HashSet<>();

    public Pizza(String name) {
        this.name = name;
    }

    public Pizza(String name, Collection<Ingredient> pizza_ingredients) {
        this.name = name;
        this.pizza_ingredients = pizza_ingredients;
    }

    public String getName() {
        return name;
    }

    public Collection<Ingredient> getIngredients() {
        return pizza_ingredients;
    }
}
