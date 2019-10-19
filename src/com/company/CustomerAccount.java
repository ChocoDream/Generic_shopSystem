package com.company;

import java.util.ArrayList;

public class CustomerAccount extends Account {

    private ArrayList<Product> cart = new ArrayList<>();

    public CustomerAccount(){
        Product product1 = new Product(
                "Träd",
                39f,
                Product.Type.AXES
        );
        cart.add(product1);
    }

    public void addProduct(Product product){
        cart.add(product);
    }

    public ArrayList<Product> getCart(){
        return cart;
    }

    @Override
    public void showInfo() {

    }
}
