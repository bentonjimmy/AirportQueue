package domain;

import java.util.Vector;
import java.util.PriorityQueue;

public class Heap 
{
	public Heap()
	{
		vector = new Vector<Customer>();
		//vector.setSize(30);
	}
	
	/**
	 * Returns the number of customers currently in the Heap
	 *
	 * @return int
	 */
	public int getSize()
	{
		return vector.size();
	}
	
	public Vector<Customer> getVector()
	{
		return vector;
	}
	
	public void setVector(Vector<Customer> vector)
	{
		this.vector = vector;
	}
	
	public void addCustomer()
	{
		
	}
	
	public Customer removeCustomer()
	{
		Customer temp = vector.firstElement(); //Make temp copy of the root
		vector.set(0, vector.lastElement()); //set the root to the last element
		vector.remove(vector.size()-1);
		if(vector.size() > 1) //must be at least one node to test root against
		{
			bubbleDown();
		}
		
		return temp;
	}
	
	protected void bubbleDown()
	{
		int i = 0;
		Customer child = null;
		Customer z = vector.firstElement();
		
		if((2*i)+2 < vector.size()) //at least two nodes to check
		{
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
		}
		else //only a left node to check
		{
			child = vector.get((2*i)+1);
			i = (2*i)+1;
		}
		
		while((vector.firstElement().getArrivalTime() > child.getArrivalTime()) &&
				vector.size() >= (2*i)+1)//this needs to change
		{
			//Swap Customers
			Customer swap = vector.firstElement();
			vector.set(0, child);//the root
			vector.set(i, swap);
			
			if((2*i)+1 == vector.size()) //only a right node
			{
				child = vector.get((2*i)+1);
				i = (2*i)+1;
			}
			else //at least two nodes to check
			{
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
			}
			
		}
	}
	
	private Vector<Customer> vector;
}
