package com.tempestiva.discounts;

public interface Discount {
    default int calculatePrice(int numOfItems, int itemPrice) {
        return itemPrice * numOfItems;
    }
}
