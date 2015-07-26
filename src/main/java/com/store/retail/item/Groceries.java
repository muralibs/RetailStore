package com.store.retail.item;

/**
 * An item representing a grocery.
 * @author bsmurali
 */
class Groceries extends AbstractItem{

     Groceries(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public ItemCategory getCategory() {
         return ItemCategory.GROCERIES;
    }
    
    
}
