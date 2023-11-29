import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoanMatcherTest {

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsUnder3000() {
        Customer customer = new Customer(3000);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsBetween3000And5000() {
        Customer customer = new Customer(4000);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsMoreThan5000() {
        Customer customer = new Customer(6000);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }
}
