package com.pizzaboys.randompizzaapp;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private List<Ingredient> pizza_ingredients;

    public Pizza(String name) {
        this.name = name;
    }

    public Pizza(String name, List<Ingredient> pizza_ingredients) {
        this.name = name;
        this.pizza_ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return pizza_ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        pizza_ingredients.add(ingredient);
    }
}
