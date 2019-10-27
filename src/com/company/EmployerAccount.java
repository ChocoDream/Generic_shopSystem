package com.company;

import com.company.Factories.AccountFactory;

public class EmployerAccount extends StaffAccount {

    public EmployerAccount(String name, int salary, int ID) {
        super(name, salary, ID);
        type = AccountFactory.AccountType.EMPLOYER;
    }
}
