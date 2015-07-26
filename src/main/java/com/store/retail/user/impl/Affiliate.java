package com.store.retail.user.impl;

import com.store.retail.discount.Discount;
import com.store.retail.user.Customer;
import com.store.retail.user.CustomerType;
import java.time.LocalDateTime;

/**
 * A customer who is an affiliate of the store.
 * @author bsmurali
 */
public class Affiliate extends Customer{

    public Affiliate(String Name, LocalDateTime registeredDateTime, CustomerType type) {
        super(Name, registeredDateTime, type);
        //Set the disount %age
        this.setDiscount(new Discount(10.0)); //10%
    }
    
}
