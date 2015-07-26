/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.retail.user;

import com.store.retail.discount.Discount;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bsmurali
 */
public class CustomerTest {
    
    public CustomerTest() {
    }

    @Test
    public void employee() {
        Customer employee = CustomerFactory.registerCustomer("EmployeeCustomer", LocalDateTime.now() ,CustomerType.EMPLOYEE);
        assertNotNull(employee);
        employee.setName("Murali");
        assertEquals("Murali", employee.getName());
        assertEquals(new Discount(30.0), employee.getDiscount());
    }
    
    @Test
    public void affiliate() {
        Customer affiliate = CustomerFactory.registerCustomer("AffiliateCustomer", LocalDateTime.now() ,CustomerType.AFFILIATE);
        assertNotNull(affiliate);
        affiliate.setName("affiliate");
        assertEquals("affiliate", affiliate.getName());
        assertEquals(new Discount(10.0), affiliate.getDiscount());
    }
    @Test
    public void otherCustomer() {
        Customer otherCustomer = CustomerFactory.registerCustomer("AffiliateCustomer", LocalDateTime.now() ,CustomerType.OTHER);
        assertNotNull(otherCustomer);
        otherCustomer.setName("affiliate");
        assertEquals("affiliate", otherCustomer.getName());
        assertEquals(new Discount(10.0), otherCustomer.getDiscount());
    }
    
    @Test
    public void validationCheck () {
        try {
            Customer nullNameCustomer = CustomerFactory.registerCustomer(null, LocalDateTime.now() ,CustomerType.OTHER);
        }catch(NameNotProvidedException ne) {
            assert(true);
        }
        
        try {
            Customer emptyNameCustomer = CustomerFactory.registerCustomer("", LocalDateTime.now() ,CustomerType.OTHER);
        }catch(NameNotProvidedException ne) {
            assert(true);
        }
        
        try {
            Customer nullTypeCustomer = CustomerFactory.registerCustomer("SomeCustomer", LocalDateTime.now() ,null);
        }catch(CustomerTypeMissingException ct) {
            assert(true);
        }
        
        
    }
    
}
