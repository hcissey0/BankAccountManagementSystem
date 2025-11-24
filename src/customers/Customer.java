package customers;

public abstract class Customer {
    private final String customerId;
    private String name;
    private int age;
    private String contact;
    private String address;

    private static int customerCounter = 0;

    Customer() {
        this.customerId = generateCustomerId();
    }

    private String generateCustomerId() {
        // "CUS" +
        return "CUS" + String.format("%03d", customerCounter++);
    }

    // getters


    public static int getCustomerCounter() {
        return customerCounter;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public abstract void displayCustomerDetails();
    public abstract String getCustomerType();

}
