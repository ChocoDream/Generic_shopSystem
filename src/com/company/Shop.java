package com.company;


import com.company.Utilities.MiscUtility;

import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Serializable {
    private ArrayList<Product> products = new ArrayList<>();

    Shop(){
        //CAN ADD TESTDATA HERE
        products.add(new Product("Lance", 49.99f, Product.Type.SWORDS));
        products.add(new Product("Broadsword", 59.99f, Product.Type.SWORDS));
        products.add(new Product("Demon Axe", 29.59f, Product.Type.AXES));
    }

    public void addProduct(){
        String input;
        String name;
        float price;
        do {
            System.out.println("Name of product");
            name = MiscUtility.scanner.nextLine();
            System.out.println("Price of " + name);
            price = MiscUtility.returnFloatFromString();

            String typeString = "";
            for (Product.Type type : Product.Type.values()) {
                typeString += String.format("%s ", type);
            }
            System.out.printf("Itemtype of %1$s[%2$s]\n", name, typeString);

            Product tempProduct = new Product(name, price, Product.Type.SWORDS);
            System.out.printf("Want to add %s?\n", tempProduct);
            input = MiscUtility.scanner.nextLine();
        } while (!input.equalsIgnoreCase("yes"));
        Product product = new Product(name, price, Product.Type.SWORDS);
        products.add(product);
    }

    public ArrayList<Product> getProducts(){
        return products;
    }
}
