package restaurant.system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RestaurantSystem {

    private static TableManagementSystem tableManagementSystem = new TableManagementSystem();
    private static Menu menu = new Menu();
    private static OrderSystem orderSystem = new OrderSystem(menu);

    public static void main(String[] args) {
        // initial dishes and categories
        Category appetizers = new Category("Appetizers");
        Category mainCourses = new Category("Main Courses");
        Category desserts = new Category("Desserts");

        menu.addCategory(appetizers);
        menu.addCategory(mainCourses);
        menu.addCategory(desserts);

        Dish salad = new Dish("Salad", "Fresh garden salad", 5.99, Arrays.asList("Lettuce", "Tomatoes", "Cucumbers"));
        Dish steak = new Dish("Steak", "Grilled sirloin steak", 15.99, Arrays.asList("Beef", "Seasonings"));
        Dish iceCream = new Dish("Ice Cream", "Creamy vanilla ice cream", 3.99, Arrays.asList("Milk", "Sugar", "Vanilla"));

        appetizers.addDish(salad);
        menu.addDishToCategory(appetizers, salad);
        menu.addDishToCategory(mainCourses, steak);
        menu.addDishToCategory(desserts, iceCream);
        
        

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n==== Restaurant System ====");
            System.out.println("1. View Menu");
            System.out.println("2. Add Dish to Category");
            System.out.println("3. Remove Dish from Category");
            System.out.println("4. Update Dish");
            System.out.println("5. Make Reservation");
            System.out.println("6. View All Staff Members");
            System.out.println("7. Add Staff Member");
            System.out.println("8. Place Order");
            System.out.println("9. Remove Order");
            System.out.println("10. Add Table");
            System.out.println("11. View Tables");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    addDishToCategory(scanner);
                    break;
                case 3:
                    removeDishFromCategory(scanner);
                    break;
                case 4:
                    updateDish(scanner);
                    break;
                case 5:
                    makeReservation(scanner);
                    break;
                case 6:
                    viewStaffMembers();
                    break;
                case 7:
                    addStaffMember(scanner);
                    break;
                case 8:
                    placeOrder(scanner);
                    break;
                case 9:
                    removeOrder(scanner);
                    break;
                case 10:
                    addTable(tableManagementSystem, scanner);
                    break;
                case 11:
                    viewTables();
                    break;
                case 12:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    private static void viewMenu() {
        menu.printMenu();
    }

    private static void addDishToCategory(Scanner scanner) {
        System.out.print("Enter the name of the category to add the dish: ");
        String categoryName = scanner.nextLine();
        Category category = menu.getCategoryByName(categoryName);

        if (category != null) {
            System.out.print("Enter the name of the dish: ");
            String dishName = scanner.nextLine();
            System.out.print("Enter the description of the dish: ");
            String description = scanner.nextLine();
            System.out.print("Enter the price of the dish: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter the ingredients of the dish (comma-separated): ");
            String ingredientsInput = scanner.nextLine();
            List<String> ingredients = Arrays.asList(ingredientsInput.split(","));

            Dish dish = new Dish(dishName, description, price, ingredients);
            category.addDish(dish);
            System.out.println("Dish added successfully to the category: " + categoryName);
        } else {
            System.out.println("Category not found.");
        }
    }

    private static void updateDish(Scanner scanner) {
        System.out.print("Enter the name of the category that contains the dish: ");
        String categoryName = scanner.nextLine();
        Category category = menu.getCategoryByName(categoryName);

        if (category != null) {
            System.out.print("Enter the name of the dish to update: ");
            String dishName = scanner.nextLine();

            Dish dish = category.getDishByName(dishName);
            if (dish != null) {
                System.out.print("Enter the new name of the dish: ");
                String newDishName = scanner.nextLine();
                System.out.print("Enter the new description of the dish: ");
                String newDescription = scanner.nextLine();
                System.out.print("Enter the new price of the dish: ");
                double newPrice = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character

                dish.setName(newDishName);
                dish.setDescription(newDescription);
                dish.setPrice(newPrice);
                System.out.println("Dish updated successfully.");
            } else {
                System.out.println("Dish not found in the category.");
            }
        } else {
            System.out.println("Category not found.");
        }
    }

    private static void removeDishFromCategory(Scanner scanner) {
        System.out.print("Enter the name of the category to remove the dish from: ");
        String categoryName = scanner.nextLine();
        Category category = menu.getCategoryByName(categoryName);

        if (category != null) {
            System.out.print("Enter the name of the dish to remove: ");
            String dishName = scanner.nextLine();

            Dish dish = category.getDishByName(dishName);
            if (dish != null) {
                category.removeDish(dish);
                System.out.println("Dish removed successfully from the category: " + categoryName);
            } else {
                System.out.println("Dish not found in the category.");
            }
        } else {
            System.out.println("Category not found.");
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter the number of guests: ");
        int numberOfGuests = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the reservation date and time (YYYY-MM-DD HH:MM): ");
        String dateTime = scanner.nextLine();

        boolean reservationAdded = tableManagementSystem.makeReservation(numberOfGuests, dateTime);
        if (reservationAdded) {
            System.out.println("Reservation made successfully for " + numberOfGuests + " guests at " + dateTime);
        } else {
            System.out.println("Reservation could not be made. Please try again.");
        }
    }

    private static void viewStaffMembers() {
        System.out.println("\n=== Staff Members ===");
        tableManagementSystem.printAllStaffMembers();
    }

    private static void addStaffMember(Scanner scanner) {
        System.out.print("Enter staff name: ");
        String staffName = scanner.nextLine();
        System.out.print("Enter staff contact information: ");
        String staffContactInformation = scanner.nextLine();
        System.out.print("Enter staff job position (Waiter/Chef/Manager): ");
        String jobPosition = scanner.nextLine();

        Staff newStaffMember;
        if (jobPosition.equalsIgnoreCase("Waiter")) {
            newStaffMember = new Waiter(staffName, staffContactInformation);
        } else if (jobPosition.equalsIgnoreCase("Chef")) {
            newStaffMember = new Chef(staffName, staffContactInformation);
        } else if (jobPosition.equalsIgnoreCase("Manager")) {
            newStaffMember = new Manager(staffName, staffContactInformation);
        } else {
            System.out.println("Invalid job position. Staff member not added.");
            return;
        }

        boolean staffAdded = tableManagementSystem.addStaffMember(newStaffMember);
        if (staffAdded) {
            System.out.println("Staff member added successfully: " + newStaffMember);
        } else {
            System.out.println("Failed to add staff member.");
        }
    }

    private static void placeOrder(Scanner scanner) {
        System.out.print("Enter the table number: ");
        int tableNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Table table = tableManagementSystem.getTableByNumber(tableNumber);
        if (table == null) {
            System.out.println("Table " + tableNumber + " is not available.");
            return;
        }

        System.out.println("Table " + tableNumber + " is available. Let's place an order.");

        List<Dish> menuItems = menu.getAllDishes();
        if (menuItems.isEmpty()) {
            System.out.println("The menu is empty. Please add dishes to the menu first.");
            return;
        }

        List<Dish> orderedItems = new ArrayList<>();
        boolean continueOrdering = true;
        while (continueOrdering) {
            System.out.println("\n=== Menu ===");
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println((i + 1) + ". " + menuItems.get(i));
            }
            System.out.println("0. Finish Ordering");

            System.out.print("Enter the item number to order (0 to finish): ");
            int itemNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (itemNumber == 0) {
                continueOrdering = false;
                break;
            } else if (itemNumber < 1 || itemNumber > menuItems.size()) {
                System.out.println("Invalid item number. Please try again.");
                continue;
            }

            Dish selectedDish = menuItems.get(itemNumber - 1);
            orderedItems.add(selectedDish);
            System.out.println("Ordered: " + selectedDish.getName());
        }

        if (!orderedItems.isEmpty()) {
            Order order = new Order(table, orderedItems);
            boolean orderPlaced = orderSystem.placeOrder(order);
            if (orderPlaced) {
                System.out.println("Order placed successfully for table " + tableNumber);
            } else {
                System.out.println("Failed to place the order.");
            }
        } else {
            System.out.println("No items ordered. Order placement canceled.");
        }
    }

    private static void removeOrder(Scanner scanner) {
        System.out.print("Enter the table number to remove the order: ");
        int tableNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        boolean orderRemoved = orderSystem.removeOrderByTableNumber(tableNumber);
        if (orderRemoved) {
            System.out.println("Order removed successfully for table " + tableNumber);
        } else {
            System.out.println("No order found for table " + tableNumber);
        }
    }

    private static void addTable(Scanner scanner) {
        System.out.print("Enter the table number: ");
        int tableNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the table capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Table newTable = new Table(tableNumber, capacity);
        boolean isAdded = tableManagementSystem.addTable(newTable);
        if (isAdded) {
            System.out.println("Table added successfully: " + newTable);
        } else {
            System.out.println("Failed to add table.");
        }
    }

    private static void viewTables() {
        System.out.println("\n=== Tables ===");
        List<Table> tables = tableManagementSystem.getAllTables();
        if (tables.isEmpty()) {
            System.out.println("No tables found.");
        } else {
            for (Table table : tables) {
                System.out.println("Table number: " + table.getNumber());
            }
        }
    }

    private static void addTable(TableManagementSystem tableManagementSystem, Scanner scanner) {
        System.out.print("Enter the table number: ");
        int tableNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the table capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Table newTable = new Table(tableNumber, capacity);
        boolean isAdded = tableManagementSystem.addTable(newTable);
        if (isAdded) {
            System.out.println("Table added successfully: " + newTable);
        } else {
            System.out.println("Failed to add table.");
        }
    }

    private static void exit() {
        System.out.println("Exiting the restaurant system. Goodbye!");
        System.exit(0);
    }
}
