package com.company;

import com.company.Utilities.Generics;
import com.company.Utilities.MiscUtility;

import java.util.ArrayList;
import java.util.Collections;

public class ProductManagement {
    private ArrayList<Product> products = new ArrayList<>();

    public void createProduct(){
        String input;
        String name;
        float price;
        Product.Type type;

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

            Product product = new Product(name, price, type);
            System.out.printf("Want to add \n%s(yes to confirm, cancel to not create product and return to previous menu)\n", product);
            input = MiscUtility.scanner.nextLine();
            if (input.equalsIgnoreCase("yes")){
                addProduct(product);
                break;
            }
            else {
                System.out.println("Canceling creating current product. Starting over...");
            }
        } while (true);
    }

    public void addProduct(Product product){
        Generics.addElementToList(products, product);
    }

    public void removeMenu(){
        String input;
        do{
            Collections.sort(products);
            showAllProducts(products);

            System.out.printf("Write name of product you want to remove" +
                    "\nWrite SORT to Change Sorting order" +
                    "\nCurrently sorting by '%s'" +
                    "\nWrite 'return' to go back to menu\n", Product.getSortBy().description);
            input = MiscUtility.scanner.nextLine();

            if (input.equals("SORT")){
                changeSortingOrder();
                continue;
            }

            if (input.equalsIgnoreCase("return")){
                break; //stops the loop
            }

            removeProduct(products, input);
        }while (true);
    }

    private void changeSortingOrder(){
        if (Product.getSortBy().ordinal() < (Product.SortBy.values().length) -1){ //As far Index is not at the end of the enum. Preventing out of bounds
            Product.setSortBy(Product.getSortBy().ordinal() + 1); //Moves index by 1
        }
        else {
            Product.setSortBy(0); //Reset index back to the first of the enum. Such a simple yet nice way to make a sort.
        }
    }

    public void removeProduct(ArrayList<Product> products, String str) {
        if (products.isEmpty()) {
            System.out.println("No products to remove");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            if (str.equalsIgnoreCase(products.get(i).getName())) {
                System.out.printf("%s has been removed\n", products.get(i).getName());
                products = Generics.removeElementInList(products, i);
                return;
            }
        }
        System.out.println("Product not found");
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void showAllProducts(ArrayList<Product> products){
        if (products.isEmpty()){
            System.out.println("No available products!");
        }
        else {
            Generics.showElementsInArrayList(products);
        }
        System.out.println();
    }

    public ArrayList<String> prepareForTXTfile(ArrayList<Product> products){
        String inData = "";
        ArrayList<String> outDataArray = new ArrayList<>();
        for (Product product : products){
            inData = String.format("%1$s:%2$.2ff:%3$s", product.getName(), product.getPrice(), product.getType());
            outDataArray.add(inData);
        }

        return outDataArray;
    }
}
