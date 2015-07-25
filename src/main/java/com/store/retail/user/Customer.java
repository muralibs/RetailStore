package com.store.retail.user;

import com.store.retail.checkout.Order;
import com.store.retail.discount.Discount;
import java.time.LocalDateTime;

/**
 * Customer represents the end user who will be a user of the
 * application. 
 * @author bsmurali
 */
public class Customer {
    
    private String name;
    private LocalDateTime registeredDateTime;
    private Discount discount;
    private Order order;
    private CustomerType type;

    public Customer() {
        this.registeredDateTime = LocalDateTime.now();
    }

    public Customer(String name, LocalDateTime registeredDateTime, CustomerType type) {
        if(null == name || name.isEmpty()) {
            throw new NameNotProvidedException("Please provide the customer name.");
        }
        if(null == registeredDateTime) { //TODO: Include future check validation
            this.registeredDateTime = LocalDateTime.now();
        } else {
            this.registeredDateTime = registeredDateTime;
        }
        if(null == type) {
            throw new CustomerTypeMissingException("Please provide the customer type");
        }
        this.name = name;
        this.type = type;
    }

    public Customer(CustomerType type) {
        this.type = type;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegisteredDateTime() {
        return registeredDateTime;
    }

    public void setRegisteredDateTime(LocalDateTime registeredDateTime) {
        this.registeredDateTime = registeredDateTime;
    }

    /**
     * If the customer is neither an employee nor an affiliate, check whether he is
     * an old (loyal) customer and give a loyality discount.
     * @return Discount 
     */
    public Discount getDiscount() {
        //if the discount percent is empty
        if(null == discount){
            //Check whether the customer registered before 2 years.
            System.out.println("registered Date = " + this.registeredDateTime);
            System.out.println("Minus check = " + LocalDateTime.now().minusYears(2));
            boolean registeredBefore = this.getRegisteredDateTime().isBefore(LocalDateTime.now().minusYears(2));
            boolean registeredExactly2yrsAgo = this.getRegisteredDateTime().isEqual(LocalDateTime.now().minusYears(2));
            //Check equals or before
            if(registeredBefore || registeredExactly2yrsAgo) {
                this.discount = new Discount(5);//5% discount
                System.out.println("Loyality discount 5% applicable");
            } else {
                this.discount = new Discount(0);//0% discount
                System.out.println("No customer discount applicable");
            }
        }
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Customer{" + "Name=" + name + ", registeredDateTime=" + registeredDateTime + ", discount=" + discount + ", order=" + order + ", type=" + type + '}';
    }

}
