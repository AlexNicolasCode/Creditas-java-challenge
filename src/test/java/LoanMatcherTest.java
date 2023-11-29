import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoanMatcherTest {

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsUnder3000() {
        Customer customer = new Customer("PB", 30, 3000);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsBetween3000And5000() {
        Customer customer = new Customer("PB", 30, 4000);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsMoreThan5000() {
        Customer customer = new Customer("PB", 30, 6000);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsGuaranteeLoanWhenCustomerAgeIsLessThan30() {
        Customer customer = new Customer("PB", 29, 0);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("GUARANTEE_LOAN");
    }

    @Test
    void returnsGuaranteeLoanWhenCustomerLocationIsSP() {
        Customer customer = new Customer("SP", 31, 0);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("GUARANTEE_LOAN");
    }
}
