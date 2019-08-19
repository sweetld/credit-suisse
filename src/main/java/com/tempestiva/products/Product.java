package com.tempestiva.products;

import com.tempestiva.discounts.Discount;

public interface Product {
    default int calculatePrice(int numOfItems) {
        return getDiscount().calculatePrice(numOfItems, getPrice());
    }

    int getPrice();

    String getName();

    Discount getDiscount();
}
