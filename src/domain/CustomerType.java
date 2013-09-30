package domain;

/**
 * Class CustomerType                *
 * Represent a type that can be assigned to Customer, Queue, and ServiceStation
 *
 * name: short string that uniquely identifies this type
 * description: display name for this type
 * serviceTime: time used to service customer of this type in the appropriate service station
 * totalCustomer: the maximum number of Customers a type can have in a spawn
 */
public class CustomerType {
    private String name;
    private String description;
    private int serviceTime;
    private int arrivalSpread;
    private float dispatchingStake;

    public CustomerType(String name, String description, int f, int arrivalSpread) {
        this.setName(name);
        this.setDescription(description);
        this.setServiceTime(f);
        this.setArrivalSpread(arrivalSpread);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public float getDispatchingStake() {
        return dispatchingStake;
    }

    public void setDispatchingStake(float dispatchingStake) {
        this.dispatchingStake = dispatchingStake;
    }

    public int getArrivalSpread() {
		return arrivalSpread;
	}

	public void setArrivalSpread(int arrivalSpread) {
		this.arrivalSpread = arrivalSpread;
	}
	
	/**
	 * Creates a group of new customers based off parameters.
	 * @param number - The number of customers that will be created
	 * @param arrivalTime - The time that customers will arrive/be created
	 * @return Customer[] - An array of Customers
	 */
	public Customer[] spawn(int number, long arrivalTime) {
        Customer[] customers = new Customer[number];
        for(int i=0;i<number;i++) {
            customers[i] = new Customer(this.getName(), arrivalTime, serviceTime);
        }
  
        return customers;
    }

	/**
	 * Checks CustomerType objects for equality.
	 */
    public boolean equals(Object o) {
        if(o instanceof CustomerType) {
            return (this.getName() == ((CustomerType)o).getName());
        }
        return false;
    }
}
