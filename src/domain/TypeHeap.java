package domain;

/**
 * This class extends the Heap class to allow for only certain types of customers
 * to be held in the Heap.
 * @author Jim Benton
 *
 */
public class TypeHeap extends Heap {

	public TypeHeap(String id, String type) 
	{
		super();
		this.type = type;
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Adds a Customer of a given type to a heap.
	 */
	public void addCustomer(Customer customer)
	{
		if(customer.getType() == type)
		{
			super.addCustomer(customer);
			this.getObserver().queueMessage(id, getVector().size());
		}
		else
		{
			System.out.println("Incorrect customer type");
		}
	}
	
	private String type;
	private String id;
}
