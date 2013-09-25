package domain;

public class TypeHeap extends Heap {

	public TypeHeap(CustomerType type) 
	{
		super();
		this.type = type;
	}
	
	public void addCustomer(Customer customer)
	{
		if(customer.getType() == type.getName())
		{
			super.addCustomer(customer);
		}
		else
		{
			System.out.println("Incorrect customer type");
		}
	}
	
	private CustomerType type;
}
