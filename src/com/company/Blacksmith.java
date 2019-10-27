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
import java.util.ArrayList;
import java.util.List;

public class Blacksmith implements Serializable {

    //FILE
    private static final String FILE_DIRECTORY = "src/com/company/Files/";

    //Fields
    private String companyName = null;
    private boolean isRunning = true;
    private boolean isRunningSubMenu = true;
    private static int index;

    //Classes and Arraylists
    private SaleManagement saleManagement = new SaleManagement();
    private ProductManagement productManagement = new ProductManagement();
    private ArrayList<StaffAccount> staff = new ArrayList<>();
    private ArrayList<CustomerAccount> customers = new ArrayList<>();

    Blacksmith(){
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
                                Generics.showElementsInArrayList(customers.get(index).getCart());
                                break;
                            case LOG_OUT:
                                returnToMainMenu(); //set isRunningSubMenu to false
                                break;
                        }
                    }while (isRunningSubMenu); //submenu
                    Generics.saveFile(customers, FILE_DIRECTORY + "customerAccounts.ser"); //saves customer to customerAccounts.ser
                    break;
                case GOTO_EMPLOYEE:
                    index = AccountManagement.getStaffIndexByID(staff, AccountFactory.AccountType.EMPLOYEE);
                    if (index == -1){ //When you don't have access
                        continue;
                    }
                    do{
                        switch (View.getInstance().showMenuAndGetChoice(EmployeeMenu.values())){
                            case ADD_PRODUCT:

                                break;
                            case SHOW_EMPLOYEES:
                                Generics.showElementsInArrayListWithIndex(staff);
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
                                productManagement.createProduct(); //creates a product
                                saleManagement.setProducts(productManagement.getProducts()); //gets all products from saleManagement to productManagement
                                break;
                            case HIRE_EMPLOYEE:
                                Generics.addElementToList(staff, AccountManagement.newStaff());
                                break;
                            case SHOW_EMPLOYEES:
                                Generics.showElementsInArrayListWithIndex(staff);
                                break;
                            case SHOW_CUSTOMERS:
                                Generics.showElementsInArrayListWithIndex(customers);
                                break;
                            case SHOW_INFO:
                                staff.get(index).showInfo();
                                break;
                            case SAVE_FILE:
                                Generics.saveFile(staff, FILE_DIRECTORY + "staffAccounts.ser");

                                Generics.saveFile(customers, FILE_DIRECTORY + "customerAccounts.ser");
                                break;
                            case LOAD_FILE:
                                staff = Generics.loadFile(staff, FILE_DIRECTORY + "staffAccounts.ser");

                                customers = Generics.loadFile(customers, FILE_DIRECTORY + "customerAccounts.ser");
                                break;
                            case LOG_OUT:
                                returnToMainMenu();
                                break;
                        }
                    }while (isRunningSubMenu); //submenu
                    break;
                case EXIT:
                    exitProgram(); //set isRunning to false
                    break;
            }
            isRunningSubMenu = true;
        }while (isRunning); //continues to run until isRunning to false.
    }

    private void firstTimeStartingProgram(){
        checkForExistingStaffFile(FILE_DIRECTORY + "staffAccounts.ser");

        checkForExistingCustomerFile(FILE_DIRECTORY + "customerAccounts.ser");

        checkForExistingBlacksmithFile(FILE_DIRECTORY + "BlacksmithData.txt");

        checkForExistingProductsFile(FILE_DIRECTORY + "ProductsData.txt");
    }

    private void checkForExistingProductsFile(String path){
        if(FileUtility.fileExists(path)){
            List<String> rows = FileUtility.loadText(path);
            for (String row : rows){
                String[] parts = row.split(":");
                productManagement.addProduct(new Product(parts[0], Float.parseFloat(parts[1]), Product.Type.valueOf(parts[2]))); //adding items from txt file
            }
            saleManagement.setProducts(productManagement.getProducts());
        }
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
        staff = Generics.loadFile(staff, path);

        if (staff.isEmpty()) {
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
