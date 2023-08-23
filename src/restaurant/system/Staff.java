package restaurant.system;

class Staff {

    private String name;
    private String contactInformation;

    public Staff(String name, String contactInformation) {
        this.name = name;
        this.contactInformation = contactInformation;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "Staff Member - Name: " + name + ", Contact: " + contactInformation;
    }
}
