package domain;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * This will random number of customers arrived between minCount and maxCount
 * for each customer type
 */
public class SimpleDispatcher extends Dispatcher {
    private int minCount = 0; //Min number of customers of a certain type to create at spawn
    private int maxCount = 4;//Max number of customers of a certain type to create at spawn

    public SimpleDispatcher(int minCount, int maxCount) {
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    public Customer[] dispatch(Long time) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        int rand = 0;
        String type = null;
        CustomerType customerType = null;
        //create number of customers based on the customer type distribution
        Enumeration<String> keys = this.getCustomerTypes().keys();
        while(keys.hasMoreElements()) {
            type = keys.nextElement().toString();
            customerType = this.getCustomerTypes().get(type);
            int toSpawn = (int)((Math.random() * 1000) % customerType.getArrivalSpread());
            if(toSpawn == 0)
            {
	            rand = minCount + (int)(Math.random() * ((maxCount - minCount) + 1));
	            //spawn customers of this type and add to customer list
	            Customer[] customersOfType = customerType.spawn(rand, time);
	            for(Customer x : customersOfType) {
	                customers.add(x);
	            }
            }
        }
        return customers.toArray(new Customer[customers.size()]);
    }
}
