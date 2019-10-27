package com.company;

import com.company.Factories.AccountFactory;
import com.company.Utilities.Generics;
import com.company.Utilities.MiscUtility;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountManagement implements Serializable {
    //Taking care of staff hiring and new customer process. USed to have this in blacksmith program but decided to move it into a seperate class.
    //The result resulted in a much cleaner Code strucutre. Win win I say.

    public static StaffAccount hireStaff(){
        AccountFactory.AccountType accountType;
        String name;
        int salary;
        int ID = generateID();

        System.out.println("Employee or Employer?");
        accountType = setStaffAccountType();
        name = setName();
        salary = setSalary();
        System.out.println("Your ID is " + ID);

        StaffAccount staffAccount = (StaffAccount)AccountFactory.createAccount(accountType, name, salary, ID);
        return staffAccount;
    }

    public static StaffAccount hireEmployer(){
        AccountFactory.AccountType accountType = AccountFactory.AccountType.EMPLOYER;
        String name;
        int salary;
        int ID = generateID();

        System.out.println("Adding Employer");
        name = setName();
        salary = setSalary();
        System.out.println("Your ID is " + ID);

        StaffAccount staffAccount = (StaffAccount)AccountFactory.createAccount(accountType, name, salary, ID);
        return staffAccount;
    }

    public static CustomerAccount newCustomer(){
        AccountFactory.AccountType accountType = AccountFactory.AccountType.CUSTOMER;
        String name;
        int ID = generateID();

        name = setName();
        System.out.println("Your ID is " + ID);

        CustomerAccount customerAccount = (CustomerAccount)AccountFactory.createAccount(accountType, name, ID);
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

    public static int askIfCustomerNewAndGetCustomerIndexByID(ArrayList<CustomerAccount> customers) {
        String input; //I'm one of the worst when it comes to making method names

        while (true) {
            System.out.println("Are you a new Customer? (yes/no)");
            input = MiscUtility.scanner.nextLine();
            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
                Generics.addElementToList(customers, AccountManagement.newCustomer());
                return (customers.size() - 1);
            } else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")){
                System.out.println("Enter ID");
                int idInput = MiscUtility.returnIntegerFromString();
                for (int i = 0; i < customers.size(); i++){
                    if (customers.get(i).ID == idInput){
                        System.out.println("Customer found!");
                        return i;
                    }
                }
                System.out.println("Customer not found");
            }
            else{
                System.out.println("Invalid input");
            }
        }
    }

    public static int getStaffIndexByID(ArrayList<StaffAccount> staff){
        int idInput;

        while (true) {
            System.out.println("Enter ID");
            idInput = MiscUtility.returnIntegerFromString();
            for (int i = 0; i < staff.size(); i++){
                if(staff.get(i).ID == idInput){
                    System.out.println("Staff found!");
                    return i;
                }
            }
            System.out.println("Staff not found");
        }
    }

    private static int generateID(){
        return MiscUtility.generateID(4); //Length of ID
    }
}
