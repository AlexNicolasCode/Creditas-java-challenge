import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LoanMatcherTest {
    private Faker faker = new Faker(); 

    private LoanMatcher makeSut(
        String nameSelected,
        String locationSelected,
        String ageSelected,
        String incomeSelected
    ) {
        String name = nameSelected != null ? nameSelected : faker.name().toString();
        int age = ageSelected != null ? Integer.parseInt(ageSelected) : faker.number().randomDigit();
        int income = incomeSelected != null ? Integer.parseInt(incomeSelected) : faker.number().randomDigit();
        int locationIndex = faker.number().numberBetween(0, 2);
        String[] locations = "PB|RN|PE".split("|");
        String location = locationSelected != null ? locationSelected : locations[locationIndex];
        Customer customer = new Customer(name, location, age, income);
        LoanMatcher loanMatcher = new LoanMatcher(customer);
        return loanMatcher;
    }

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsUnder3000() {
        int age = faker.number().numberBetween(30, 100);
        int income = faker.number().numberBetween(0, 3000);
        LoanMatcher loanMatcher = makeSut(null, null, Integer.toString(age), Integer.toString(income));

        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);
        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsBetween3000And5000() {
        int age = faker.number().numberBetween(30, 100);
        int income = faker.number().numberBetween(3001, 5000);
        LoanMatcher loanMatcher = makeSut(null, null, Integer.toString(age), Integer.toString(income));

        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);
        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsPersonalLoanWhenCustomerIncomeIsMoreThan5000() {
        int age = faker.number().numberBetween(30, 100);
        int income = faker.number().numberBetween(5000, 10000);
        LoanMatcher loanMatcher = makeSut(null, null, Integer.toString(age), Integer.toString(income));
        
        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);
        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
    }

    @Test
    void returnsGuaranteeLoanWhenCustomerAgeIsLessThan30() {
        int age = faker.number().numberBetween(0, 29);
        LoanMatcher loanMatcher = makeSut(null, null, Integer.toString(age), null);

        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);
        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(1).type()).isEqualTo("GUARANTEE_LOAN");
    }

    @Test
    void returnsGuaranteeLoanWhenCustomerLocationIsSP() {
        LoanMatcher loanMatcher = makeSut(null, "SP", null, null);

        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);
        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(1).type()).isEqualTo("GUARANTEE_LOAN");
    }

    @Test
    void returnsConsignedLoanWhenCustomerIncomeIsEqualOrMoreThan5000() {
        int age = faker.number().numberBetween(31, 100);
        int income = faker.number().numberBetween(5000, 10000);
        LoanMatcher loanMatcher = makeSut(null, null, Integer.toString(age), Integer.toString(income));

        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);
        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(1).type()).isEqualTo("CONSIGNED_LOAN");
    }

    @Test
    void returnsPersonalLoanWithCorrectTax() {
        int age = faker.number().numberBetween(5000, 10000);
        LoanMatcher loanMatcher = makeSut(null, null, Integer.toString(age), null);

        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(1);
        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(0).type()).isEqualTo("PERSONAL_LOAN");
        assertThat(availableLoans.get(0).tax()).isEqualTo(4);
    }

    @Test
    void returnsGuaranteeLoanWithCorrectTax() {
        int age = faker.number().numberBetween(0, 29);
        LoanMatcher loanMatcher = makeSut(null, null, Integer.toString(age), null);

        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);
        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(1).type()).isEqualTo("GUARANTEE_LOAN");
        assertThat(availableLoans.get(1).tax()).isEqualTo(3);
    }

    @Test
    void returnsConsignedLoanWithCorrectTax() {
        int age = faker.number().numberBetween(31, 100);
        int income = faker.number().numberBetween(5000, 10000);
        LoanMatcher loanMatcher = makeSut(null, null, Integer.toString(age), Integer.toString(income));

        List<Loan> availableLoans = loanMatcher.loans();

        assertThat(availableLoans.size()).isEqualTo(2);
        assertThat(availableLoans.stream().findFirst().isPresent()).isTrue();
        assertThat(availableLoans.get(1).type()).isEqualTo("CONSIGNED_LOAN");
        assertThat(availableLoans.get(1).tax()).isEqualTo(2);
    }
}
