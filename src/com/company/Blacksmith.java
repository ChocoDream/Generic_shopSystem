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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import static com.company.Utilities.Generics.showElementsInArrayList;

public class Blacksmith implements Serializable {

    //FILE
    private static final String FILE_DIRECTORY = "src/com/company/Files";

    //Fields
    private String companyName = null;
    private boolean isRunning = true;
    private boolean isRunningSubMenu = true;

    //TEMPOARY
    private static CustomerAccount customer = new CustomerAccount();
    private static EmployeeAccount employee = new EmployeeAccount(10);
    private static EmployerAccount employer = new EmployerAccount(10);

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
                                shop.menu(customer);
                                break;
                            case SHOW_CART:
                                showElementsInArrayList(customer.getCart());
                                break;
                            case LOG_OUT:
                                returnToMainMenu();
                                break;
                        }
                    }while (isRunningSubMenu);
                    break;
                case GOTO_EMPLOYEE:
                    do{
                        switch (View.getInstance().showMenuAndGetChoice(EmployeeMenu.values())){
                            case ADD_PRODUCT:
                                shop.addNewProduct();
                                break;
                            case SHOW_EMPLOYEES:
                                showElementsInArrayList(staff);
                                break;
                            case SHOW_INFO:
                                employee.showInfo();
                                break;
                            case LOG_OUT:
                                returnToMainMenu();
                                break;
                        }
                    }while (isRunningSubMenu);
                    break;
                case GOTO_EMPLOYER:
                    do{
                        switch (View.getInstance().showMenuAndGetChoice(EmployerMenu.values())){
                            case ADD_PRODUCT:
                                shop.addNewProduct();
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
                                saveFilesCheckForExistingFiles();
                                break;
                            case LOAD_FILE:
                                loadFilesIfExist();
                                break;
                            case LOG_OUT:
                                returnToMainMenu();
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

    private void saveFilesCheckForExistingFiles() {
        if ((Files.exists(Paths.get(FILE_DIRECTORY + "/staffAccounts.ser")))
            && (Files.exists(Paths.get(FILE_DIRECTORY + "/customersAccounts.ser")))){
            FileUtility.saveObject(staff, FILE_DIRECTORY + "/staffAccounts.ser", StandardOpenOption.APPEND);
            FileUtility.saveObject(customers, FILE_DIRECTORY + "/customerAccounts.ser", StandardOpenOption.APPEND);
        }
        else {
            System.out.println("Creating new files");
            FileUtility.saveObject(staff, FILE_DIRECTORY + "/staffAccounts.ser", StandardOpenOption.CREATE);
            FileUtility.saveObject(customers, FILE_DIRECTORY + "/customerAccounts.ser", StandardOpenOption.CREATE);
        }
    }

    private void loadFilesIfExist() {
        if ((Files.exists(Paths.get(FILE_DIRECTORY + "/staffAccounts.ser")))
            && (Files.exists(Paths.get(FILE_DIRECTORY + "/customersAccounts.ser")))){
            staff = (ArrayList<StaffAccount>) FileUtility.loadObject(FILE_DIRECTORY + "/staffAccounts.ser");
            customers = (ArrayList<CustomerAccount>)FileUtility.loadObject(FILE_DIRECTORY + "/customerAccounts.ser");
        }
        else {
            System.out.println("File(s) not found");
        }
    }

    private void returnToMainMenu() {
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

    private void addCustomer(AccountFactory.AccountType accountType){
        Account account = AccountFactory.createAccount(accountType);
        if (account != null){
            customers.add((CustomerAccount)account);
        }
    }

    private void addStaff(AccountFactory.AccountType accountType){
        Account account = AccountFactory.createAccount(accountType);
        if (account != null){
            staff.add((StaffAccount)account);
        }
    }
}
