package com.company;

public class EmployeeAccount extends StaffAccount {

    public EmployeeAccount(int salary) {
        super(salary);
    }

    @Override
    public void showInfo() {
        System.out.println(salary);
    }
}
