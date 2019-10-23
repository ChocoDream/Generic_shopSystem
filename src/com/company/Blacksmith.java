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
        //TESTDATA
        shop.getProduct(new Product(
                "Blazing Rod",
                55.49f,
                Product.Type.AXES
        ));
        shop.getProduct(new Product(
                "Stabby",
                29.99f,
                Product.Type.SWORDS
        ));
        shop.getProduct(new Product(
                "Sword",
                19.99f,
                Product.Type.SWORDS
        ));
        shop.getProduct(new Product(
                "Sap",
                69.99f,
                Product.Type.ARMOR
        ));
        shop.getProduct(new Product(
                "Flush",
                39.99f,
                Product.Type.ARMOR
        ));
    }

    public void run(){
        checkForExistingFiles(); //Check for existing staffAccounts. If found, uses loadFile()

        System.out.println("Name of blacksmith");
        companyName = MiscUtility.scanner.nextLine();

        do{
            System.out.printf("Welcome to the scorching hot %s\n", companyName);
            switch (View.getInstance().showMenuAndGetChoice(MainMenu.values())){
                case GOTO_CUSTOMER:
                    do {
                        System.out.println("Welcome Customer!");
                        switch (View.getInstance().showMenuAndGetChoice(CustomerMenu.values())) {
                            case GO_TO_STORE:
                                shop.menu(customer); //Wishes to find a way to not having to call a customer.
                                break;
                            case SHOW_CART:
                                showElementsInArrayList(customer.getCart());
                                break;
                            case LOG_OUT:
                                returnToMainMenu(); //makes isRunningSubMenu false
                                break;
                        }
                    }while (isRunningSubMenu);
                    break;
                case GOTO_EMPLOYEE:
                    do{
                        switch (View.getInstance().showMenuAndGetChoice(EmployeeMenu.values())){
                            case ADD_PRODUCT:
                                addNewProduct();
                                break;
                            case SHOW_EMPLOYEES:
                                showElementsInArrayList(staff);
                                break;
                            case SHOW_INFO:
                                employee.showInfo(); //Using an interface method in Account
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
                                addNewProduct();
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
                                checkForExistingFiles();
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

    private void checkForExistingFiles() {
        if ((Files.exists(Paths.get(FILE_DIRECTORY + "/staffAccounts.ser")))
            && (Files.exists(Paths.get(FILE_DIRECTORY + "/customersAccounts.ser")))){
            loadFiles();
        }
        else {
            System.out.println("File(s) not found");
        }
    }

    private void loadFiles() {
        staff = (ArrayList<StaffAccount>) FileUtility.loadObject(FILE_DIRECTORY + "/staffAccounts.ser");
        customers = (ArrayList<CustomerAccount>)FileUtility.loadObject(FILE_DIRECTORY + "/customerAccounts.ser");
    }

    private void returnToMainMenu() {
        System.out.println("Logging out...");
        isRunningSubMenu = false;
    }

    private void exitProgram() {
        isRunning = false;
    }

    private void addAccountWithCondition(AccountFactory.AccountType accountType){
        Account account = AccountFactory.createAccount(accountType);
        if (account != null){
            if (accountType == AccountFactory.AccountType.CUSTOMER){ //Checks if accountType is Customer, else staffAccount.
                customers.add((CustomerAccount)account);
            }
            else{
                staff.add((StaffAccount)account);
            }
        }
    }

    public void addNewProduct(){
        String input;
        String name;
        float price;
        Product.Type type;

        Product product = null;

        do {
            System.out.println("Name of product");
            name = MiscUtility.scanner.nextLine();
            System.out.printf("Price of %s(needs a f if using decimals, ex 35.99f..)\n", name);
            price = MiscUtility.returnFloatFromString();

            String typeString = "";
            for (Product.Type _type : Product.Type.values()) {
                typeString += String.format("%s ", _type);
            }
            System.out.printf("Type of item [%s]\n", typeString);

            input = MiscUtility.scanner.nextLine();
            try{
                type = Product.Type.valueOf(input);
            }
            catch (Exception ignore){
                View.getInstance().showErrorMessage(input + " Itemtype does not exist");
                System.out.println("Starting over from step 1");
                continue;
            }

            product = new Product(name, price, type);
            System.out.printf("Want to add %s? (yes to confirm)\n", product);
            input = MiscUtility.scanner.nextLine();
        } while (!input.equalsIgnoreCase("yes"));

        shop.getProduct(product);
    }
}
