package com.store.retail;

import com.store.retail.item.ItemTest;
import com.store.retail.user.CustomerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author bsmurali
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    CustomerTest.class,
    ItemTest.class,
    RetailStoreTest.class
})
public class RetailStoreTestSuite {
    
    public static void main(String[] args) {
    }
    
}
