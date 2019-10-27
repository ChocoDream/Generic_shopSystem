package com.company;

import com.company.Factories.AccountFactory;
import com.company.Menu.CustomerMenu;
import com.company.Menu.EmployeeMenu;
import com.company.Menu.EmployerMenu;
import com.company.Menu.MainMenu;
import com.company.Utilities.FileUtility;
import com.company.Utilities.Generics;
import com.company.Utilities.MiscUtility;

import java.io.Serializable;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static com.company.Utilities.Generics.showElementsInArrayList;
import static com.company.Utilities.Generics.showElementsInArrayListWithIndex;

public class Blacksmith implements Serializable {

    //FILE
    private static final String FILE_DIRECTORY = "src/com/company/Files/";

    //Fields
    private String companyName = null;
    private boolean isRunning = true;
    private boolean isRunningSubMenu = true;
    private static int index;

    private SaleManagement saleManagement = new SaleManagement();
    private ArrayList<StaffAccount> staff = new ArrayList<>();
    private ArrayList<CustomerAccount> customers = new ArrayList<>();

    Blacksmith(){
        //TESTDATA
        saleManagement.addProduct(new Product(
                "Blazing Rod",
                55.49f,
                Product.Type.AXES
        ));
        saleManagement.addProduct(new Product(
                "Stabby",
                29.99f,
                Product.Type.SWORDS
        ));
        saleManagement.addProduct(new Product(
                "Sword",
                19.99f,
                Product.Type.SWORDS
        ));
        saleManagement.addProduct(new Product(
                "Sap",
                69.99f,
                Product.Type.ARMOR
        ));
        saleManagement.addProduct(new Product(
                "Flush",
                39.99f,
                Product.Type.ARMOR
        ));
    }

    public void run(){
        firstTimeStartingProgram();

        do{
            System.out.printf("Welcome to the scorching hot %s\n", companyName);
            switch (View.getInstance().showMenuAndGetChoice(MainMenu.values())) {
                case GOTO_CUSTOMER:
                    index = AccountManagement.askIfCustomerIsNewAndReturnIndexElseGetCustomerIndexByID(customers); //Index of customer. Uses ID to find customer.
                    do {
                        System.out.printf("Welcome %s!\n", customers.get(index));
                        switch (View.getInstance().showMenuAndGetChoice(CustomerMenu.values())) {
                            case GO_TO_STORE:
                                saleManagement.menu(customers.get(index));
                                break;
                            case SHOW_CART:
                                showElementsInArrayList(customers.get(index).getCart());
                                break;
                            case LOG_OUT:
                                returnToMainMenu(); //makes isRunningSubMenu false
                                break;
                        }
                    }while (isRunningSubMenu); //submenu
                    break;
                case GOTO_EMPLOYEE:
                    index = AccountManagement.getStaffIndexByID(staff, AccountFactory.AccountType.EMPLOYEE);
                    if (index == -1){ //When you don't have access
                        continue;
                    }
                    do{
                        switch (View.getInstance().showMenuAndGetChoice(EmployeeMenu.values())){
                            case ADD_PRODUCT:
                                saleManagement.addProduct(ProductManagement.createProduct());
                                break;
                            case SHOW_EMPLOYEES:
                                showElementsInArrayListWithIndex(staff);
                                break;
                            case SHOW_INFO:
                                staff.get(index).showInfo(); //Using an interface method in Account
                                break;
                            case LOG_OUT:
                                returnToMainMenu();
                                break;
                        }
                    }while (isRunningSubMenu); //Submenu
                    break;
                case GOTO_EMPLOYER:
                    index = AccountManagement.getStaffIndexByID(staff, AccountFactory.AccountType.EMPLOYER);
                    if (index == -1){
                        continue;
                    }
                    do{
                        switch (View.getInstance().showMenuAndGetChoice(EmployerMenu.values())){
                            case ADD_PRODUCT:
                                saleManagement.addProduct(ProductManagement.createProduct());
                                break;
                            case HIRE_EMPLOYEE:
                                Generics.addElementToList(staff, AccountManagement.newStaff());
                                break;
                            case SHOW_EMPLOYEES:
                                showElementsInArrayListWithIndex(staff);
                                break;
                            case SHOW_CUSTOMERS:
                                showElementsInArrayListWithIndex(customers);
                                break;
                            case SHOW_INFO:
                                staff.get(index).showInfo();
                                break;
                            case SAVE_FILE:
                                if(FileUtility.fileExists(FILE_DIRECTORY + "staffAccounts.ser")){
                                    FileUtility.saveObject(staff, FILE_DIRECTORY, StandardOpenOption.APPEND);
                                }
                                else {
                                    System.out.println("Creating new files");
                                    FileUtility.saveObject(staff, FILE_DIRECTORY, StandardOpenOption.CREATE);
                                }
                                break;
                            case LOAD_FILE:
                                if(FileUtility.fileExists(FILE_DIRECTORY + "staffAccounts.ser")){
                                    staff = (ArrayList<StaffAccount>)FileUtility.loadObject(FILE_DIRECTORY + "/staffAccounts.ser");
                                }
                                else {
                                    System.out.println("No files found");
                                }
                                break;
                            case LOG_OUT:
                                returnToMainMenu();
                                break;
                        }
                    }while (isRunningSubMenu); //submenu
                    break;
                case EXIT:
                    exitProgram();
                    break;
            }
            isRunningSubMenu = true;
        }while (isRunning); //continues to run until exitProgram is makes isRUnning to false. Main Menu
    }

    private void firstTimeStartingProgram(){
        checkForExistingStaffFile(FILE_DIRECTORY + "staffAccounts.ser");

        checkForExistingCustomerFile(FILE_DIRECTORY + "customerAccounts.ser");

        checkForExistingBlacksmithFile(FILE_DIRECTORY + "BlacksmithData.txt");
    }

    private void checkForExistingBlacksmithFile(String path) {
        if (FileUtility.fileExists(path)) {
            List<String> rows = FileUtility.loadText(path);

            for (String row : rows) {
                String[] parts = row.split(":");
                companyName = parts[1];
            }
        }
        else {
            System.out.printf("No %s found\n", path); //If no blacksmithData exist
            System.out.println("Name of blacksmith");
            companyName = MiscUtility.scanner.nextLine();
        }
    }

    private void checkForExistingStaffFile(String path) {
        if (FileUtility.fileExists(path)){ //If there exist staffAccounts
            staff = (ArrayList<StaffAccount>)FileUtility.loadObject(path);
        }
        else {
            System.out.printf("No %s found!\n", path); //Assumes you're the employer
            Generics.addElementToList(staff, AccountManagement.newEmployer());
        }
    }

    private void checkForExistingCustomerFile(String path) {
        if (FileUtility.fileExists(path)){ //If there exist staffAccounts
            customers = (ArrayList<CustomerAccount>)FileUtility.loadObject(path);
        }
    }

    private void returnToMainMenu() {
        System.out.println("Logging out...");
        isRunningSubMenu = false;
    }

    private void exitProgram() {
        isRunning = false;
    }
}
