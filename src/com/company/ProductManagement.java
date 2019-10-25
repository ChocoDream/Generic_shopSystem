package com.company;

import com.company.Utilities.MiscUtility;

public class ProductManagement {

    public static Product createProduct(){
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
                return product;
            }
            else {
                System.out.println("Canceling creating current product. Starting over...");
            }
        } while (true);
    }
}
