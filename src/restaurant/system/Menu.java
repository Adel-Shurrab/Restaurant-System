package restaurant.system;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Category> categories;

    public Menu() {
        categories = new ArrayList<>();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addDishToCategory(Category category, Dish dish) {
        category.addDish(dish);
    }

    public void removeDishFromCategory(Category category, Dish dish) {
        category.removeDish(dish);
    }

    public Category getCategoryByName(String categoryName) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;
    }

    public List<Dish> getAllDishes() {
        List<Dish> allDishes = new ArrayList<>();
        for (Category category : categories) {
            allDishes.addAll(category.getDishes());
        }
        return allDishes;
    }

    public void printMenu() {
        System.out.println("=== Menu ===");
        for (Category category : categories) {
            System.out.println("\n" + category.getName() + ":");
            List<Dish> dishes = category.getDishes();
            if (dishes.isEmpty()) {
                System.out.println("No dishes found in this category.");
            } else {
                for (Dish dish : dishes) {
                    System.out.println(dish);
                }
            }
        }
    }
}
