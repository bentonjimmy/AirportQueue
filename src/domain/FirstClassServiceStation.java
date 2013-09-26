package domain;

import java.util.ArrayList;
import java.util.Iterator;

public class FirstClassServiceStation extends ServiceStation {

	public FirstClassServiceStation() {
		super();
	}

	public FirstClassServiceStation(String id, ArrayList<TypeHeap> heaps) {
		super(id, null, heaps);
	}
	
	public synchronized void getNextCustomer()
	{
    	TypeHeap chosenQueue = null;
    	Customer tempCustomer = null;

    	Iterator<TypeHeap> heapIter = this.getHeaps().iterator();
    	while(heapIter.hasNext())
    	{
    		TypeHeap th = heapIter.next();
    		if(th.getType().equals("First Class") && !th.isEmpty()) //First class and not empty
    		{
    			tempCustomer = th.removeCustomer();
    		}
    		else if(th.getType().equals("Coach") && !th.isEmpty())
    		{
    			tempCustomer = th.removeCustomer();
    		}
    			
    	}
    	
    	if(tempCustomer != null)
    	{
    		this.setCustomer(tempCustomer);
    		System.out.println("Dequeue Customer ID:" + tempCustomer.getId()+", Queue ID:" + chosenQueue.getId());
    	}
	}
	/*
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
	*/

}
