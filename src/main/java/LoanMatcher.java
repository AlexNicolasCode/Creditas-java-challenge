import java.util.ArrayList;
import java.util.List;

public class LoanMatcher {
    private Customer customer;

    public LoanMatcher(Customer customer) {
        this.customer = customer;
    }

    public List<Loan> loans() {
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan("PERSONAL_LOAN"));
        if (customer.age() < 30 || customer.location() == "SP") {
            loans.add(new Loan("GUARANTEE_LOAN"));
        }
        if (customer.income() >= 5000) {
            loans.add(new Loan("CONSIGNED_LOAN"));
        }
        return loans;
    }
}
