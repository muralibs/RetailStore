/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.retail.item;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bsmurali
 */
public class ItemTest {
    
    public ItemTest() {
    }
    
    @Test
    public void validation() {
        try {
             ItemFactory.createItem("GRAIN", 990.0, -1, ItemCategory.GROCERIES);
        } catch(IncorrectQuantityException e) {
            assert(true);
        }
        try {
             ItemFactory.createItem("GRAIN", -990.0, 1, ItemCategory.GROCERIES);
        } catch(IncorrectPriceException e) {
            assert(true);
        }
        try {
             ItemFactory.createItem(null, 990.0, 1, ItemCategory.GROCERIES);
        } catch(EmptyNameException e) {
            assert(true);
        }
    }
    
    @Test
    public void createItem() {
        final Item item = ItemFactory.createItem("GRAIN", 990.0, 1, ItemCategory.GROCERIES);
        Assert.assertNotNull(item);
        Item gro = new Groceries("GRAIN", 990.0, 1);
        Assert.assertNotNull(gro);
        Assert.assertEquals(gro, item);
    }
}
