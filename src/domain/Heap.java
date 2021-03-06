package domain;

import java.util.Vector;

/**
 * A heap that will hold Customers.  The order of the heap is based 
 * off of arrival time with the earliest at the top.
 * @author Jim Benton
 *
 */
public class Heap 
{
	public Heap()
	{
		vector = new Vector<Customer>();
	}
	
	/**
	 * Returns the number of customers currently in the Heap
	 *
	 * @return int - number of Customers
	 */
	public int getSize()
	{
		return vector.size();
	}
	
	public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}
	
	public boolean isEmpty()
	{
		return vector.size() == 0;
	}
	
	public Vector<Customer> getVector()
	{
		return vector;
	}
	
	public void setVector(Vector<Customer> vector)
	{
		this.vector = vector;
	}
	
	/**
	 * Adds the given customer to the heap.
	 * @param customer - the Customer to add
	 */
	public synchronized void addCustomer(Customer customer)
	{
		if(customer != null)
		{
			vector.add(customer);
			if(vector.size() > 1)
			{
				bubbleUp();
			}
		}
	}
	
	/**
	 * The method that puts the new added Customer in the correct position of
	 * the heap.
	 */
	protected void bubbleUp()
	{
		Customer parent = null;
		int parentPos;
		int bubblePos = vector.size() - 1;
		Customer bubbleNode = vector.get(bubblePos);
		if(bubblePos % 2 == 1) //odd
		{
			parentPos = (bubblePos - 1) / 2;
		}
		else //even
		{
			parentPos = (bubblePos - 2) / 2;
		}
		parent = vector.get(parentPos);
		while(bubbleNode.getArrivalTime() < parent.getArrivalTime())
		{
			//Swap Customers
			Customer swap = bubbleNode;
			vector.set(bubblePos, parent);//move the parent down
			vector.set(parentPos, swap);
			bubblePos = parentPos; //update position of the bubbled node
			
			if(bubblePos != 0)
			{
				if(bubblePos % 2 == 1) //odd
				{
					parentPos = (bubblePos - 1) / 2;
				}
				else //even
				{
					parentPos = (bubblePos - 2) / 2;
				}
			}
			else
			{
				parent = buCustomer;
			}
			parent = vector.get(parentPos);
			bubbleNode = vector.get(bubblePos);
		}
	}
	
	/**
	 * Removes the Customer at the top of the heap and returns it.
	 * @return Customer at the top of the heap
	 */
	public synchronized Customer removeCustomer()
	{
		if(vector.size() > 0)
		{
			Customer temp = vector.firstElement(); //Make temp copy of the root
			Customer le = vector.lastElement();
				vector.set(0, le); //set the root to the last element
				vector.remove(vector.size()-1);
				if(vector.size() > 1) //must be at least one node to test root against
					{
						bubbleDown();
					}
			return temp;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * The method that fixes the heap after a Customer has been removed
	 */
	protected void bubbleDown()
	{
		int i = 0;
		Customer child = null;
		Customer bubbleNode = vector.firstElement();
		int bubblePos = 0; //the bubble node's position
		
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
		
		do
		{
			//Swap Customers
			Customer swap = bubbleNode;
			vector.set(bubblePos, child);//the node to be bubbled down
			vector.set(i, swap);
			
			bubblePos = i; //update position of the bubbled node
			
			if((2*i)+1 >= vector.size()) 
			{
				child = bdCustomer;
				i = (2*i)+1;
			}
			else //should have at least a left node
			{
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
			}
			
			bubbleNode = vector.get(bubblePos);
			
		}while((bubbleNode.getArrivalTime() > child.getArrivalTime()));
	}
	
	private Vector<Customer> vector;
	private Observer observer;
	private Customer bdCustomer = new Customer("Sentinel", 100000000, 0);
	private Customer buCustomer = new Customer("Sentinel", -1, 0);
}
