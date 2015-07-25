/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.retail.item;

/**
 *
 * @author bsmurali
 */
public interface Item {
    
    /**
     * Returns the name of the item
     * @return 
     */
    public String getName();
    
    /**
     * Returns the price of the item
     * @return 
     */
    public double getPrice();
    
    /**
     * Returns the # of the items
     * @return 
     */
    public int getQuantity();
    /**
     * Returns the item type
     * @return ItemCategory
     */
    public ItemCategory getCategory();
}
