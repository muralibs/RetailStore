package com.store.retail.checkout;

import com.store.retail.item.Item;
import com.store.retail.item.ItemCategory;
import com.store.retail.user.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>Customer</code> adds the <code>Item</code> to this Order class. 
 * This holds the customer's items and this becomes the basis for final bill 
 * generation.
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
        if (null != item) {
            items.add(item);
        }
    }

    /**
     * Generates the final amount payable by the customer.
     * This applies the discount on each of the item price.
     * 
     * @return netPayableAmount
     */
    public double generateBill() {
        double netPayableAmount = 0;

        //Add all the price * quantity to finalAmt
        //if customer has any percentage discount available, apply it.
        if (this.customer.getDiscount().getPercent() > 0.0) {
            netPayableAmount = applyDiscount();
        } else {
            //No percentage discount. Give $5 per $100 discount.
            netPayableAmount = applyDefaultDiscount();
        }
        return netPayableAmount;
    }

    /**
     * Apply the discount as per the rules of item type and customer type.
     *
     * @param item
     * @return
     */
    private double applyDiscount() {
        double afterDiscount = 0.0;
        double discountPercent = 0.0;
        double discountAmt = 0.0;

        for (Item item : this.items) {
            //apply item discount
            if (ItemCategory.OTHERS.equals(item.getCategory())) {//Not groceries
                //apply customer percent discount
                discountPercent = this.customer.getDiscount().getPercent();
                //Get the discount amount based on discount percentage.
                discountAmt = (discountPercent == 0) ? item.getPrice() : (discountPercent / 100) * item.getPrice();
                System.out.println("applyDiscount: Discount Amount for Item: " + item.getName() + " is : " + discountAmt);
                afterDiscount += (item.getPrice() - discountAmt) * item.getQuantity();
            } else {
                afterDiscount += item.getPrice() * item.getQuantity();
            }
        }

        //now apply $5 for every $100 
        discountAmt = ((int) afterDiscount / 100) * 5;
        afterDiscount -= discountAmt;
        
        return afterDiscount;
    }

    /**
     * Customer doesn't fall under any of the percentage discount cap. Apply the
     * $5 per $100 discount.
     *
     * @return
     */
    private double applyDefaultDiscount() {
        double totalPrice = 0.0;
        for (Item item : this.items) {
            totalPrice += (item.getPrice() * item.getQuantity());
        }

        double discountAmt = ((int) totalPrice / 100) * 5;
        System.out.println("applyDefaultDiscount: Total price =" + totalPrice);
        System.out.println("applyDefaultDiscount: Total discount Amount = " + discountAmt);
        double finalAmount = totalPrice - discountAmt;
        return finalAmount;
    }
}
