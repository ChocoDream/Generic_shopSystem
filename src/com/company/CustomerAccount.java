package com.company;

import java.util.ArrayList;

public class CustomerAccount extends Account {

    private ArrayList<Product> cart = new ArrayList<>();

    public CustomerAccount(String name){
        super(name);
    }

    public void addProductToCart(Product product){
        cart.add(product);
    }

    public ArrayList<Product> getCart(){
        return cart;
    }

    @Override
    public void showInfo() { //Not using but can be used if wanted to.
        System.out.printf("Showing info for %1$s:\n" +
                "\tname| %1$s\n" +
                "\tID| %2$d\n", name, 30);
    }

    @Override
    public String toString() {
        return name;
    }
}
