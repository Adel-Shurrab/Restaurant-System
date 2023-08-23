package restaurant.system;

import java.time.LocalDateTime;

class Reservation {

    private int numberOfGuests;
    private String dateTimeString;

    public Reservation(int numberOfGuests, String dateTimeString) {
        this.numberOfGuests = numberOfGuests;
        this.dateTimeString = dateTimeString;
    }

    // Getters and setters
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getDateTimeString() {
        return dateTimeString;
    }

    public void setDateTimeString(String dateTimeString) {
        this.dateTimeString = dateTimeString;
    }
}
