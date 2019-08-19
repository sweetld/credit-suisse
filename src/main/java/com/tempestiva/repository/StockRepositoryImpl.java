package com.tempestiva.repository;

import com.tempestiva.discounts.BuyOneGetOneFree;
import com.tempestiva.discounts.BuyTwoGetOneFree;
import com.tempestiva.discounts.NoDiscount;
import com.tempestiva.products.Product;
import com.tempestiva.products.ProductImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Items are priced as follows:
 * - Apples are 35p each
 * - Bananas are 20p each
 * - Melons are 50p each, but are available as ?buy one get one free?
 * - Limes are 15p each, but are available in a ?three for the price of two? offer
 */
public class StockRepositoryImpl implements StockRepository {

    private static final Map<String, Product> PRODUCTS = populateProducts();

    private static Map<String, Product> populateProducts() {
        Map<String, Product> productsMap = new HashMap<>();
        Product apple = ProductImpl.builder().name("APPLE").price(35).discount(new NoDiscount()).build();
        Product banana = ProductImpl.builder().name("BANANA").price(20).discount(new NoDiscount()).build();
        Product melon = ProductImpl.builder().name("MELON").price(50).discount(new BuyOneGetOneFree()).build();
        Product lime = ProductImpl.builder().name("LIME").price(15).discount(new BuyTwoGetOneFree()).build();

        productsMap.put(apple.getName(), apple);
        productsMap.put(banana.getName(), banana);
        productsMap.put(melon.getName(), melon);
        productsMap.put(lime.getName(), lime);

        return productsMap;
    }

    private static final List<String> PRODUCT_NAMES = PRODUCTS.values()
                                                              .stream()
                                                              .map(Product::getName)
                                                              .collect(Collectors.toList());

    @Override
    public List<String> getProductNames() {
        return PRODUCT_NAMES;
    }

    @Override
    public Product getProduct(final String item) {
        return PRODUCTS.get(item);
    }

    @Override
    public boolean validProduct(final String item) {
        return PRODUCT_NAMES.contains(item);
    }
}
