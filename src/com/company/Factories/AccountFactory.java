package com.company.Factories;

import com.company.*;

public class AccountFactory {
    public enum AccountType{
        CUSTOMER,
        EMPLOYEE,
        EMPLOYER,
        TEST,
    }

    public static Account createAccount(AccountType accountType, String name, int ID){
        switch (accountType){
            case CUSTOMER:
                return new CustomerAccount(name, ID);
            case TEST:
            default:
                View.getInstance().showErrorMessage("Could not find AccountType: " + accountType);
                return null;
        }
    }
    public static Account createAccount(AccountType accountType, String name, int salary, int ID){
        switch (accountType){
            case EMPLOYEE:
                return new EmployeeAccount(name, salary, ID);
            case EMPLOYER:
                return new EmployerAccount(name, salary, ID);
            case TEST:
            default:
                View.getInstance().showErrorMessage("Could not find AccountType: " + accountType);
                return null;
        }
    }
}
