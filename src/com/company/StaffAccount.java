package com.company;

public abstract class StaffAccount extends Account{
    protected int salary;

    StaffAccount(String name, int salary){
        super(name);
        this.salary = salary;
    }

    public void showInfo() {
        System.out.printf("Showing info for %1$s:\n" +
                "\tname : %1$s\n" +
                "\tID : %2$d\n" +
                "\tSalary : %3$d\n", name, 30, salary);
    }
    @Override
    public String toString() {
        return String.format("Name: %1$s | ID: %2$d\n", name, 200);
    }
}
