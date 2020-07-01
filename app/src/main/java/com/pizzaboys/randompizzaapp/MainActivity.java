package com.pizzaboys.randompizzaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Switch switch_theme;

    private Collection<Ingredient> ingredients = new HashSet<>();
    private Collection<Pizza> pizzas = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //SWITCH_THEME
        MenuItem menuItem = navigationView.getMenu().findItem(R.id.app_bar_switch);

        switch_theme = menuItem.getActionView().findViewById(R.id.drawer_switch);

            //SEE IF THERE IS NIGHT MODE BY DEFAULT
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
            switch_theme.setChecked(true);
        }

            //SET LISTENER
        switch_theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

                Toast.makeText(getApplicationContext(), "Theme changed - " +
                                (b ? switch_theme.getTextOn().toString() : switch_theme.getTextOff().toString()),
                        Toast.LENGTH_SHORT).show();
            }
        });
        //END SWITCH_THEME

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PizzaFragment()).commit();

            navigationView.setCheckedItem(R.id.nav_traditional);
        }

        createIngredientsSet();
        createPizzaSet();

    }

    public void createIngredientsSet() {
        Ingredient patateLesse = new Ingredient("Patate lesse");
        ingredients.add(patateLesse);
        Ingredient patatineFritte = new Ingredient("Patatine fritte");
        ingredients.add(patatineFritte);
        Ingredient pomodoro = new Ingredient("Pomodoro");
        ingredients.add(pomodoro);
        Ingredient funghi = new Ingredient("Funghi");
        ingredients.add(funghi);
        Ingredient pesto = new Ingredient("Pesto", "Salse");
        ingredients.add(pesto);
        Ingredient peperoni = new Ingredient("Peperoni");
        ingredients.add(peperoni);
        Ingredient salsiccia = new Ingredient("Salsiccia");
        ingredients.add(salsiccia);
        Ingredient wurstel = new Ingredient("Wurstel");
        ingredients.add(wurstel);
        Ingredient olive = new Ingredient("Olive", "Verdure");
        ingredients.add(olive);
        Ingredient tonno = new Ingredient("Tonno");
        ingredients.add(tonno);
        Ingredient zucchine = new Ingredient("Zucchine", "Verdure");
        ingredients.add(zucchine);
        Ingredient panna = new Ingredient("Panna", "Salse");
        ingredients.add(panna);
    }

    public void createPizzaSet() {
        Pizza margherita = new Pizza("Margherita");
        pizzas.add(margherita);
        Pizza marinara = new Pizza("Marinara");
        pizzas.add(marinara);
        Pizza quattroFormaggi = new Pizza("4 Formaggi");
        pizzas.add(quattroFormaggi);
        Pizza vegetariana = new Pizza("Vegetariana");
        pizzas.add(vegetariana);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_traditional:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PizzaFragment()).commit();
                break;
            //CREATE FRAGMENTS
        }

        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){  //.END se il drawer si trova a destra
            drawer.closeDrawer(GravityCompat.START);   //same
        } else{
            super.onBackPressed();
        }
    }

    public void generatePizza(View view){
        Pizza p_show = null;
        String pizza_name;
        int size = pizzas.size();
        int item = new Random().nextInt(size);
        int i = 0;

        for(Pizza p : pizzas){
            if(i == item){
                p_show = p;
                break;
            }
            i++;
        }

        if(p_show!=null) {
            pizza_name = p_show.getName();

            TextView textView = findViewById(R.id.textView);
            textView.setText(pizza_name);
        }

    }
}