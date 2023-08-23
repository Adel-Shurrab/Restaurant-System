package restaurant.system;

import java.util.List;

class Order {

    private static int nextId = 1;
    private int id;
    private Table table;
    private List<Dish> dishes;

    public Order(Table table, List<Dish> dishes) {
        this.id = nextId++;
        this.table = table;
        this.dishes = dishes;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
