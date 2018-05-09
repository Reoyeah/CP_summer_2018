package pl.waw.sgh.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    List<Customer> custList = new ArrayList<>();

    List<Account> accList = new ArrayList<>();

    private Integer lastCustID = 0;

    private Integer lastAccID = 0;

    public Customer createCustomer(String firstName,
                                   String lastName,
                                   String email) {
        Customer customer = new Customer(lastCustID++,firstName, lastName, email);
        custList.add(customer);
        return customer;
    }

    public Account createAccount(Customer customer, boolean isSavings) {
        Account account = (isSavings ?
                            new SavingsAccount(lastAccID++, new BigDecimal(0), customer)
                            :
                            new DebitAccount(lastAccID++, new BigDecimal(0), customer)
        );
        accList.add(account);
        return account;
    }

    public void transfer(Integer fromAccId, Integer toAccId, double amount) {
        Account fromAcc = findAccountById(fromAccId);
        Account toAcc = findAccountById(toAccId);
        fromAcc.charge(amount);
        toAcc.deposit(amount);
    }

    public Account findAccountById(int id) {
        // iterate through the account list and return the account with a given id
        for (Account acc : accList) {
            //if (id.equals(acc.getAccountID()))
            if (id==acc.getAccountID())
                return acc;
        }

        return null;
    }


    @Override
    public String toString() {
        return "Bank{" +
                "cust=" + custList +
                "\nacc=" + accList +
                '}';
    }
}