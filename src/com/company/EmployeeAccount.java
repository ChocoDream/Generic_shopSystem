package com.company;

public class EmployeeAccount extends StaffAccount {

    public EmployeeAccount(String name, int salary) {
        super(name,salary);
    }

    @Override
    public void showInfo() {
        System.out.println(salary);
    }
}
