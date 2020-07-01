package com.pizzaboys.randompizzaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Switch switch_theme;

    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Pizza> pizzas = new ArrayList<>();

    private boolean[] filters = new boolean[7];
    private Chip filter_vegetali, filter_salumi, filter_formaggi, filter_pesce, filter_salse,
                    filter_frutta, filter_spezie;

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

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PizzaFragment()).commit();

            navigationView.setCheckedItem(R.id.nav_traditional);
        }

        createIngredientsList();
        createPizzaList();

    }

    public void createIngredientsList() {
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

    public void createPizzaList() {
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
            case R.id.nav_360:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new P360Fragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {  //.END se il drawer si trova a destra
            drawer.closeDrawer(GravityCompat.START);   //same
        } else {
            super.onBackPressed();
        }
    }

    public void generatePizza(View view) {
        Pizza p_show = null;
        String pizza_name;
        int size = pizzas.size();
        int item = new Random().nextInt(size);
        int i = 0;

        for (Pizza p : pizzas) {
            if (i == item) {
                p_show = p;
                break;
            }
            i++;
        }

        if (p_show != null) {
            pizza_name = p_show.getName();

            TextView textView = findViewById(R.id.textView);
            textView.setText(pizza_name);
        }

    }

    public void generate360(View view) {
        Random r1, r2, r3, r4;
        int random_number1, random_number2, random_number3, random_number4;
        Ingredient ingredient1 = null, ingredient2 = null, ingredient3 = null, ingredient4 = null;
        String ingredient1_name = "", ingredient2_name = "", ingredient3_name = "", ingredient4_name = "";

        r1 = new Random();
        r2 = new Random();
        r3 = new Random();
        r4 = new Random();

        random_number1 = r1.nextInt(ingredients.size());

        do {
            random_number2 = r2.nextInt(ingredients.size());
        } while (random_number2 == random_number1);

        do {
            random_number3 = r3.nextInt(ingredients.size());
        } while (random_number3 == random_number1 || random_number3 == random_number2);

        do {
            random_number4 = r4.nextInt(ingredients.size());
        } while (random_number4 == random_number3 || random_number4 == random_number2 || random_number4 == random_number1);

        ingredient1 = ingredients.get(random_number1);
        ingredient2 = ingredients.get(random_number2);
        ingredient3 = ingredients.get(random_number3);
        ingredient4 = ingredients.get(random_number4);

        if (ingredient1 != null)
            ingredient1_name = ingredient1.getName();
        if (ingredient2 != null)
            ingredient2_name = ingredient2.getName();
        if (ingredient3 != null)
            ingredient3_name = ingredient3.getName();
        if (ingredient4 != null)
            ingredient4_name = ingredient4.getName();


        TextView textView1 = findViewById(R.id.textView3);
        TextView textView2 = findViewById(R.id.textView4);
        TextView textView3 = findViewById(R.id.textView5);
        TextView textView4 = findViewById(R.id.textView6);

        textView1.setText("");
        textView2.setText("");
        textView3.setText("");
        textView4.setText("");

        RadioGroup radioGroup = findViewById(R.id.radio_group);

        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_4:
                textView4.setText(ingredient4_name);
            case R.id.radio_3:
                textView3.setText(ingredient3_name);
            case R.id.radio_2:
                textView2.setText(ingredient2_name);
            case R.id.radio_1:
                textView1.setText(ingredient1_name);
                break;
        }

    }

    public void filtersPopUp(View view){
        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        filter_vegetali = (Chip) popupView.findViewById(R.id.chip_vegetali);
        filter_vegetali.setChecked(filters[0]);
        filter_pesce = (Chip) popupView.findViewById(R.id.chip_pesce);
        filter_pesce.setChecked(filters[1]);
        filter_formaggi = (Chip) popupView.findViewById(R.id.chip_formaggi);
        filter_formaggi.setChecked(filters[2]);
        filter_salumi = (Chip) popupView.findViewById(R.id.chip_salumi);
        filter_salumi.setChecked(filters[3]);
        filter_salse = (Chip) popupView.findViewById(R.id.chip_salse);
        filter_salse.setChecked(filters[4]);
        filter_frutta = (Chip) popupView.findViewById(R.id.chip_frutta);
        filter_frutta.setChecked(filters[5]);
        filter_spezie = (Chip) popupView.findViewById(R.id.chip_spezie);
        filter_spezie.setChecked(filters[6]);


        popupWindow.setFocusable(true);

        popupWindow.setBackgroundDrawable(new ColorDrawable());

        int[] location = new int[2];

        view.getLocationOnScreen(location);

        PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                filters[0] = filter_vegetali.isChecked();
                filters[1] = filter_pesce.isChecked();
                filters[2] = filter_formaggi.isChecked();
                filters[3] = filter_salumi.isChecked();
                filters[4] = filter_salse.isChecked();
                filters[5] = filter_frutta.isChecked();
                filters[6] = filter_spezie.isChecked();
            }
        };

        popupWindow.setOnDismissListener(onDismissListener);

        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] +
                view.getHeight());

    }
}
