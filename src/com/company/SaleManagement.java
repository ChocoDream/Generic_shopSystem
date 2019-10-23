package com.company;


import com.company.Utilities.Generics;
import com.company.Utilities.MiscUtility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class SaleManagement implements Serializable {
    private ArrayList<Product> products = new ArrayList<>();

    SaleManagement(){
    }

    public void menu(CustomerAccount customer){
        String input;
        do {
            Collections.sort(products);
            showAllProducts();

            System.out.printf("Write name of product to add to cart." +
                    "\nWrite SORT to change Sorting order. " +
                    "\nCurrently sorting by '%s'" +
                    "\nWrite 'return' to head back to menu\n", Product.getSortBy().description);
            input = MiscUtility.scanner.nextLine();

            if (input.equals("SORT")){
                changeSortingOrder();
            }

            checkForCustomer(customer, input);

        }while (!(input.equalsIgnoreCase("return")));
    }

    private void checkForCustomer(CustomerAccount customer, String input) {
        for (Product product : products){
            if(input.equalsIgnoreCase(product.getName())){
                customer.addProductToCart(product);
                System.out.println(product.getName() + " has been added to cart\n");
                break;
            }
        }
    }

    private void changeSortingOrder(){
        if (Product.getSortBy().ordinal() < (Product.SortBy.values().length) -1){ //As far Index is not at the end of the enum. Preventing out of bounds
            Product.setSortBy(Product.getSortBy().ordinal() + 1);
        }
        else {
            Product.setSortBy(0); //Reset index back to the first of the enum. Such a simple yet nice way to make a sort.
        }
    }

    public void getProduct(Product product){
        products.add(product);
    }

    private void showAllProducts() {
        System.out.println("Showing available wares");
        Generics.showElementsInArrayList(products);
        System.out.println();
    }
}
