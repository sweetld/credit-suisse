package com.tempestiva.products;

import com.tempestiva.discounts.Discount;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class ProductImpl implements Product {
    private final String name;
    private final int price;
    private final Discount discount;
}
