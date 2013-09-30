package domain;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * This represents the dispatching of new Customers to queues.  Between 0 and 4 customers
 * are assigned in a group to a certain queue.  How often a customer is assigned
 * depends upon the value given by the user.
 */
public class SimpleDispatcher extends Dispatcher {
    private int minCount = 0; //Min number of customers of a certain type to create at spawn
    private int maxCount = 4;//Max number of customers of a certain type to create at spawn

    public SimpleDispatcher(int minCount, int maxCount) {
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    /**
     * Dispatches a customer to a new queue
     */
    public Customer[] dispatch(Long time) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        int rand = 0;
        String type = null;
        CustomerType customerType = null;
        //create number of customers based on the customer type distribution
        Enumeration<String> keys = this.getCustomerTypes().keys();
        //Iterate through all Customer Types
        while(keys.hasMoreElements()) {
            type = keys.nextElement().toString();
            customerType = this.getCustomerTypes().get(type);
            /*
             * Using the value given by the user it determines if new customers should
             * be created or not.  It creates a random number from 0 to the number 
             * provided and if it is zero then new customers are created.
             */
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
