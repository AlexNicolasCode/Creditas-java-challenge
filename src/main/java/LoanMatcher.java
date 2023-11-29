import java.util.ArrayList;
import java.util.List;

public class LoanMatcher {
    private Customer customer;

    public LoanMatcher(Customer customer) {
        this.customer = customer;
    }

    public List<Loan> loans() {
        List<Loan> loans = new ArrayList<>();
        if (customer.income() <= 3000) {
            Loan loan = new Loan("PERSONAL_LOAN");
            loans.add(loan);
        }
        return loans;
    }
}
