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
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Switch switch_theme;

    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Ingredient> filtered_ingredients = new ArrayList<>();

    private List<Pizza> pizzas = new ArrayList<>();

    private boolean[] filters = new boolean[7];
    private Chip filter_vegetali, filter_pesce, filter_formaggi, filter_salumi, filter_salse,
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

        createAllList();

    }

    public void createAllList() {
        //INGREDIENTS
        Ingredient olive = new Ingredient(getString(R.string.olive), getString(R.string.vegetali));
        ingredients.add(olive);
        Ingredient zucchine = new Ingredient(getString(R.string.zucchine), getString(R.string.vegetali));
        ingredients.add(zucchine);
        Ingredient melanzane = new Ingredient(getString(R.string.melanzane), getString(R.string.vegetali));
        ingredients.add(melanzane);
        Ingredient peperoni = new Ingredient(getString(R.string.peperoni), getString(R.string.vegetali));
        ingredients.add(peperoni);
        Ingredient asparagi = new Ingredient(getString(R.string.asparagi), getString(R.string.vegetali));
        ingredients.add(asparagi);
        Ingredient friarelli = new Ingredient(getString(R.string.friarelli), getString(R.string.vegetali));
        ingredients.add(friarelli);
        Ingredient rucola = new Ingredient(getString(R.string.rucola), getString(R.string.vegetali));
        ingredients.add(rucola);
        Ingredient carciofi = new Ingredient(getString(R.string.carciofi), getString(R.string.vegetali));
        ingredients.add(carciofi);
        Ingredient funghi_porcini = new Ingredient(getString(R.string.funghi_porcini), getString(R.string.vegetali));
        ingredients.add(funghi_porcini);
        Ingredient funghi_champignon = new Ingredient(getString(R.string.funghi_champignon), getString(R.string.vegetali));
        ingredients.add(funghi_champignon);
        Ingredient zucca = new Ingredient(getString(R.string.zucca), getString(R.string.vegetali));
        ingredients.add(zucca);
        Ingredient patatine_fritte = new Ingredient(getString(R.string.patatine_fritte), getString(R.string.vegetali));
        ingredients.add(patatine_fritte);
        Ingredient patate_lesse = new Ingredient(getString(R.string.patate_lesse), getString(R.string.vegetali));
        ingredients.add(patate_lesse);
        Ingredient cipolla = new Ingredient(getString(R.string.cipolla), getString(R.string.vegetali));
        ingredients.add(cipolla);
        Ingredient capperi = new Ingredient(getString(R.string.capperi), getString(R.string.vegetali));
        ingredients.add(capperi);
        Ingredient fiori_di_zucca = new Ingredient(getString(R.string.fiori_di_zucca), getString(R.string.vegetali));
        ingredients.add(fiori_di_zucca);
        Ingredient pistacchi = new Ingredient(getString(R.string.pistacchi), getString(R.string.vegetali));
        ingredients.add(pistacchi);
        Ingredient radicchio = new Ingredient(getString(R.string.radicchio), getString(R.string.vegetali));
        ingredients.add(radicchio);
        Ingredient fagioli = new Ingredient(getString(R.string.fagioli), getString(R.string.vegetali));
        ingredients.add(fagioli);
        Ingredient mais = new Ingredient(getString(R.string.mais), getString(R.string.vegetali));
        ingredients.add(mais);
        Ingredient noci = new Ingredient(getString(R.string.noci), getString(R.string.vegetali));
        ingredients.add(noci);
        Ingredient cime_di_rapa = new Ingredient(getString(R.string.cime_di_rapa), getString(R.string.vegetali));
        ingredients.add(cime_di_rapa);
        Ingredient pomodori_secchi = new Ingredient(getString(R.string.pomodori_secchi), getString(R.string.vegetali));
        ingredients.add(pomodori_secchi);

        Ingredient speck = new Ingredient(getString(R.string.speck), getString(R.string.salumi));
        ingredients.add(speck);
        Ingredient prosciutto_cotto = new Ingredient(getString(R.string.prosciutto_cotto), getString(R.string.salumi));
        ingredients.add(prosciutto_cotto);
        Ingredient prosciutto_crudo = new Ingredient(getString(R.string.prosciutto_crudo), getString(R.string.salumi));
        ingredients.add(prosciutto_crudo);
        Ingredient salame_piccante = new Ingredient(getString(R.string.salame_piccante), getString(R.string.salumi));
        ingredients.add(salame_piccante);
        Ingredient salame_dolce = new Ingredient(getString(R.string.salame_dolce), getString(R.string.salumi));
        ingredients.add(salame_dolce);
        Ingredient salsiccia = new Ingredient(getString(R.string.salsiccia), getString(R.string.salumi));
        ingredients.add(salsiccia);
        Ingredient nduja = new Ingredient(getString(R.string.nduja), getString(R.string.salumi));
        ingredients.add(nduja);
        Ingredient pancetta = new Ingredient(getString(R.string.pancetta), getString(R.string.salumi));
        ingredients.add(pancetta);
        Ingredient bresaola = new Ingredient(getString(R.string.bresaola), getString(R.string.salumi));
        ingredients.add(bresaola);
        Ingredient uovo = new Ingredient(getString(R.string.uovo), getString(R.string.salumi));
        ingredients.add(uovo);
        Ingredient mortadella = new Ingredient(getString(R.string.mortadella), getString(R.string.salumi));
        ingredients.add(mortadella);
        Ingredient wurstel = new Ingredient(getString(R.string.wurstel), getString(R.string.salumi));
        ingredients.add(wurstel);
        Ingredient capocollo = new Ingredient(getString(R.string.capocollo), getString(R.string.salumi));
        ingredients.add(capocollo);
        Ingredient lardo = new Ingredient(getString(R.string.lardo), getString(R.string.salumi));
        ingredients.add(lardo);

        Ingredient mozzarella_di_bufala = new Ingredient(getString(R.string.mozzarella_di_bufala), getString(R.string.formaggi));
        ingredients.add(mozzarella_di_bufala);
        Ingredient scamorza_affumicata = new Ingredient(getString(R.string.scamorza_affumicata), getString(R.string.formaggi));
        ingredients.add(scamorza_affumicata);
        Ingredient taleggio = new Ingredient(getString(R.string.taleggio), getString(R.string.formaggi));
        ingredients.add(taleggio);
        Ingredient brie = new Ingredient(getString(R.string.brie), getString(R.string.formaggi));
        ingredients.add(brie);
        Ingredient gorgonzola = new Ingredient(getString(R.string.gorgonzola), getString(R.string.formaggi));
        ingredients.add(gorgonzola);
        Ingredient parmigiano = new Ingredient(getString(R.string.parmigiano), getString(R.string.formaggi));
        ingredients.add(parmigiano);
        Ingredient emmenthal = new Ingredient(getString(R.string.emmenthal), getString(R.string.formaggi));
        ingredients.add(emmenthal);
        Ingredient burrata = new Ingredient(getString(R.string.burrata), getString(R.string.formaggi));
        ingredients.add(burrata);
        Ingredient stracciatella = new Ingredient(getString(R.string.stracciatella), getString(R.string.formaggi));
        ingredients.add(stracciatella);
        Ingredient fontina = new Ingredient(getString(R.string.fontina), getString(R.string.formaggi));
        ingredients.add(fontina);

        Ingredient salmone = new Ingredient(getString(R.string.salmone), getString(R.string.pesce));
        ingredients.add(salmone);
        Ingredient tonno = new Ingredient(getString(R.string.tonno), getString(R.string.pesce));
        ingredients.add(tonno);
        Ingredient frutti_di_mare = new Ingredient(getString(R.string.frutti_di_mare), getString(R.string.pesce));
        ingredients.add(frutti_di_mare);
        Ingredient acciughe = new Ingredient(getString(R.string.acciughe), getString(R.string.pesce));
        ingredients.add(acciughe);
        Ingredient gamberetti = new Ingredient(getString(R.string.gamberetti), getString(R.string.pesce));
        ingredients.add(gamberetti);
        Ingredient surimi = new Ingredient(getString(R.string.surimi), getString(R.string.pesce));
        ingredients.add(surimi);
        Ingredient anelli_di_calamari = new Ingredient(getString(R.string.anelli_di_calamari), getString(R.string.pesce));
        ingredients.add(anelli_di_calamari);

        Ingredient pesto_alla_genovese = new Ingredient(getString(R.string.pesto_alla_genovese), getString(R.string.salse));
        ingredients.add(pesto_alla_genovese);
        Ingredient panna = new Ingredient(getString(R.string.panna), getString(R.string.salse));
        ingredients.add(panna);
        Ingredient pesto_di_pistacchi = new Ingredient(getString(R.string.pesto_di_pistacchi), getString(R.string.salse));
        ingredients.add(pesto_di_pistacchi);

        Ingredient pere = new Ingredient(getString(R.string.pere), getString(R.string.frutta));
        ingredients.add(pere);
        Ingredient mele = new Ingredient(getString(R.string.mele), getString(R.string.frutta));
        ingredients.add(mele);
        Ingredient ananas = new Ingredient(getString(R.string.ananas), getString(R.string.frutta));
        ingredients.add(ananas);

        Ingredient origano = new Ingredient(getString(R.string.origano), getString(R.string.spezie));
        ingredients.add(origano);
        Ingredient rosmarino = new Ingredient(getString(R.string.rosmarino), getString(R.string.spezie));
        ingredients.add(rosmarino);
        Ingredient aglio = new Ingredient(getString(R.string.aglio), getString(R.string.spezie));
        ingredients.add(aglio);
        Ingredient peperoncino = new Ingredient(getString(R.string.peperoncino), getString(R.string.spezie));
        ingredients.add(peperoncino);
        Ingredient basilico = new Ingredient(getString(R.string.basilico), getString(R.string.spezie));
        ingredients.add(basilico);


        filtered_ingredients.addAll(ingredients);

        //PIZZE
        Pizza americana = new Pizza(getString(R.string.americana));
        americana.addIngredient(wurstel);
        americana.addIngredient(patatine_fritte);
        pizzas.add(americana);
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
        Pizza p_show;
        String pizza_name;
        int size = pizzas.size();
        int item = new Random().nextInt(size);

        p_show = pizzas.get(item);

        if (p_show != null) {
            pizza_name = p_show.getName();

            TextView textView = findViewById(R.id.textView);
            textView.setText(pizza_name);


            TextView textView1 = findViewById(R.id.textView7);
            TextView textView2 = findViewById(R.id.textView9);
            TextView textView3 = findViewById(R.id.textView10);
            TextView textView4 = findViewById(R.id.textView11);

            textView1.setText("");
            textView2.setText("");
            textView3.setText("");
            textView4.setText("");

            switch (p_show.getIngredients().size()) {
                case 4:
                    textView4.setText(p_show.getIngredients().get(3).getName());
                case 3:
                    textView3.setText(p_show.getIngredients().get(2).getName());
                case 2:
                    textView2.setText(p_show.getIngredients().get(1).getName());
                case 1:
                    textView1.setText(p_show.getIngredients().get(0).getName());
                    break;
                case 0:
                    break;
            }

        }

    }

    public void generate360(View view) {
        Random r1, r2, r3, r4;
        int random_number1 = -1, random_number2 = -1, random_number3 = -1, random_number4 = -1;
        Ingredient ingredient1, ingredient2, ingredient3, ingredient4;
        String ingredient1_name = "", ingredient2_name = "", ingredient3_name = "", ingredient4_name = "";

        if (ingredients.size() != filtered_ingredients.size()) {
            filtered_ingredients.clear();
            filtered_ingredients.addAll(ingredients);
        }

        r1 = new Random();
        r2 = new Random();
        r3 = new Random();
        r4 = new Random();

        if (filters[0] || filters[1] || filters[2] || filters[3] || filters[4] || filters[5] || filters[6]) {
            for (Iterator<Ingredient> iterator = filtered_ingredients.iterator(); iterator.hasNext(); ) {
                Ingredient ingredient = iterator.next();

                if (filters[0] && ingredient.getCategory() != null
                        && ingredient.getCategory().equals(getString(R.string.vegetali))) {
                    iterator.remove();
                }
                if (filters[1] && ingredient.getCategory() != null
                        && ingredient.getCategory().equals(getString(R.string.pesce))) {
                    iterator.remove();
                }
                if (filters[2] && ingredient.getCategory() != null
                        && ingredient.getCategory().equals(getString(R.string.formaggi))) {
                    iterator.remove();
                }
                if (filters[3] && ingredient.getCategory() != null
                        && ingredient.getCategory().equals(getString(R.string.salumi))) {
                    iterator.remove();
                }
                if (filters[4] && ingredient.getCategory() != null
                        && ingredient.getCategory().equals(getString(R.string.salse))) {
                    iterator.remove();
                }
                if (filters[5] && ingredient.getCategory() != null
                        && ingredient.getCategory().equals(getString(R.string.frutta))) {
                    iterator.remove();
                }
                if (filters[6] && ingredient.getCategory() != null
                        && ingredient.getCategory().equals(getString(R.string.spezie))) {
                    iterator.remove();
                }
            }
        }

        if (filtered_ingredients.size() == 0) {
            Toast.makeText(getApplicationContext(), "You're gonna eat nothing!\nPlease remove some filters.", Toast.LENGTH_SHORT).show();
        } else {
            random_number1 = r1.nextInt(filtered_ingredients.size());
            ingredient1 = filtered_ingredients.get(random_number1);
            ingredient1_name = ingredient1.getName();

            if (filtered_ingredients.size() >= 2) {

                do {
                    random_number2 = r2.nextInt(filtered_ingredients.size());
                } while (random_number2 == random_number1);

                ingredient2 = filtered_ingredients.get(random_number2);
                ingredient2_name = ingredient2.getName();

                if (filtered_ingredients.size() >= 3) {

                    do {
                        random_number3 = r3.nextInt(filtered_ingredients.size());
                    } while (random_number3 == random_number1 || random_number3 == random_number2);

                    ingredient3 = filtered_ingredients.get(random_number3);
                    ingredient3_name = ingredient3.getName();

                    if (filtered_ingredients.size() >= 4) {

                        do {
                            random_number4 = r4.nextInt(filtered_ingredients.size());
                        } while (random_number4 == random_number3 || random_number4 == random_number2 ||
                                random_number4 == random_number1);

                        ingredient4 = filtered_ingredients.get(random_number4);
                        ingredient4_name = ingredient4.getName();
                    }

                }
            }

        }

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
                if (random_number4 != -1)
                    textView4.setText(ingredient4_name);
            case R.id.radio_3:
                if (random_number3 != -1)
                    textView3.setText(ingredient3_name);
            case R.id.radio_2:
                if (random_number2 != -1)
                    textView2.setText(ingredient2_name);
            case R.id.radio_1:
                if (random_number1 != -1)
                    textView1.setText(ingredient1_name);
                break;
        }


    }

    public void filtersPopUp(View view) {

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
