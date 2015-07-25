package com.store.retail.item;

/**
 * A presentation of item which is bucket for all generic items.
 * @author bsmurali
 */
class GenericItem extends AbstractItem {

    public GenericItem(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public ItemCategory getCategory() {
        return ItemCategory.OTHERS;
    }
}
