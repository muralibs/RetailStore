package com.store.retail.user;

import com.store.retail.user.impl.Affiliate;
import com.store.retail.user.impl.Employee;
import java.time.LocalDateTime;

/**
 * Factory for customer type
 * @author bsmurali
 */
public final class CustomerFactory {
    
    //Restricting instantiation...
    private CustomerFactory() {}
    
    /**
     * Returns the <code>Customer</code> for the requested type.
     * @param type  CustomerType
     * @return Customer
     */
    public static final Customer registerCustomer(String name, LocalDateTime registeredDateTime, CustomerType type) {
        if(CustomerType.EMPLOYEE.equals(type)) {
            return new Employee(name, registeredDateTime, type);
        } else if(CustomerType.AFFILIATE.equals(type)) {
            return new Affiliate(name, registeredDateTime, type);
        } else {
            return new Customer(name, registeredDateTime, type);
        }
    }
}
