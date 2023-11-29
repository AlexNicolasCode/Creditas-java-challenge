import java.util.ArrayList;
import java.util.List;

public class LoanMatcher {
    private Customer customer;

    public LoanMatcher(Customer customer) {
        this.customer = customer;
    }

    public List<Loan> loans() {
        List<Loan> loans = new ArrayList<>();
        Loan loan = new Loan();
        loan.setType("PERSONAL_LOAN");
        loans.add(loan);
        if (customer.age() < 30) {
            loan.setType("GUARANTEE_LOAN");
            loans.add(loan);
        }
        return loans;
    }
}
