package com.company;

public class EmployerAccount extends StaffAccount {

    public EmployerAccount(String name, int salary) {
        super(name, salary);
    }

    @Override
    public void showInfo() {
        System.out.println(salary);
    }
}
