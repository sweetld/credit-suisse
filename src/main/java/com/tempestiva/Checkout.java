package com.tempestiva;

import com.tempestiva.service.ShoppingCart;

import java.util.Collections;

public class Checkout {

    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItems(Collections.singletonList("Apple"));
        System.out.println(shoppingCart);
        System.out.println(Checkout.pay(shoppingCart));
    }

    public static Receipt pay(final ShoppingCart shoppingCart) {
        return shoppingCart.pay();
    }
}
