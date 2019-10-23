package com.company;

import com.company.Factories.AccountFactory;
import com.company.Menu.CustomerMenu;
import com.company.Menu.EmployeeMenu;
import com.company.Menu.EmployerMenu;
import com.company.Menu.MainMenu;
import com.company.Utilities.FileUtility;
import com.company.Utilities.MiscUtility;

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
    private static CustomerAccount customer = new CustomerAccount("aha");
    private static EmployeeAccount employee = new EmployeeAccount("hej",10);
    private static EmployerAccount employer = new EmployerAccount("dab", 10);

    private SaleManagement saleManagement = new SaleManagement();
    private ArrayList<StaffAccount> staff = new ArrayList<>();
    private ArrayList<CustomerAccount> customers = new ArrayList<>();

    Blacksmith(){
        //TESTDATA
        saleManagement.getProduct(new Product(
                "Blazing Rod",
                55.49f,
                Product.Type.AXES
        ));
        saleManagement.getProduct(new Product(
                "Stabby",
                29.99f,
                Product.Type.SWORDS
        ));
        saleManagement.getProduct(new Product(
                "Sword",
                19.99f,
                Product.Type.SWORDS
        ));
        saleManagement.getProduct(new Product(
                "Sap",
                69.99f,
                Product.Type.ARMOR
        ));
        saleManagement.getProduct(new Product(
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
                                saleManagement.menu(customer); //Wishes to find a way to not having to call a customer.
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
                                addStaff(AccountFactory.AccountType.EMPLOYEE, "Daniel", 55);
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
            && (Files.exists(Paths.get(FILE_DIRECTORY + "/customerAccounts.ser")))){
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

    private void hireStaff(){
        AccountFactory.AccountType accountType;
        String name;
        String input;
        int salary;

        accountType = setAccountType();

        
    }

    private AccountFactory.AccountType setAccountType() {
        String input;
        do {
            System.out.println("Employee or Employer?");
            input = MiscUtility.scanner.nextLine();
            if(input.toUpperCase().equals(AccountFactory.AccountType.EMPLOYEE)){
                return AccountFactory.AccountType.EMPLOYEE;
            }
            else if(input.toUpperCase().equals(AccountFactory.AccountType.EMPLOYER)){
                return AccountFactory.AccountType.EMPLOYER;
            }
            else {
                View.getInstance().showErrorMessage("Invalid accountType");
            }
        }while (true);
    }

    private void addCustomer(AccountFactory.AccountType accountType, String name){
        Account account = AccountFactory.createAccount(accountType, name);
        if (account != null){
            customers.add((CustomerAccount)account);
        }
    }

    private void addStaff(AccountFactory.AccountType accountType, String name, int salary){
        Account account = AccountFactory.createAccount(accountType, name, salary);
        if (account != null){
            staff.add((StaffAccount)account);
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
            do {
                System.out.printf("Type of item [%s]\n", typeString);

                input = MiscUtility.scanner.nextLine();
                try {
                    type = Product.Type.valueOf(input);
                    break;
                } catch (Exception ignore) {
                    View.getInstance().showErrorMessage(input + " Itemtype does not exist");
                }
            }while (true);

            product = new Product(name, price, type);
            System.out.printf("Want to add %s? (yes to confirm)\n", product);
            input = MiscUtility.scanner.nextLine();
        } while (!input.equalsIgnoreCase("yes"));

        saleManagement.getProduct(product);
    }

    private void returnToMainMenu() {
        System.out.println("Logging out...");
        isRunningSubMenu = false;
    }

    private void exitProgram() {
        isRunning = false;
    }
}
