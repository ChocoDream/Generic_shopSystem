package com.company;


import com.company.Utilities.Generics;
import com.company.Utilities.MiscUtility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Shop implements Serializable {
    private ArrayList<Product> products = new ArrayList<>();
    private Product product;

    Shop(){
        //CAN ADD TESTDATA HERE
        products.add(new Product("Lance", 49.99f, Product.Type.SWORDS));
        products.add(new Product("Broadsword", 59.99f, Product.Type.SWORDS));
        products.add(new Product("Demon Axe", 29.59f, Product.Type.AXES));
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

            for (Product product : products){
                if(input.equalsIgnoreCase(product.getName())){
                    this.product = product;
                    customer.addProductToCart(addProduct());
                    System.out.println(product.getName() + " has been added to cart\n");
                    break;
                }
            }

        }while (!(input.equalsIgnoreCase("return")));
    }

    private void changeSortingOrder(){
        if (Product.getSortBy().ordinal() < (Product.SortBy.values().length) -1){ //As far Index is not at the end of the enum. Preventing out of bounds
            Product.setSortBy(Product.getSortBy().ordinal() + 1);
        }
        else {
            Product.setSortBy(0); //Reset index back to the first of the enum. Such a simple yet nice way to make a sort.
        }
    }

    public Product addProduct(){
        return product;
    }

    private void showAllProducts() {
        System.out.println("Showing available wares");
        Generics.showElementsInArrayList(products);
        System.out.println();
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

        products.add(product);
    }

    public ArrayList<Product> getProducts(){
        return products;
    }
}
