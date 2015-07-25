package com.store.retail;

import com.store.retail.checkout.Order;
import com.store.retail.item.Item;
import com.store.retail.item.ItemCategory;
import com.store.retail.item.ItemFactory;
import com.store.retail.user.Customer;
import com.store.retail.user.CustomerFactory;
import com.store.retail.user.CustomerType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test for retail store
 *
 * @author bsmurali
 */
public class RetailStoreTest {

    public RetailStoreTest() {
    }

    /**
     * Employee's net payable amount
     */
    @Test
    public void netPayableBillAmtByEmployee() {
        //Create customer
        Customer employee = CustomerFactory.registerCustomer("EmployeeCustomer", LocalDateTime.now(), CustomerType.EMPLOYEE);
        //Create order
        Order order = new Order(employee); //Order belongs to this employee
        final Item genericItemABC = ItemFactory.createItem("ABC", 12.0, 2, ItemCategory.OTHERS);
        final Item genericItemXYZ = ItemFactory.createItem("XYZ", 120.0, 1, ItemCategory.OTHERS);
        final Item groceries = ItemFactory.createItem("GRAIN", 990.0, 1, ItemCategory.GROCERIES);
        //add items to order (basket)
        order.addItem(genericItemABC);
        order.addItem(genericItemXYZ);
        order.addItem(groceries);
        //Create the bill
        double finalAmt = order.generateBill();//applies the discount as well
        System.out.println("final Amt =" + finalAmt);
        //test helper
        ExpectedEmployeeNetPayable eenp = new ExpectedEmployeeNetPayable();
        eenp.addItem(genericItemABC);
        eenp.addItem(genericItemXYZ);
        eenp.addItem(groceries);
        double expected = eenp.getExpectedPayableAmt();

        assertEquals(expected, finalAmt, 0.0);
    }

    @Test
    public void netPayableBillAmtByAffiliate() {
        Customer affiliable = CustomerFactory.registerCustomer("AffiliableCustomer", LocalDateTime.now(), CustomerType.AFFILIATE);
        System.out.println(affiliable);

        Order order = new Order(affiliable); //Order belongs to this employee
        final Item genericItemABC = ItemFactory.createItem("ABC", 12.0, 2, ItemCategory.OTHERS);
        final Item genericItemXYZ = ItemFactory.createItem("XYZ", 120.0, 1, ItemCategory.OTHERS);
        final Item groceries = ItemFactory.createItem("GRAIN", 990.0, 1, ItemCategory.GROCERIES);
        //add items to order (basket)
        order.addItem(genericItemABC);
        order.addItem(genericItemXYZ);
        order.addItem(groceries);
        
        //Create the bill
        double finalAmt = order.generateBill();//applies the discount as well
        System.out.println("netPayableBillAmtByAffiliate =" + finalAmt);
        
        /*
         * -------Excepted result calculation----- 
         * 10 % of 12.0 * 2 units = 21.6
         * 10 % of 120.0 = 108.0
         * $5 per $100 for groceries of 990.0 = 945.0
         * total = 21.6 + 108.0 + 945.0 = 1074.6
         */
        assertEquals(1074.6, finalAmt, 0.0);
    }

    @Test
    public void netPayableBillAmtBy2YearOldCustomer() {
        Customer _2YrOldCustomer = CustomerFactory.registerCustomer("OldCustomer", LocalDateTime.now().minusYears(2), CustomerType.OTHER);
        System.out.println(_2YrOldCustomer);

        Order order = new Order(_2YrOldCustomer); //Order belongs to this employee
        final Item genericItemABC = ItemFactory.createItem("ABC", 12.0, 2, ItemCategory.OTHERS);
        final Item genericItemXYZ = ItemFactory.createItem("XYZ", 120.0, 1, ItemCategory.OTHERS);
        final Item groceries = ItemFactory.createItem("GRAIN", 990.0, 1, ItemCategory.GROCERIES);
        //add items to order (basket)
        order.addItem(genericItemABC);
        order.addItem(genericItemXYZ);
        order.addItem(groceries);
        
        //Create the bill
        double finalAmt = order.generateBill();//applies the discount as well
        System.out.println("final Amt =" + finalAmt);
        
        /*
         * 5 % of 12.0 * 2 units = 22.8
         * 5 % of 120.0 = 114.0
         * $5 per $100 for groceries of 990.0 = 945.0
         * total = 22.8 + 114.0 + 945.0 = 1081.8
         */
        assertEquals(1081.8, finalAmt, 0.0);
    }

    /**
     * A customer who is neither an employer nor an affiliate and nor an old
     * customer.
     */
    @Test
    public void netPayableBillAmtByNewCustomer() {
        System.out.println("-------------------------------------");
        Customer newCustomer = CustomerFactory.registerCustomer("NewCustomer", LocalDateTime.now() , CustomerType.OTHER);

        Order order = new Order(newCustomer); //Order belongs to this employee
        final Item genericItemABC = ItemFactory.createItem("ABC", 12.0, 2, ItemCategory.OTHERS);
        final Item genericItemXYZ = ItemFactory.createItem("XYZ", 120.0, 1, ItemCategory.OTHERS);
        final Item groceries = ItemFactory.createItem("GRAIN", 990.0, 1, ItemCategory.GROCERIES);
        //add items to order (basket)
        order.addItem(genericItemABC);
        order.addItem(genericItemXYZ);
        order.addItem(groceries);
        //Create the bill
        double finalAmt = order.generateBill();//applies the discount as well
        System.out.println("final Amt =" + finalAmt);

        /*
         * only $5 per $100 is applicable.
         * total price = 12.0 * 2(quantity) + 120.0 + 990.0 = 1134.0
         * net payable = 1134 - (1134/100) * 5 = 1134 - 55 = 1079.0
         */
        assertEquals(1079.0, finalAmt, 0.0);
        System.out.println("-------------------------------------");
    }

    @Test
    public void netPayableBillAmtForOnlyGroceries() {
        System.out.println("----------------netPayableBillAmtForOnlyGroceries -START---------------------");
        Customer groceriesCustomer = CustomerFactory.registerCustomer("GroceriesCustomer", LocalDateTime.now() , CustomerType.OTHER);

        Order order = new Order(groceriesCustomer); //Order belongs to this employee
        final Item groceries = ItemFactory.createItem("GRAIN", 990.0, 1, ItemCategory.GROCERIES);
        //add items to order (basket)
        order.addItem(groceries);
        //Create the bill
        double finalAmt = order.generateBill();//applies the discount as well
        System.out.println("final Amt =" + finalAmt);

        /*
         * only $5 per $100 is applicable.
         * total price = 990.0 
         * net payable = 990.0 - (990.0/100) * 5 = 990.0 - 45 = 945.0
         */
        assertEquals(945.0, finalAmt, 0.0);
        
        //It should be the same irrespective of customer type
        Customer groceriesEmp = CustomerFactory.registerCustomer("GroceriesEmp", LocalDateTime.now() , CustomerType.EMPLOYEE);

        Order orderEmp = new Order(groceriesEmp); //Order belongs to this employee
        final Item groceriesItemEmp = ItemFactory.createItem("GRAIN", 990.0, 1, ItemCategory.GROCERIES);
        //add items to order (basket)
        orderEmp.addItem(groceries);
        //Create the bill
        double finalAmtEmp = orderEmp.generateBill();//applies the discount as well
        System.out.println("final Amt =" + finalAmtEmp);
        assertEquals(945.0, finalAmtEmp, 0.0);
        System.out.println("----------------netPayableBillAmtForOnlyGroceries -END ---------------------");
    }

    public class ExpectedEmployeeNetPayable {

        private final List<Item> items = new ArrayList();

        public void addItem(Item item) {
            this.items.add(item);
        }

        public double getExpectedPayableAmt() {

            double expectedAmt = 0.0;

            //TODO: Java 8 foreach()...
            //Add all the price * quantity to expectedAmt
            for (Item item : this.items) {
                if (ItemCategory.GROCERIES.equals(item.getCategory())) {
                    expectedAmt += (item.getPrice() - (((int) item.getPrice() / 100) * 5)) * item.getQuantity();
                    System.out.println(" Groceries = " + expectedAmt);
                } else {
                    //30% for employees if item is not groceries
                    expectedAmt += (item.getPrice() - (30.0 / 100 * item.getPrice())) * item.getQuantity();
                    System.out.println(" not groceries = " + expectedAmt);
                }
            }
            System.out.println("Expected = " + expectedAmt);
            return expectedAmt;
        }
    }

}