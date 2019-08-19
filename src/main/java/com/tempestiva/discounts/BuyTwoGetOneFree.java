package com.tempestiva.discounts;

public class BuyTwoGetOneFree implements Discount {
    @Override
    public int calculatePrice(final int numOfItems, final int itemPrice) {
        return (numOfItems / 3) * itemPrice * 2 + (numOfItems % 3) * itemPrice;
    }
}
