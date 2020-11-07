package com.edfa3ly;

import base.BaseClass;
import org.testng.annotations.Test;


import static org.testng.Assert.*;


public class CartPageTest extends BaseClass
{
    // region Global variables used in test methods
    String nonAutomatedLink = "https://www.adidas.com/us/nite-jogger-shoes/CG5950.html";
    String automatedLink = "https://www.6pm.com/p/product/9409514";
    String ProhibitedLink ="https://www.goat.com/sneakers/shane-sb-premium-light-orewood-brown-cv5500-200";
    String itemName = "Name Test";
    double price = 20.99;
    String color = "Red";
    String size = "12";
    String category = "Shoes";
    int colorIndex = 1;
    int sizeIndex = 1;
    // endregion
    @Test
    public void addNonAutomatedItem()
    {
        cartPage.addNonAutomatedItem(nonAutomatedLink, itemName, price, color, size, category);
        assertTrue(cartPage.getCartDetails());
    }
    @Test
    public void addAutomatedItem()
    {
        cartPage.addAutomatedItem(automatedLink, colorIndex, sizeIndex);
        assertTrue(cartPage.getCartDetails());
    }
    @Test
    public void addProhibitedLink()
    {
        cartPage.addProhibitedItem(ProhibitedLink);
        assertTrue(cartPage.getNotAvailableMessage());
    }
}
