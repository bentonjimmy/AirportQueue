package application;

import domain.*;

import java.util.ArrayList;

/**
 *
 */
public class Application {

	public static void main(String[] args) {
               
        Simulation sim = new Simulation("src/config.xml");
        sim.build();
        sim.start();

        /*SHOPPING CHECKOUT SCENARIO
         * Customer Types:
         *   Express customers (X items or less)
         *   Regular customers (> X items)
         * Number of queues = Number of service stations
         * Regular customers can only be assigned to non-express queues
         * Express customers can be assigned to either express or non-express queues
         * (This may be modified with smarter logic in the dispatcher calculating using the type serviceTime)
        */

	}
	
	
	
}
