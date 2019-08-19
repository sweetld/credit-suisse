package com.tempestiva.repository;

import com.tempestiva.products.Product;

import java.util.List;

public interface StockRepository {
    List<String> getProductNames();

    Product getProduct(final String item);

    boolean validProduct(final String item);
}
