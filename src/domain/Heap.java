package domain;

import java.util.Vector;
import java.util.PriorityQueue;

public class Heap 
{
	public Heap()
	{
		size = 0;
		vector = new Vector<Customer>();
		vector.setSize(30);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSize()
	{
		return vector.size();
	}
	
	public void addCustomer()
	{
		
	}
	
	public Customer removeCustomer()
	{
		Customer temp = vector.firstElement(); //Make temp copy of the root
		vector.set(0, vector.lastElement()); //set the root to the last element
		vector.remove(size-1);
		size--;
		if(size > 1)
		{
			bubbleDown();
		}
		
		return temp;
	}
	
	protected void bubbleDown()
	{
		int i = 0;
		Customer child;
		Customer z = vector.firstElement();
		if(vector.get((2*i)+1).getArrivalTime() < vector.get((2*i)+2).getArrivalTime())
		{
			child = vector.get((2*i)+1);
			i = (2*i)+1;
		}
		else
		{
			child = vector.get((2*i)+2);
			i = (2*i)+2;
		}
		while(z.getArrivalTime() > child.getArrivalTime())
		{
			//Swap Customers
			Customer swap = z;
			z = child;
			child = swap;
			
		}
	}
	
	private int size;
	private Vector<Customer> vector;
}
