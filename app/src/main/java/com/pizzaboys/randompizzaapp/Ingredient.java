package com.pizzaboys.randompizzaapp;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private String name;
    private String category;

    private List<Ingredient> good_matches = new ArrayList<>();
    private List<Ingredient> bad_matches = new ArrayList<>();

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

    public void addGoodMatch(Ingredient ingredient){
        this.good_matches.add(ingredient);
    }

    public void addBadMatch(Ingredient ingredient){
        this.bad_matches.add(ingredient);
    }
}
