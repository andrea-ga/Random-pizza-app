package com.pizzaboys.randompizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Collection;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private Collection<Ingredient> ingredients = new HashSet<>();
    private Collection<Pizza> pizzas = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createIngredientsSet();
        createPizzaSet();

    }

    public void createIngredientsSet(){
        Ingredient patateLesse = new Ingredient("Patate lesse");
        ingredients.add(patateLesse);
        Ingredient patatineFritte = new Ingredient("Patatine fritte");
        ingredients.add(patatineFritte);
        Ingredient pomodoro = new Ingredient("Pomodoro");
        ingredients.add(pomodoro);
        Ingredient funghi = new Ingredient("Funghi");
        ingredients.add(funghi);
        Ingredient pesto = new Ingredient("Pesto","Salse");
        ingredients.add(pesto);
        Ingredient peperoni = new Ingredient("Peperoni");
        ingredients.add(peperoni);
        Ingredient salsiccia = new Ingredient("Salsiccia");
        ingredients.add(salsiccia);
        Ingredient wurstel = new Ingredient("Wurstel");
        ingredients.add(wurstel);
        Ingredient olive = new Ingredient("Olive","Verdure");
        ingredients.add(olive);
        Ingredient tonno = new Ingredient("Tonno");
        ingredients.add(tonno);
        Ingredient zucchine = new Ingredient("Zucchine","Verdure");
        ingredients.add(zucchine);
        Ingredient panna = new Ingredient("Panna","Salse");
        ingredients.add(panna);
    }

    public void createPizzaSet(){
        Pizza margherita = new Pizza("Margherita");
        pizzas.add(margherita);
        Pizza marinara = new Pizza("Marinara");
        pizzas.add(marinara);
        Pizza quattroFormaggi = new Pizza("4 Formaggi");
        pizzas.add(quattroFormaggi);
        Pizza vegetariana = new Pizza("Vegetariana");
        pizzas.add(vegetariana);
    }
}