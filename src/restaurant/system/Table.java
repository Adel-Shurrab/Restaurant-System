package restaurant.system;

public class Table {

    private int number;
    private int capacity;
    private boolean available;
    private Reservation reservation;

    public Table(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
        this.available = false;
        this.reservation = null;
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    
    public boolean canAccommodate(int numberOfGuests) {
        return capacity >= numberOfGuests;
    }


    @Override
    public String toString() {
        return "Table " + number + " (Capacity: " + capacity + ")";
    }
}
