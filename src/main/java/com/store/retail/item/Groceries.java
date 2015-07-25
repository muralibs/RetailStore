package com.store.retail.item;

/**
 *
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
