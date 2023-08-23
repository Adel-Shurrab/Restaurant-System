package restaurant.system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class OrderSystem {

    private Menu menu;
    private List<Table> tables;
    private List<Order> orders;

    public OrderSystem(Menu menu) {
        this.menu = menu;
        tables = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public Menu getMenu() {
        return menu;
    }

    public Table getTableByNumber(int tableNumber) {
        for (Table table : tables) {
            if (table.getNumber() == tableNumber) {
                return table;
            }
        }
        return null;
    }

    public boolean removeOrderByTableNumber(int tableNumber) {
        for (Order order : orders) {
            if (order.getTable().getNumber() == tableNumber) {
                orders.remove(order);
                return true;
            }
        }
        return false;
    }

    public boolean placeOrder(Order order) {
        orders.add(order);
        order.getTable().setAvailable(true);
        generateInvoice(order);
        return true;
    }

    private void generateInvoice(Order order) {
        System.out.println("\n=== Invoice ===");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Date: " + LocalDateTime.now());
        System.out.println("Table: " + order.getTable().getNumber());
        System.out.println("Items ordered:");

        List<Dish> orderedItems = order.getDishes();
        double subtotal = 0.0;

        for (Dish dish : orderedItems) {
            System.out.println("- " + dish.getName() + " - $" + dish.getPrice());
            subtotal += dish.getPrice();
        }

        double total = calculateTotalBill(subtotal);
        System.out.println("Total: $" + total);
    }

    private double calculateTotalBill(double subtotal) {
        // Example: Apply a 10% discount for orders over $50
        if (subtotal > 50.0) {
            double discount = subtotal * 0.1;
            subtotal -= discount;
        }

        // Example: Add 1% tax to the subtotal
        double taxAmount = subtotal * 0.01;
        double total = subtotal + taxAmount;

        return total;
    }
}
