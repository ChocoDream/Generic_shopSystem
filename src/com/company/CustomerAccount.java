package com.company;

import java.util.ArrayList;

public class CustomerAccount extends Account {

    private ArrayList<Product> cart = new ArrayList<>();

    public CustomerAccount(String name){
        super(name);
        //TEMPOARY DATA
        Product product1 = new Product(
                "Träd",
                39f,
                Product.Type.AXES
        );
        cart.add(product1);
    }

    public void addProductToCart(Product product){
        cart.add(product);
    }

    public ArrayList<Product> getCart(){
        return cart;
    }

    @Override
    public void showInfo() {
        System.out.printf("Showing info for %1$s:\n" +
                "\tname| %1$s\n" +
                "\tID| %2$d\n", name, 30);
    }

    @Override
    public String toString() {
        return null;
    }
}
