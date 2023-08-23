package restaurant.system;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private List<Dish> dishes;

    public Category(String name) {
        this.name = name;
        this.dishes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    public Dish getDishByName(String dishName) {
        for (Dish dish : dishes) {
            if (dish.getName().equals(dishName)) {
                return dish;
            }
        }
        return null;
    }
}
