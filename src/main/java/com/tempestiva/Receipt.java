package com.tempestiva;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Currency;
import java.util.Locale;

@Getter
@EqualsAndHashCode
public class Receipt {
    private static final String currencySymbol = Currency.getInstance(Locale.getDefault()).getSymbol();
    private final int pounds;
    private final int pence;
    private final int totalInPence;

    public Receipt(int total) {
        if (total < 0)
            throw new IllegalArgumentException("Total cannot be negative");
        totalInPence = total;
        pounds = total / 100;
        pence = total % 100;
    }

    public String toString() {
        return String.format("Cost %s%,d.%02d", currencySymbol, pounds, pence);
    }
}
