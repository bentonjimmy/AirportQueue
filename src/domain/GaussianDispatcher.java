package domain;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Based on Gaussion Distribution
 */
public class GaussianDispatcher extends Dispatcher {
    private double a = 10;
    private double b = 0;
    private double c = 0;

    public GaussianDispatcher(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Customer[] dispatch(Long time) {
        //timeNumber time in the specified tick unit
        ArrayList<Customer> customers = new ArrayList<Customer>();
        int number = 0;

        Enumeration keys = this.getCustomerTypes().keys();
        while(keys.hasMoreElements()) {
            String type = keys.nextElement().toString();
            CustomerType customerType = this.getCustomerTypes().get(type);
            //gaussion function
            number = (int)(a*(Math.pow(Math.E, -(Math.pow(time-b, 2)/(2*Math.pow(c, 2))))));
            //spawn customers of this type and add to customer list
            Customer[] customersOfType = customerType.spawn(number, time);
            for(Customer x : customersOfType) {
                customers.add(x);
            }
        }
        return customers.toArray(new Customer[customers.size()]);
    }
}
