package com.tempestiva;

import org.junit.Assert;
import org.junit.Test;

import java.util.Currency;
import java.util.Locale;

public class ReceiptTest {
    private static final String CURRENCY_SYMBOL = Currency.getInstance(Locale.getDefault()).getSymbol();

    @Test(expected = IllegalArgumentException.class)
    public final void whenNullThenException() {
        new Receipt(-1);
    }

    @Test
    public final void whenZeroTotalThenZero() {
        Receipt receipt = new Receipt(0);
        Assert.assertEquals(0, receipt.getTotalInPence());
        Assert.assertEquals(0, receipt.getPence());
        Assert.assertEquals(0, receipt.getPounds());
    }

    @Test
    public final void when100PoundsThenFormatted() {
        Receipt receipt = new Receipt(10000);
        Assert.assertEquals(10000, receipt.getTotalInPence());
        Assert.assertEquals(0, receipt.getPence());
        Assert.assertEquals(100, receipt.getPounds());
        Assert.assertEquals("Cost " + CURRENCY_SYMBOL + "100.00", receipt.toString());
    }

    @Test
    public final void when1000PoundsThenFormatted() {
        Receipt receipt = new Receipt(100000);
        Assert.assertEquals(100000, receipt.getTotalInPence());
        Assert.assertEquals(0, receipt.getPence());
        Assert.assertEquals(1000, receipt.getPounds());
        Assert.assertEquals("Cost " + CURRENCY_SYMBOL + "1,000.00", receipt.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void whenMinTotalThenException() {
        Receipt receipt = new Receipt(-1);
    }

    @Test
    public final void when35PenceThenFormatted() {
        Receipt receipt = new Receipt(35);
        Assert.assertEquals(35, receipt.getTotalInPence());
        Assert.assertEquals(35, receipt.getPence());
        Assert.assertEquals(0, receipt.getPounds());
        Assert.assertEquals("Cost " + CURRENCY_SYMBOL + "0.35", receipt.toString());
    }

    @Test
    public final void when135PenceThenFormatted() {
        Receipt receipt = new Receipt(135);
        Assert.assertEquals(135, receipt.getTotalInPence());
        Assert.assertEquals(35, receipt.getPence());
        Assert.assertEquals(1, receipt.getPounds());
        Assert.assertEquals("Cost " + CURRENCY_SYMBOL + "1.35", receipt.toString());
    }

}
