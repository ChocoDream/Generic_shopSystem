package com.company;

import com.company.Factories.AccountFactory;
import com.company.Utilities.MiscUtility;

import java.io.Serializable;

public class AccountManagement implements Serializable {
    //Taking care of staff hiring and new customer process. USed to have this in blacksmith program but decided to move it into a seperate class.
    //The result resulted in a much cleaner Code strucutre. Win win I say.

    public static StaffAccount hireStaff(){
        AccountFactory.AccountType accountType;
        String name;
        int salary;

        System.out.println("Employee or Employer?");
        accountType = setStaffAccountType();
        name = setName();
        salary = setSalary();

        StaffAccount staffAccount = (StaffAccount)AccountFactory.createAccount(accountType, name, salary);
        return staffAccount;
    }

    public static CustomerAccount newCustomer(){
        AccountFactory.AccountType accountType = AccountFactory.AccountType.CUSTOMER;
        String name;

        System.out.println("What's your name, customer?");
        name = MiscUtility.scanner.nextLine();

        CustomerAccount customerAccount = (CustomerAccount)AccountFactory.createAccount(accountType, name);
        return customerAccount;
    }

    private static String setName(){
        System.out.println("Name of person?");
        String name = MiscUtility.scanner.nextLine();
        return name;
    }

    private static int setSalary(){
        System.out.println("Salary of person?");
        int salary = MiscUtility.returnIntegerFromString();
        return salary;
    }

    private static AccountFactory.AccountType setStaffAccountType(){
        String input;
        do {
            input = MiscUtility.scanner.nextLine();
            if(input.toUpperCase().equals(AccountFactory.AccountType.EMPLOYEE.toString())){
                return AccountFactory.AccountType.EMPLOYEE;
            }
            else if(input.toUpperCase().equals(AccountFactory.AccountType.EMPLOYER.toString())){
                return AccountFactory.AccountType.EMPLOYER;
            }
            else {
                View.getInstance().showErrorMessage("Invalid accountType");
            }
        }while (true);
    }
}
