package domain;

import java.util.ArrayList;
import java.util.Iterator;

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

	@Override
	public synchronized void getNextCustomer() 
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
    		System.out.println("Dequeue Customer ID:" + tempCustomer.getId()+" of type "+ tempCustomer.getType() +
    				", Queue ID:" + chosenQueue.getId());
    	}

	}

}
