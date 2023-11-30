import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoanMatcherTest {

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsUnder3000() {
        Customer customer = new Customer("Tester", "PB", 30, 3000);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsBetween3000And5000() {
        Customer customer = new Customer("Tester", "PB", 30, 4000);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsMoreThan5000() {
        Customer customer = new Customer("Tester", "PB", 30, 5001);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsGuaranteeLoanWhenCustomerAgeIsLessThan30() {
        Customer customer = new Customer("Tester", "PB", 29, 0);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(1).type()).isEqualTo("GUARANTEE_LOAN");
    }

    @Test
    void returnsGuaranteeLoanWhenCustomerLocationIsSP() {
        Customer customer = new Customer("Tester", "SP", 31, 0);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(1).type()).isEqualTo("GUARANTEE_LOAN");
    }

    @Test
    void returnsConsignedLoanWhenCustomerIncomeIsEqualOrMoreThan5000() {
        Customer customer = new Customer("Tester", "PB", 31, 5001);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(1).type()).isEqualTo("CONSIGNED_LOAN");
    }

    @Test
    void returnsPersonalLoanWithCorrectTax() {
        Customer customer = new Customer("Tester", "PB", 31, 2000);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
        assertThat(availableLoans.get(0).tax()).isEqualTo(4);
    }

    @Test
    void returnsGuaranteeLoanWithCorrectTax() {
        Customer customer = new Customer("Tester", "PB", 29, 3000);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(1).type()).isEqualTo("GUARANTEE_LOAN");
        assertThat(availableLoans.get(1).tax()).isEqualTo(3);
    }

    @Test
    void returnsConsignedLoanWithCorrectTax() {
        Customer customer = new Customer("Tester", "PB", 31, 5001);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);

        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(1).type()).isEqualTo("CONSIGNED_LOAN");
        assertThat(availableLoans.get(1).tax()).isEqualTo(2);
    }
}
