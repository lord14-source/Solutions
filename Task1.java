import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task1 {

    public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {

        List<LoanAccount> result = new ArrayList<>();

        // Handle null input list
        if (accounts == null) {
            return result;
        }

        Date currentDate = new Date();

        for (LoanAccount account : accounts) {

            // Skip null accounts
            if (account == null) {
                continue;
            }

            Date dueDate = account.getDueDate();

            // Add only overdue accounts with positive  balance
            if (dueDate != null
                    && dueDate.before(currentDate)
                    && account.getOutstandingBalance() > 0) {

                result.add(account);
            }
        }

        return result;
    }
}
