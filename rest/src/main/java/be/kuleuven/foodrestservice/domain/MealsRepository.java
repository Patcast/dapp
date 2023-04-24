package be.kuleuven.foodrestservice.domain;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class MealsRepository {
    // map: id -> meal
    private static final Map<String, Meal> meals = new HashMap<>();

    @PostConstruct
    public void initData() {

        Meal a = new Meal();
        a.setId("5268203c-de76-4921-a3e3-439db69c462a");
        a.setName("Steak");
        a.setDescription("Steak with fries");
        a.setMealType(MealType.MEAT);
        a.setKcal(1100);
        a.setPrice((14.30));

        meals.put(a.getId(), a);


        Meal b = new Meal();
        b.setId("4237681a-441f-47fc-a747-8e0169bacea1");
        b.setName("Portobello");
        b.setDescription("Portobello Mushroom Burger");
        b.setMealType(MealType.VEGAN);
        b.setKcal(637);
        b.setPrice((10.40));

        meals.put(b.getId(), b);



        Meal c= new Meal();
        c.setId("cfd1601f-2re0-485d-8d21-7607ec0340c8");
        c.setName("Chocolate cake");
        c.setDescription("An explosion of melted chocolate");
        c.setMealType(MealType.VEGGIE);
        c.setKcal(1240);
        c.setPrice(6.12);

        meals.put(c.getId(), c);
    }

    public Optional<Meal> findMeal(String id) {
        Assert.notNull(id, "The meal id must not be null");
        Meal meal = meals.get(id);
        return Optional.ofNullable(meal);
    }

    public Collection<Meal> getAllMeal() {
        return meals.values();
    }

    public Optional<Meal> getCheapestMeal(){
        return meals.values().stream().min(Comparator.comparing(Meal::getPrice));
    }
    public Optional<Meal> getLargestMeal(){
        return meals.values().stream().max(Comparator.comparing(Meal::getKcal));
    }
}
