public class Customer {
    private final int age;
    private final int income;

    public Customer(int age, int income) {
        this.age = age;
        this.income = income;
    }

    public int income() {
        return this.income;
    }

    public int age() {
        return this.age;
    }
}
