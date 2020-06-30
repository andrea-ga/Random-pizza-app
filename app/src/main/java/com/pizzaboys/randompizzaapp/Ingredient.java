package com.pizzaboys.randompizzaapp;

public class Ingredient {
    private String name;
    private String category;

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
