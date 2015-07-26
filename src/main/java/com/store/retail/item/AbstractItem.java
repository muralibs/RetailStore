package com.store.retail.item;

import java.util.Objects;

/**
 * Represents the state of each item. Contains the common properties of any item.
 * @author bsmurali
 */
public abstract class AbstractItem implements Item{
    
    private String name;
    private double price;
    private int quantity;

    public AbstractItem(String name, double price, int quantity) {
        if(null == name || name.isEmpty()) {
            throw new EmptyNameException("Name Shouldn't be empty");
        }
        if(price <= 0.0) {
            throw new IncorrectPriceException("Price can't be 0.0 or negative");
        }
        if(quantity <= 0) {
            throw new IncorrectQuantityException("Quantity should be provided");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 73 * hash + this.quantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractItem other = (AbstractItem) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        return true;
    }
    
    
    
}
