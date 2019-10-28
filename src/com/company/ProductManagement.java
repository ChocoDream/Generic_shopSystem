package com.company;

import com.company.Utilities.Generics;
import com.company.Utilities.MiscUtility;

import java.util.ArrayList;

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

    public void removeProduct() {
        if (products.isEmpty()) {
            System.out.println("No products to remove");
            return;
        }

        System.out.println("Product you wishes to remove :");
        String str = MiscUtility.scanner.nextLine();
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
