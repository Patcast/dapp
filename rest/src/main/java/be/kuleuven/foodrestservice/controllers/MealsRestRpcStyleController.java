package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.Meal;
import be.kuleuven.foodrestservice.domain.MealType;
import be.kuleuven.foodrestservice.domain.MealsRepository;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import be.kuleuven.foodrestservice.exceptions.NoMealsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@RestController
public class MealsRestRpcStyleController {

    private final MealsRepository mealsRepository;

    @Autowired
    MealsRestRpcStyleController(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    @GetMapping("/restrpc/meals/{id}")
    Meal getMealById(@PathVariable String id) {
        Optional<Meal> meal = mealsRepository.findMeal(id);

        return meal.orElseThrow(() -> new MealNotFoundException(id));
    }

    @GetMapping("/restrpc/meals/cheapest")
    Meal getCheapestMeals() {
        Optional<Meal> meal = mealsRepository.getCheapestMeal();
        return meal.orElseThrow(NoMealsException::new);
    }

    @GetMapping("/restrpc/meals/largest")
    Meal getLargestMeals() {
        Optional<Meal> meal = mealsRepository.getLargestMeal();
        return meal.orElseThrow(NoMealsException::new);
    }
    @GetMapping("/restrpc/meals")
    Collection<Meal> getMeals() {
        return mealsRepository.getAllMeal();
    }

    @DeleteMapping("/restrpc/meals/delete/{id}")
    String deleteMealById(@PathVariable String id) {
        if(mealsRepository.deleteMealById(id)){
            return "Meal Deleted Successfully";
        }else{
            throw new MealNotFoundException(id);
        }
    }
    @PutMapping("/restrpc/meals/edit/{id}")
    Meal replaceEmployee(@RequestBody Meal updatedMeal, @PathVariable String id) {

        return mealsRepository.findMeal(id)
                .map(a -> {
                    a.setName(updatedMeal.getName());
                    a.setDescription(updatedMeal.getDescription());
                    a.setMealType(updatedMeal.getMealType());
                    a.setKcal(updatedMeal.getKcal());
                    a.setPrice(updatedMeal.getPrice());
                    return a;
                })
                .orElseThrow(() -> new MealNotFoundException(id));
    }
    @PostMapping("/restrpc/meals/add")
    Meal newEmployee(@RequestBody Meal newMeal) {
        return mealsRepository.addNewMeal(newMeal);
    }
}
