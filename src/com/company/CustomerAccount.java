package com.company;

import com.company.Factories.AccountFactory;
import com.company.Utilities.Generics;

import java.util.ArrayList;

public class CustomerAccount extends Account {

    private ArrayList<Product> cart = new ArrayList<>();

    public CustomerAccount(String name, int ID){
        super(name, ID);
        type = AccountFactory.AccountType.CUSTOMER;
    }

    public void addProductToCart(Product product){
        Generics.addElementToList(cart, product);
    }

    public ArrayList<Product> getCart(){
        return cart;
    }

    @Override
    public void showInfo() { //Not using but can be used if wanted to.
        System.out.printf("Showing info for %1$s\n" +
                "\tAccountType : %3$s\n" +
                "\tname : %1$s\n" +
                "\tID : %2$d\n", name, ID, type);
    }

    @Override
    public String toString() {
        return name;
    }
}
