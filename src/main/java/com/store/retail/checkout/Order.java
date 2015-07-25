package com.store.retail.checkout;

import com.store.retail.item.Item;
import com.store.retail.item.ItemCategory;
import com.store.retail.user.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bsmurali
 */
public class Order {
    /**
     * Final order once generated.
     */
    private String confirmationNumber;
    
    private final Customer customer;
    
    private final List<Item> items = new ArrayList();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addItem(Item item) {
        if(null != item) {
            items.add(item);
        }
    }

    public double generateBill() {
        double finalAmt = 0;
        
        //TODO: Java 8 foreach()...
        //Add all the price * quantity to finalAmt
        //if customer has any percentage discount available, apply it
            if(this.customer.getDiscount().getPercent() > 0.0) {
//                for(Item item: this.items) {
//                    finalAmt += (applyDiscount(item) * item.getQuantity());
//                }
                finalAmt = applyDiscount();
            } else {
                //No percentage discount. Give $5 per $100
                finalAmt = applyDefaultDiscount();
            }
        return finalAmt;
    }

    /**
     * Apply the discount as per the rules of item type and customer type.
     * 
     * @param item
     * @return 
     */
    private double applyDiscount() {
        System.out.println("In Apply Discount");
        double afterDiscount = 0.0;
        double discountPercent = 0.0;
        double discountAmt = 0.0;
        
        for(Item item: this.items) {
            //apply item discount
            if(ItemCategory.OTHERS.equals(item.getCategory())){//Not groceries
                //apply customer discount
                discountPercent = this.customer.getDiscount().getPercent();
                //Get the discount amount based on discount percentage.
                discountAmt = (discountPercent == 0) ? item.getPrice() : (discountPercent/100) * item.getPrice();
            } 

            //Either Item Category is groceries or 
            //apply $5 for every $100 
            if(0.0 == discountPercent || ItemCategory.GROCERIES.equals(item.getCategory())){

                double price = item.getPrice();
                discountAmt = ((int)price/100) * 5;
            }

            System.out.println("Discount Amount = " + discountAmt);
            afterDiscount += (item.getPrice() - discountAmt) * item.getQuantity();
        }
        return afterDiscount;
    }

    /**
     * Customer doesn't fall under any of the percentage discount cap. 
     * Apply the $5 per $100 discount.
     * @return 
     */
    private double applyDefaultDiscount() {
        System.out.println("In Apply Default Discount");
        double totalPrice = 0.0;
        for(Item item: this.items) {
            totalPrice += (item.getPrice() * item.getQuantity());
        }
            
        double discountAmt = ((int)totalPrice/100) * 5;
        System.out.println("total price =" + totalPrice);
        System.out.println("Discount Amount = " + discountAmt);
        double finalAmount = totalPrice - discountAmt;
        return finalAmount;
    }
}
