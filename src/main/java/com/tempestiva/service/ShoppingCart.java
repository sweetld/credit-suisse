package com.tempestiva.service;

import com.tempestiva.Receipt;
import com.tempestiva.products.Product;
import com.tempestiva.repository.StockRepository;
import com.tempestiva.repository.StockRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given a list of shopping, calculate the total cost of those items.
 */
public class ShoppingCart {
    private final StockRepository STOCK_REPOSITORY = new StockRepositoryImpl();
    private List<String> items = new ArrayList<>();

    public void addItems(final List<String> items) {
        if (items == null) {
            throw new IllegalArgumentException("Cannot add null items.");
        }
        this.items.addAll(items);
    }

    public void empty() {
        items.clear();
    }

    public String toString() {
        return "ShoppingCart contains " + items;
    }

    public Receipt pay() {
        if (items == null)
            throw new IllegalArgumentException("Items parameter was null");

        Map<Product, Integer> productGrouping = items.stream()
                                                     .map(String::trim)
                                                     .map(String::toUpperCase)
                                                     .filter(item -> item.length() > 0)
                                                     .peek(item -> {
                                                         if (!STOCK_REPOSITORY.validProduct(item))
                                                             System.out.println("Invalid item " + item + " found, will be ignored!");
                                                     })
                                                     .map(STOCK_REPOSITORY::getProduct)
                                                     .filter(Objects::nonNull)
                                                     .collect(Collectors.groupingBy(Function.identity(),
                                                                                    Collectors.summingInt(x -> 1)));

        final int totalCost = productGrouping.entrySet()
                                             .stream()
                                             .map(entry -> entry.getKey().calculatePrice(entry.getValue()))
                                             .mapToInt(Integer::intValue)
                                             .sum();

        return new Receipt(totalCost);
    }
}
