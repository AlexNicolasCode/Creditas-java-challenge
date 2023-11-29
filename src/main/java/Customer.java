public class Customer {
    private final String location;
    private final int age;
    private final int income;

    public Customer(String location, int age, int income) {
        this.location = location;
        this.age = age;
        this.income = income;
    }

    public int income() {
        return this.income;
    }

    public int age() {
        return this.age;
    }

    public String location() {
        return this.location;
    }
}
