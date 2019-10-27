package com.company;

import com.company.Factories.AccountFactory;

public class EmployeeAccount extends StaffAccount {

    public EmployeeAccount(String name, int salary, int ID) {
        super(name,salary, ID);
        type = AccountFactory.AccountType.EMPLOYEE;
    }
}
