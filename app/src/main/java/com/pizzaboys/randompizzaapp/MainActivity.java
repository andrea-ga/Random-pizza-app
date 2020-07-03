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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Switch switch_theme;

    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Ingredient> filtered_ingredients = new ArrayList<>();
    private List<Ingredient> good_matches_1 = new ArrayList<>();
    private List<Ingredient> bad_matches_1 = new ArrayList<>();
    private List<Ingredient> good_matches_2 = new ArrayList<>();
    private List<Ingredient> bad_matches_2 = new ArrayList<>();
    private List<Ingredient> good_matches_3 = new ArrayList<>();
    private List<Ingredient> bad_matches_3 = new ArrayList<>();

    private List<Pizza> pizzas = new ArrayList<>();

    private boolean[] filters = new boolean[7];
    private Chip filter_vegetali, filter_pesce, filter_formaggi, filter_salumi, filter_salse,
            filter_frutta, filter_spezie;

    private int[] good_pizza_index = {-1, -1, -1};

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
        Ingredient pomodorini = new Ingredient(getString(R.string.pomodorini), getString(R.string.vegetali));
        ingredients.add(pomodorini);

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

        //MATCHES
        salsiccia.addGoodMatch(patate_lesse);
        patate_lesse.addGoodMatch(salsiccia);

        for (Ingredient i : ingredients) {
            if (i.getCategory().equals("Pesce")) {
                salsiccia.addBadMatch(i);
            }
        }

        //PIZZE
        Pizza americana = new Pizza(getString(R.string.americana));
        americana.addIngredient(wurstel);
        americana.addIngredient(patatine_fritte);
        pizzas.add(americana);

        Pizza margherita = new Pizza(getString(R.string.margherita));
        pizzas.add(margherita);

        Pizza marinara = new Pizza(getString(R.string.marinara));
        pizzas.add(marinara);

        Pizza boscaiola = new Pizza(getString(R.string.boscaiola));
        boscaiola.addIngredient(salsiccia);
        boscaiola.addIngredient(funghi_champignon);
        boscaiola.addIngredient(olive);
        pizzas.add(boscaiola);

        Pizza quattro_formaggi = new Pizza(getString(R.string.quattro_formaggi));
        pizzas.add(quattro_formaggi);

        Pizza quattro_stagioni = new Pizza(getString(R.string.quattro_stagioni));
        quattro_stagioni.addIngredient(prosciutto_cotto);
        quattro_stagioni.addIngredient(olive);
        quattro_stagioni.addIngredient(funghi_champignon);
        quattro_stagioni.addIngredient(carciofi);
        pizzas.add(quattro_stagioni);

        Pizza tonno_cipolla = new Pizza(getString(R.string.tonno_cipolla));
        pizzas.add(tonno_cipolla);

        Pizza vegetariana = new Pizza(getString(R.string.vegetariana));
        pizzas.add(vegetariana);

        Pizza parmigiana = new Pizza(getString(R.string.parmigiana));
        parmigiana.addIngredient(melanzane);
        parmigiana.addIngredient(parmigiano);
        pizzas.add(parmigiana);

        Pizza diavola = new Pizza(getString(R.string.diavola));
        diavola.addIngredient(salame_piccante);
        pizzas.add(diavola);

        Pizza parma = new Pizza(getString(R.string.parma));
        parma.addIngredient(prosciutto_crudo);
        parma.addIngredient(rucola);
        pizzas.add(parma);

        Pizza pugliese = new Pizza(getString(R.string.pugliese));
        pugliese.addIngredient(cipolla);
        pizzas.add(pugliese);

        Pizza romana = new Pizza(getString(R.string.romana));
        romana.addIngredient(acciughe);
        romana.addIngredient(origano);
        pizzas.add(romana);

        Pizza napoli = new Pizza(getString(R.string.napoli));
        napoli.addIngredient(acciughe);
        napoli.addIngredient(capperi);
        napoli.addIngredient(origano);
        pizzas.add(napoli);

        Pizza prosciutto_funghi = new Pizza(getString(R.string.prosciutto_funghi));
        pizzas.add(prosciutto_funghi);

        Pizza monzese = new Pizza(getString(R.string.monzese));
        monzese.addIngredient(salsiccia);
        pizzas.add(monzese);

        Pizza bufalina = new Pizza(getString(R.string.bufalina));
        bufalina.addIngredient(mozzarella_di_bufala);
        pizzas.add(bufalina);

        Pizza trevigiana = new Pizza(getString(R.string.trevigiana));
        trevigiana.addIngredient(radicchio);
        trevigiana.addIngredient(speck);
        pizzas.add(trevigiana);

        Pizza tirolese = new Pizza(getString(R.string.tirolese));
        tirolese.addIngredient(speck);
        pizzas.add(tirolese);

        Pizza calzone_margherita = new Pizza(getString(R.string.calzone_margherita));
        pizzas.add(calzone_margherita);

        Pizza calzone_farcito = new Pizza(getString(R.string.calzone_farcito));
        pizzas.add(calzone_farcito);

        Pizza pizza_frutti_di_mare = new Pizza(getString(R.string.pizza_frutti_di_mare));
        pizzas.add(pizza_frutti_di_mare);

        Pizza bismarck = new Pizza(getString(R.string.bismarck));
        bismarck.addIngredient(uovo);
        bismarck.addIngredient(prosciutto_cotto);
        pizzas.add(bismarck);

        Pizza norvegese = new Pizza(getString(R.string.norvegese));
        norvegese.addIngredient(salmone);
        pizzas.add(norvegese);

        Pizza genovese = new Pizza(getString(R.string.genovese));
        genovese.addIngredient(pesto_alla_genovese);
        genovese.addIngredient(pomodorini);
        pizzas.add(genovese);

        Pizza primavera = new Pizza(getString(R.string.primavera));
        primavera.addIngredient(rucola);
        primavera.addIngredient(parmigiano);
        pizzas.add(primavera);

        Pizza speck_zola = new Pizza(getString(R.string.speck_zola));
        pizzas.add(speck_zola);

        Pizza salsiccia_patate = new Pizza(getString(R.string.salsiccia_patate));
        salsiccia_patate.addIngredient(patate_lesse);
        salsiccia_patate.addIngredient(salsiccia);
        pizzas.add(salsiccia_patate);
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
            case R.id.nav_good_pizza:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PGoodFragment()).commit();
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

        createFilteredList(filtered_ingredients);

        if (filtered_ingredients.size() == 0) {
            Toast.makeText(getApplicationContext(), "You're gonna eat nothing!\nPlease remove some filters.", Toast.LENGTH_SHORT).show();
        } else {
            random_number1 = r1.nextInt(filtered_ingredients.size());
            ingredient1 = filtered_ingredients.get(random_number1);
            ingredient1_name = ingredient1.getName();

            if (filtered_ingredients.size() >= 2) {

                do {
                    r2 = new Random();
                    random_number2 = r2.nextInt(filtered_ingredients.size());
                } while (random_number2 == random_number1);

                ingredient2 = filtered_ingredients.get(random_number2);
                ingredient2_name = ingredient2.getName();

                if (filtered_ingredients.size() >= 3) {

                    do {
                        r3 = new Random();
                        random_number3 = r3.nextInt(filtered_ingredients.size());
                    } while (random_number3 == random_number1 || random_number3 == random_number2);

                    ingredient3 = filtered_ingredients.get(random_number3);
                    ingredient3_name = ingredient3.getName();

                    if (filtered_ingredients.size() >= 4) {

                        do {
                            r4 = new Random();
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

    public void generateGood(View view) {

        Random r1, r2, r3, r4;
        int random_number1, random_number2, random_number3, random_number4;
        Ingredient ingredient1, ingredient2 = null, ingredient3 = null, ingredient4 = null;
        String ingredient1_name = "", ingredient2_name = "", ingredient3_name = "", ingredient4_name = "";
        int index = 0;

        if (ingredients.size() != filtered_ingredients.size()) {
            filtered_ingredients.clear();
            filtered_ingredients.addAll(ingredients);
        }

        r1 = new Random();

        createFilteredList(filtered_ingredients);

        Arrays.fill(good_pizza_index, -1);
        good_matches_1.clear();
        bad_matches_1.clear();
        good_matches_2.clear();
        bad_matches_2.clear();
        good_matches_3.clear();
        bad_matches_3.clear();

        if (filtered_ingredients.size() == 0) {
            Toast.makeText(getApplicationContext(), "You're gonna eat nothing!\nPlease remove some filters.",
                    Toast.LENGTH_SHORT).show();
        } else {

            random_number1 = r1.nextInt(filtered_ingredients.size());
            ingredient1 = filtered_ingredients.get(random_number1);
            ingredient1_name = ingredient1.getName();

            findGoodMatch(ingredient1);

            if (filtered_ingredients.size() >= 2) {
                if (good_pizza_index[0] != -1) {
                    ingredient2 = ingredient1.getGood_matches().get(good_pizza_index[0]);
                } else {
                    if (ingredient1.getGood_matches().size() != 0) {

                        do {
                            if (!ingredient1.getGood_matches().get(index).getName().equals(ingredient1_name)) {
                                ingredient2 = ingredient1.getGood_matches().get(index);
                                break;
                            }

                            index++;
                        } while (index < ingredient1.getGood_matches().size());

                    }

                    if (index == ingredient1.getGood_matches().size()) {

                        do {
                            r2 = new Random();
                            random_number2 = r2.nextInt(filtered_ingredients.size());
                            ingredient2 = filtered_ingredients.get(random_number2);
                        } while (ingredient2.getName().equals(ingredient1_name));
                    }
                }

                ingredient2_name = ingredient2.getName();

                if (filtered_ingredients.size() >= 3) {
                    if (good_pizza_index[1] != -1) {
                        ingredient3 = ingredient2.getGood_matches().get(good_pizza_index[1]);
                    } else {

                        index = 0;

                        if (ingredient2.getGood_matches().size() != 0) {

                            do {
                                if (!ingredient2.getGood_matches().get(index).getName().equals(ingredient1_name) &&
                                        !ingredient2.getGood_matches().get(index).getName().equals(ingredient2_name)) {
                                    ingredient3 = ingredient2.getGood_matches().get(index);
                                    break;
                                }

                                index++;
                            } while (index < ingredient2.getGood_matches().size());

                        }

                        if (index == ingredient2.getGood_matches().size()) {
                            do {
                                r3 = new Random();
                                random_number3 = r3.nextInt(filtered_ingredients.size());
                                ingredient3 = filtered_ingredients.get(random_number3);
                            } while (ingredient3.getName().equals(ingredient2_name) ||
                                    ingredient3.getName().equals(ingredient1_name));
                        }
                    }

                    ingredient3_name = ingredient3.getName();

                    if (filtered_ingredients.size() >= 4) {
                        if (good_pizza_index[2] != -1) {
                            ingredient4 = ingredient3.getGood_matches().get(good_pizza_index[2]);
                        } else {

                            index = 0;

                            if (ingredient3.getGood_matches().size() != 0) {

                                do {
                                    if (!ingredient3.getGood_matches().get(index).getName().equals(ingredient3_name) &&
                                            !ingredient3.getGood_matches().get(index).getName().equals(ingredient2_name) &&
                                            !ingredient3.getGood_matches().get(index).getName().equals(ingredient1_name)) {
                                        ingredient4 = ingredient3.getGood_matches().get(index);
                                        break;
                                    }

                                    index++;
                                } while (index < ingredient3.getGood_matches().size());

                            }

                            if (index == ingredient3.getGood_matches().size()) {
                                do {
                                    r4 = new Random();
                                    random_number4 = r4.nextInt(filtered_ingredients.size());
                                    ingredient4 = filtered_ingredients.get(random_number4);
                                } while (ingredient4.getName().equals(ingredient3_name)
                                        || ingredient4.getName().equals(ingredient2_name)
                                        || ingredient4.getName().equals(ingredient1_name));
                            }
                        }

                        ingredient4_name = ingredient4.getName();
                    }

                    if (good_matches_3.size() != 0 && ingredient3.getGood_matches().size() != good_matches_3.size()) {
                        ingredient3.getGood_matches().clear();
                        ingredient3.getGood_matches().addAll(good_matches_3);
                    }
                    if (bad_matches_3.size() != 0 && ingredient3.getBad_matches().size() != bad_matches_3.size()) {
                        ingredient3.getBad_matches().clear();
                        ingredient3.getBad_matches().addAll(bad_matches_3);
                    }
                }

                if (good_matches_2.size() != 0 && ingredient2.getGood_matches().size() != good_matches_2.size()) {
                    ingredient2.getGood_matches().clear();
                    ingredient2.getGood_matches().addAll(good_matches_2);
                }
                if (bad_matches_2.size() != 0 && ingredient2.getBad_matches().size() != bad_matches_2.size()) {
                    ingredient2.getBad_matches().clear();
                    ingredient2.getBad_matches().addAll(bad_matches_2);
                }
            }

            if (good_matches_1.size() != 0 && ingredient1.getGood_matches().size() != good_matches_1.size()) {
                ingredient1.getGood_matches().clear();
                ingredient1.getGood_matches().addAll(good_matches_1);
            }
            if (bad_matches_1.size() != 0 && ingredient1.getBad_matches().size() != bad_matches_1.size()) {
                ingredient1.getBad_matches().clear();
                ingredient1.getBad_matches().addAll(bad_matches_1);
            }

        }


        TextView textView1 = findViewById(R.id.textView12);
        TextView textView2 = findViewById(R.id.textView13);
        TextView textView3 = findViewById(R.id.textView14);
        TextView textView4 = findViewById(R.id.textView15);

        textView1.setText("");
        textView2.setText("");
        textView3.setText("");
        textView4.setText("");

        RadioGroup radioGroup = findViewById(R.id.radio_group);

        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_4:
                if (filtered_ingredients.size() >= 4)
                    textView4.setText(ingredient4_name);
            case R.id.radio_3:
                if (filtered_ingredients.size() >= 3)
                    textView3.setText(ingredient3_name);
            case R.id.radio_2:
                if (filtered_ingredients.size() >= 2)
                    textView2.setText(ingredient2_name);
            case R.id.radio_1:
                if (filtered_ingredients.size() >= 1)
                    textView1.setText(ingredient1_name);
                break;
        }

    }

    public void findGoodMatch(Ingredient ingredient1) {
        boolean found = false, stop = false;
        Ingredient ingredient2, ingredient3;
        int i2, i3, i4;

        good_matches_1.addAll(ingredient1.getGood_matches());
        bad_matches_1.addAll(ingredient1.getBad_matches());

        createFilteredList(ingredient1.getGood_matches());
        createFilteredList(ingredient1.getBad_matches());

        if (filtered_ingredients.size() >= 2 && ingredient1.getGood_matches().size() != 0) {
            for (i2 = 0; !found && i2 < ingredient1.getGood_matches().size(); i2++) {

                if (ingredient1.getGood_matches().get(i2).getName().equals(ingredient1.getName()))
                    continue;

                ingredient2 = ingredient1.getGood_matches().get(i2);
                good_pizza_index[0] = i2;

                good_matches_2.addAll(ingredient2.getGood_matches());
                bad_matches_2.addAll(ingredient2.getBad_matches());

                createFilteredList(ingredient2.getGood_matches());
                createFilteredList(ingredient2.getBad_matches());

                if (filtered_ingredients.size() >= 3 && ingredient2.getGood_matches().size() != 0) {
                    for (i3 = 0; !found && i3 < ingredient2.getGood_matches().size(); i3++) {

                        if (ingredient2.getGood_matches().get(i3).getName().equals(ingredient2.getName()) ||
                                ingredient2.getGood_matches().get(i3).getName().equals(ingredient1.getName()))
                            continue;

                        ingredient3 = ingredient2.getGood_matches().get(i3);

                        good_matches_3.addAll(ingredient3.getGood_matches());
                        good_matches_3.addAll(ingredient3.getBad_matches());

                        createFilteredList(ingredient3.getGood_matches());
                        createFilteredList(ingredient3.getBad_matches());

                        for (Ingredient in1 : ingredient1.getBad_matches()) {
                            if (in1.getName().equals(ingredient3.getName())) {
                                break;
                            } else {

                                good_pizza_index[1] = i3;

                                for (i4 = 0; !found && i4 < ingredient3.getGood_matches().size(); i4++) {

                                    if (ingredient3.getGood_matches().get(i4).getName().equals(ingredient3.getName()) ||
                                            ingredient3.getGood_matches().get(i4).getName().equals(ingredient2.getName()) ||
                                            ingredient3.getGood_matches().get(i4).getName().equals(ingredient1.getName()))
                                        continue;

                                    for (Ingredient in2 : ingredient2.getBad_matches()) {
                                        if (in2.getName().equals(ingredient3.getGood_matches().get(i4).getName())) {
                                            stop = true;
                                            break;
                                        }
                                    }

                                    if (!stop) {
                                        for (Ingredient in3 : ingredient1.getBad_matches()) {
                                            if (in3.getName().equals(ingredient3.getGood_matches().get(i4).getName())) {
                                                stop = true;
                                                break;
                                            }
                                        }

                                        if (!stop) {
                                            found = true;
                                            good_pizza_index[2] = i4;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public void createFilteredList(List<Ingredient> filt_ingredients) {

        if (filters[0] || filters[1] || filters[2] || filters[3] || filters[4] || filters[5] || filters[6]) {
            for (Iterator<Ingredient> iterator = filt_ingredients.iterator(); iterator.hasNext(); ) {
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
    }


    public void filtersPopUp(View view) {

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout,
                new LinearLayout(getApplicationContext()), false);

        PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        filter_vegetali = popupView.findViewById(R.id.chip_vegetali);
        filter_vegetali.setChecked(filters[0]);
        filter_pesce = popupView.findViewById(R.id.chip_pesce);
        filter_pesce.setChecked(filters[1]);
        filter_formaggi = popupView.findViewById(R.id.chip_formaggi);
        filter_formaggi.setChecked(filters[2]);
        filter_salumi = popupView.findViewById(R.id.chip_salumi);
        filter_salumi.setChecked(filters[3]);
        filter_salse = popupView.findViewById(R.id.chip_salse);
        filter_salse.setChecked(filters[4]);
        filter_frutta = popupView.findViewById(R.id.chip_frutta);
        filter_frutta.setChecked(filters[5]);
        filter_spezie = popupView.findViewById(R.id.chip_spezie);
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
