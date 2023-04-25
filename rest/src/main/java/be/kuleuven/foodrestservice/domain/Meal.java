package be.kuleuven.foodrestservice.domain;
import java.util.UUID;

import java.util.Objects;

public class Meal {

    protected String id;
    protected String name;
    protected Integer kcal;
    protected Double price;
    protected String description;
    protected MealType mealType;

    public Meal() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public Meal( String name, Integer kcal, Double price, String description, MealType mealType) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.kcal = kcal;
        this.price = price;
        this.description = description;
        this.mealType = mealType;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id) &&
                Objects.equals(name, meal.name) &&
                Objects.equals(kcal, meal.kcal) &&
                Objects.equals(price, meal.price) &&
                Objects.equals(description, meal.description) &&
                mealType == meal.mealType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, kcal, price, description, mealType);
    }
}

