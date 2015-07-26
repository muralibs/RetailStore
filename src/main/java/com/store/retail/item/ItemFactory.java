package com.store.retail.item;

/**
 *
 * @author bsmurali
 */
public final class ItemFactory {
    
    //restricting initialization
    private ItemFactory() {}
    
    public static final Item createItem(String name, double price, int quantity, ItemCategory category) {
        if(null == category) {
            throw new CategoryNotProvidedException("Please provide the item category");
        }
        
        if(ItemCategory.GROCERIES.equals(category)) {
            return new Groceries(name, price, quantity);
        } else {
            return new GenericItem(name, price, quantity);
        }
    }
}
