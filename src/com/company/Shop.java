package com.company;


import com.company.Utilities.Generics;
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

    public void menu(){
        String input;
        do {
            showShopProducts();

            System.out.println("Write name of product to add to cart." +
                    "\nWrite SORT to change Sorting order" +
                    "\nWrite 'return' to head back to menu");
            input = MiscUtility.scanner.nextLine();

        }while (!(input.equalsIgnoreCase("return")));
    }

    public Product getProduct(int index){
        return products.get(0);
    }

    public boolean returnTrueIfProductExist(String str){
        for (Product product : products){
            if (product.getName().equalsIgnoreCase(str)){
                return true;
            }
        }
        return false;
    }

    public int getIndexOfProduct(String str){
        for (int i = 0; i < products.size(); i++){
            if(products.get(i).getName().equalsIgnoreCase(str)){
                return i;
            }
        }
        return 0; //Shouldn't do.
    }

    private void showShopProducts() {
        System.out.println("Showing available wares" +
                "\nCurrently Sorting by:");
        Generics.showElementsInArrayList(products);
        System.out.println();
    }

    public void addProduct(){
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
            System.out.printf("Type of item [%2$s]\n", typeString);
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
