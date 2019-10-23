package com.company.Factories;

import com.company.*;

public class AccountFactory {
    public enum AccountType{
        CUSTOMER,
        EMPLOYEE,
        EMPLOYER,
        TEST,
    }

    public static Account createAccount(AccountType accountType){
        switch (accountType){
            case CUSTOMER:
                return new CustomerAccount();
            case EMPLOYEE:
                return new EmployeeAccount(3);
            case EMPLOYER:
                return new EmployerAccount(2);
            case TEST:
            default:
                View.getInstance().showErrorMessage("Could not find AccountType: " + accountType);
                return null;
        }
    }
}
