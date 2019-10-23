package com.company;

public class EmployerAccount extends StaffAccount {

    public EmployerAccount(int salary) {
        super(salary);
    }

    @Override
    public void showInfo() {
        System.out.println(salary);
    }
}
