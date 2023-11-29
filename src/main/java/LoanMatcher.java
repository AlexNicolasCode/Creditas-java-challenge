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
        if (customer.age() < 30 || customer.location() == "SP") {
            loan.setType("GUARANTEE_LOAN");
            loans.add(loan);
        }
        if (customer.income() >= 5000) {
            Loan loan2 = new Loan();
            loan2.setType("CONSIGNED_LOAN");
            loans.add(loan2);
        }
        return loans;
    }
}
