package be.kuleuven.foodrestservice.exceptions;

public class NoMealsException extends RuntimeException{

    public NoMealsException() {
            super("There is no meals in the list.");
        }
}
