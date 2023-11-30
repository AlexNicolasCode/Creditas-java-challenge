public class Customer {
    private final String name;
    private final String location;
    private final int age;
    private final int income;

    public Customer(String name, String location, int age, int income) {
        this.name = name;
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

    public String name() {
        return this.name;
    }
}
