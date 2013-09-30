package domain;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Sub class of ServiceStation.  This object represents the service station for 
 * Coach class passengers.  The service time for a passenger is based off of the 
 * class of the passenger.
 * @author Jim Benton
 *
 */
public class CoachServiceStation extends ServiceStation 
{

	public CoachServiceStation() 
	{
		super();
	}
	
	public CoachServiceStation(String id, ArrayList<TypeHeap> heaps) 
	{
		super(id, null, heaps);
	}

	/**
	 * This method will retrieve the next available person from the Coach
	 * queue.  The method checks if the queue contains the type of customer
	 * that is handled by this station and that it is not empty.  If both conditions
	 * are met then the next available person is removed from queue.
	 */
	@Override
	public void getNextCustomer() 
	{
		TypeHeap chosenQueue = null;
    	Customer tempCustomer = null;

    	Iterator<TypeHeap> heapIter = this.getHeaps().iterator();
    	while(heapIter.hasNext())
    	{
    		TypeHeap th = heapIter.next();
    		if(th.getType().equals("Coach") && !th.isEmpty())
    		{
    			tempCustomer = th.removeCustomer();
    			chosenQueue = th;
    		}
    			
    	}
    	
    	if(tempCustomer != null)
    	{
    		this.setCustomer(tempCustomer);
    		//Message printed to screen with information about the dequeue
    		System.out.println("Dequeue Customer ID:" + tempCustomer.getId()+" of type "+ tempCustomer.getType() +
    				", Queue ID:" + chosenQueue.getId() + " Station Type: Coach");
    	}

	}

}
