package com.store.retail.user.impl;

import com.store.retail.discount.Discount;
import com.store.retail.user.Customer;
import com.store.retail.user.CustomerType;
import java.time.LocalDateTime;

/**
 * A customer who is an employee of the store.
 * @author bsmurali
 */
public class Employee extends Customer{

    public Employee() {
        super(CustomerType.EMPLOYEE);
        //Set the disount %age
        this.setDiscount(new Discount(30.0)); //30%
    }

    public Employee(String Name, LocalDateTime registeredDateTime, CustomerType type) {
        super(Name, registeredDateTime, type);
        //Set the disount %age
        this.setDiscount(new Discount(30.0)); //30%
    }
    
    
    
    
    
}
