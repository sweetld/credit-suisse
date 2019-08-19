package com.tempestiva;

import com.tempestiva.service.ShoppingCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CheckoutTest {
    private ShoppingCart shoppingCart;

    @Before
    public void intialise() {
        shoppingCart = new ShoppingCart();
    }

    @Test(expected = IllegalArgumentException.class)
    public final void whenNullThenException() {
        shoppingCart.addItems(null);
        Checkout.pay(shoppingCart);
    }

    @Test
    public final void whenNoItemsThenZero() {
        shoppingCart.addItems(Collections.emptyList());
        Receipt receipt = new Receipt(0);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void whenUnrecognisedItemThenIgnoreItem() {
        List<String> items = new ArrayList<>();
        items.add("Pear");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(0);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void whenEmptyItemThenIgnore() {
        final List<String> items = Arrays.asList("Apple", "");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(35);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void whenBlankItemThenIgnoreItem() {
        final List<String> items = Arrays.asList("Apple", " ");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(35);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void whenWhitespaceInItemThenTrim() {
        final List<String> items = Arrays.asList("Apple ", " Apple");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(70);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void whenMixedCaseThenSumCorrectly() {
        final List<String> items = Arrays.asList("appLe", "aPPLE", "aPpLe");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(105);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void whenRepeatingItemThenSumCost() {
        final List<String> items = Arrays.asList("Apple", "Apple");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(70);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void when1AppleThen35Pence() {
        final List<String> items = Collections.singletonList("Apple");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(35);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void when1BananaThen20Pence() {
        final List<String> items = Collections.singletonList("Banana");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(20);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void when1MelonThen50Pence() {
        final List<String> items = Collections.singletonList("Melon");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(50);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void when2MelonsThen50Pence() {
        final List<String> items = Arrays.asList("Melon", "Melon");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(50);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void when3MelonsThen100Pence() {
        final List<String> items = Arrays.asList("Melon", "Melon", "Melon");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(100);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void when1LimeThen15Pence() {
        final List<String> items = Collections.singletonList("Lime");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(15);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void when2LimesThen30Pence() {
        final List<String> items = Arrays.asList("Lime", "Lime");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(30);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void when3LimesThen30Pence() {
        final List<String> items = Arrays.asList("Lime", "Lime", "Lime");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(30);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }

    @Test
    public final void when4LimesThen45Pence() {
        final List<String> items = Arrays.asList("Lime", "Lime", "Lime", "Lime");
        shoppingCart.addItems(items);
        Receipt receipt = new Receipt(45);
        Assert.assertEquals(receipt, Checkout.pay(shoppingCart));
    }
}
