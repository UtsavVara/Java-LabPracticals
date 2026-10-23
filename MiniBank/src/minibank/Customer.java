package minibank;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final String customerId;
    private final String name;
    private final String email;
    private final String mobile;
    private Address address;
    public Customer(String name, String email, String mobile) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required.");
        this.customerId = "CUST" + String.format("%04d", COUNTER.getAndIncrement());
        this.name = name.trim(); this.email = email == null ? "" : email.trim(); this.mobile = mobile == null ? "" : mobile.trim();
    }
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    @Override public Customer clone() {
        try {
            Customer copy = (Customer) super.clone();
            if (address != null) copy.address = new Address(address.getLine(), address.getCity(), address.getPincode());
            return copy;
        } catch (CloneNotSupportedException ex) { throw new AssertionError(ex); }
    }
    @Override public String toString() { return customerId + " | " + name + " | " + email + " | " + mobile; }
    public static class Address implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String line, city, pincode;
        public Address(String line, String city, String pincode) { this.line = line; this.city = city; this.pincode = pincode; }
        public String getLine() { return line; }
        public String getCity() { return city; }
        public String getPincode() { return pincode; }
        @Override public String toString() { return line + ", " + city + " - " + pincode; }
    }
}
