package restaurant.system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class TableManagementSystem {

    private List<Reservation> reservations;
    private List<Staff> staffMembers;
    private List<Table> tables;

    public TableManagementSystem() {
        reservations = new ArrayList<>();
        staffMembers = new ArrayList<>();
        this.tables = new ArrayList<>();
    }

    public boolean makeReservation(int numberOfGuests, String getDateTimeString) {
        // Find an available table for the reservation
        Table availableTable = findAvailableTable(numberOfGuests, getDateTimeString);
        if (availableTable == null) {
            System.out.println("No available table for the requested date and time. or there is no capacity on the available table");
            return false;
        }
        // Create a reservation object
        Reservation reservation = new Reservation(numberOfGuests, getDateTimeString);

        // Assign the table to the reservation
        availableTable.setReservation(reservation);
        availableTable.setAvailable(true);

        return true;
    }

    private Table findAvailableTable(int numberOfGuests, String getDateTimeString) {
        for (Table table : tables) {
            if (!table.isAvailable() && table.getReservation() == null && table.canAccommodate(numberOfGuests)) {
                // Check if the table is available at the requested date and time
                if (isTableAvailableAtDateTime(table, getDateTimeString)) {
                    return table;
                }
            }
        }
        return null;
    }

    private boolean isTableAvailableAtDateTime(Table table, String getDateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(getDateTimeString, formatter);
        LocalDateTime reservationEndDateTime = dateTime.plusHours(2);
        Reservation tableReservation = table.getReservation();
        if (tableReservation == null) {
            return true;
        }
        String tableReservationDateTime = tableReservation.getDateTimeString();
        LocalDateTime dateTime2 = LocalDateTime.parse(tableReservationDateTime, formatter);
        LocalDateTime tableReservationEndDateTime = dateTime2.plusHours(2);
        return (dateTime.isAfter(tableReservationEndDateTime) || reservationEndDateTime.isBefore(tableReservationEndDateTime));
    }

    public List<Staff> getAllStaffMembers() {
        return staffMembers;
    }

    public boolean addTable(Table table) {
        // Check if the table number already exists
        for (Table existingTable : tables) {
            if (existingTable.getNumber() == table.getNumber()) {
                System.out.println("Table number already exists. Cannot add the table.");
                return false;
            }
        }

        // Add the table to the list
        tables.add(table);
        return true;
    }

    public List<Table> getAllTables() {
        return tables;
    }

    public Table getTableByNumber(int tableNumber) {
        for (Table table : tables) {
            if (table.getNumber() == tableNumber) {
                return table;
            }
        }
        return null; // Table not found
    }

    public boolean addStaffMember(Staff staffMember) {
        return staffMembers.add(staffMember);
    }

    public void printAllStaffMembers() {
        if (staffMembers.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            for (Staff staff : staffMembers) {
                System.out.println(staff);
            }
        }
    }
}
