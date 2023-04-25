package be.kuleuven.foodrestservice.exceptions;

public class MealNotFoundException extends RuntimeException {

    public MealNotFoundException(String id) {
        super("\nCould not find meal with id: " + id+"\n");
    }
}
