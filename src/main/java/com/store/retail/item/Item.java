package com.store.retail.item;

/**
 * Represents the item available in a store.
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
