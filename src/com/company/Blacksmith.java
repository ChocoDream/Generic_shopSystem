package com.company;

import com.company.Factories.AccountFactory;
import com.company.Menu.CustomerMenu;
import com.company.Menu.EmployeeMenu;
import com.company.Menu.EmployerMenu;
import com.company.Menu.MainMenu;
import com.company.Utilities.FileUtility;
import com.company.Utilities.MiscUtility;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import static com.company.Utilities.Generics.showElementsInArrayList;

public class Blacksmith implements Serializable {

    //Fields
    private String companyName = null;
    private boolean isRunning = true;
    private boolean isRunningSubMenu = true;

    //TEMPOARY
    private static CustomerAccount customer = new CustomerAccount();
    private static EmployeeAccount employee = new EmployeeAccount();
    private static EmployerAccount employer = new EmployerAccount();

    private Shop shop = new Shop();
    private ArrayList<StaffAccount> staff = new ArrayList<>();
    private ArrayList<CustomerAccount> customers = new ArrayList<>();

    Blacksmith(){
    }

    public void run(){
        checkForSavedStaffAccountsFile();

        firstTimeStartingProgram();

        do{
            System.out.printf("Welcome to the scorching hot %s\n", companyName);
            switch (View.getInstance().showMenuAndGetChoice(MainMenu.values())){
                case GOTO_CUSTOMER:
                    do {
                        System.out.println("Welcome Customer!");
                        switch (View.getInstance().showMenuAndGetChoice(CustomerMenu.values())) {
                            case GO_TO_STORE:
                                String input;
                                do {
                                    showShopProducts();

                                    System.out.println("Write name of product to add to cart." +
                                            "\nWrite SORT to change Sorting order" +
                                            "\nWrite 'return' to head back to menu");
                                    input = MiscUtility.scanner.nextLine();
                                }while (!(input.equalsIgnoreCase("return")));
                                break;
                            case SHOW_CART:
                                showElementsInArrayList(customer.getCart());
                                break;
                            case LOG_OUT:
                                logOut();
                                break;
                        }
                    }while (isRunningSubMenu);
                    break;
                case GOTO_EMPLOYEE:
                    do{
                        switch (View.getInstance().showMenuAndGetChoice(EmployeeMenu.values())){
                            case ADD_PRODUCT:

                                break;
                            case SHOW_EMPLOYEES:
                                showElementsInArrayList(staff);
                                break;
                            case SHOW_INFO:
                                employee.showInfo();
                                break;
                            case LOG_OUT:
                                logOut();
                                break;
                        }
                    }while (isRunningSubMenu);
                    break;
                case GOTO_EMPLOYER:
                    do{
                        switch (View.getInstance().showMenuAndGetChoice(EmployerMenu.values())){
                            case ADD_PRODUCT:
                                shop.addProduct();
                                break;
                            case HIRE_EMPLOYEE:
                                AccountFactory.AccountType employee = AccountFactory.AccountType.EMPLOYEE;
                                addAccountWithCondition(employee);
                                break;
                            case SHOW_EMPLOYEES:
                                showElementsInArrayList(staff);
                                break;
                            case SHOW_CUSTOMERS:
                                showElementsInArrayList(customers);
                                break;
                            case SHOW_INFO:
                                employer.showInfo();
                                break;
                            case SAVE_FILE:

                                break;
                            case LOAD_FILE:

                                break;
                            case LOG_OUT:
                                logOut();
                                break;
                        }
                    }while (isRunningSubMenu);
                    break;
                case EXIT:
                    exitProgram();
                    break;
            }
            isRunningSubMenu = true;
        }while (isRunning);
    }

    private void showShopProducts() {
        System.out.println("Showing available wares" +
                "\nCurrently Sorting by:");
        showElementsInArrayList(shop.getProducts());
        System.out.println();
    }

    private void logOut() {
        System.out.println("Logging out...");
        isRunningSubMenu = false;
    }

    private void exitProgram() {
        isRunning = false;
    }

    private void checkForSavedStaffAccountsFile() {
        if (new File("src/com/company/Files/staffAccounts.ser").isFile()){
            //Implement reading from file TODO
            staff = (ArrayList<StaffAccount>)(FileUtility.loadObject("src/com/company/Files/staffAccounts.ser"));
        }
    }

    private void firstTimeStartingProgram() {
        if (staff.isEmpty() || companyName.isEmpty()){
            addAccountWithCondition(AccountFactory.AccountType.EMPLOYER);
            System.out.println("Name of blacksmith");
            companyName = MiscUtility.scanner.nextLine();
        }
    }

    private void addAccountWithCondition(AccountFactory.AccountType accountType){
        Account account = AccountFactory.createAccount(accountType);
        if (account != null){
            if (accountType == AccountFactory.AccountType.CUSTOMER){
                customers.add((CustomerAccount)account);
            }
            else{
                staff.add((StaffAccount)account);
            }
        }
    }
}
