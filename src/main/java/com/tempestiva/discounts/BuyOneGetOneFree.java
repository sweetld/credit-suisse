package com.tempestiva.discounts;

public class BuyOneGetOneFree implements Discount {
    @Override
    public int calculatePrice(final int numOfItems, final int itemPrice) {
        return (numOfItems / 2) * itemPrice + (numOfItems % 2) * itemPrice;
    }
}
