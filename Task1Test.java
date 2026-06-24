import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class Task1Test {

@Test
public void shouldIgnoreNullDueDateAndReturnOnlyOverdueAccounts() {

    LoanAccount overdue = new LoanAccount(
            new Date(System.currentTimeMillis() - 86400000),
            1000.0,
            "A1"
    );

    LoanAccount restructured = new LoanAccount(
            null,
            500.0,
            "A2"
    );

    LoanAccount zeroBalance = new LoanAccount(
            new Date(System.currentTimeMillis() - 86400000),
            0.0,
            "A3"
    );

    List<LoanAccount> accounts =
            Arrays.asList(overdue, restructured, zeroBalance);

    List<LoanAccount> result =
            new Task1().getOverdueLoans(accounts);

    assertEquals(1, result.size());
    assertEquals("A1", result.get(0).getAccountId());
}

@Test
public void shouldReturnEmptyListWhenAccountsIsNull() {

    List<LoanAccount> result =
            new Task1().getOverdueLoans(null);

    assertEquals(0, result.size());
}

@Test
public void shouldReturnEmptyListWhenNoAccountsAreOverdue() {

    LoanAccount futureAccount = new LoanAccount(
            new Date(System.currentTimeMillis() + 86400000),
            1000.0,
            "A4"
    );

    List<LoanAccount> result =
            new Task1().getOverdueLoans(
                    Arrays.asList(futureAccount));

    assertEquals(0, result.size());
}


}

