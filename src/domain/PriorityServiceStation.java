package domain;

import java.util.ArrayList;
import java.util.Iterator;

public class PriorityServiceStation extends ServiceStation {

	public PriorityServiceStation() {
		super();
	}

	public PriorityServiceStation(String id, ArrayList<String> customerTypes, ArrayList<Queue> queues) {
		super(id, customerTypes, queues);
	}
	
	public synchronized void getNextCustomer()
	{
		long earliestArrival = -1;
    	Queue chosenQueue = null;
    	Customer tempCustomer = null;

    	ArrayList<String> ssTypes = this.getTypes();
    	Iterator<String> typesIter = ssTypes.iterator();
    	while(typesIter.hasNext() && tempCustomer == null) //Iterate through Types then
    	{
    		String type = typesIter.next();
    		ArrayList<Queue> queues = this.getQueues();
    		Iterator<Queue> queueIter = queues.iterator();
    		while(queueIter.hasNext()) //Iterate through queues
    		{
    			Queue q = queueIter.next();
    			if(checkCustomer(q, type, earliestArrival)) //Get the customer with highest priority and lowest arrival time
    			{
    				tempCustomer = q.topCustomer();
    				chosenQueue = q;
    				earliestArrival = tempCustomer.getArrivalTime();
    			}
    		}
    	}
    	if(tempCustomer != null)
    	{
    		this.setCustomer(chosenQueue.dequeue(tempCustomer));
    		System.out.println("Dequeue Customer ID:" + tempCustomer.getId()+", Queue ID:" + chosenQueue.getId());
    	}
	}
	
	private boolean checkCustomer(Queue q, String type, long earliestArrival)
	{
		if(q.topCustomer() != null)
		{
			return (q.topCustomer().getType().equals(type)) && (earliestArrival == -1 || q.topCustomer().getArrivalTime() < earliestArrival);
		}
		else
		{
			return false;
		}
	}

}
