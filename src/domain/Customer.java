package domain;

import java.util.UUID;
/**
 * Represent a customer that being dispatched by the dispatcher,
 * inserted into queues, and serviced by the service stations
 * id: short string that uniquely identifies this customer
 * type: customer type, assigned by dispatcher during dispatching
 * arrivalTime: set by dispatcher during dispatching, used in calculating total time by observer
 */
public class Customer {
    private String id;
    private String type;
    private long arrivalTime;
    private int serviceTime;

    public Customer(String type, long arrivalTime, int serviceTime) {
        this.setId(UUID.randomUUID().toString());
        this.setType(type);
        this.setArrivalTime(arrivalTime);
        this.setServiceTime(serviceTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(CustomerType type) {
        this.type = type.getName();
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	/**
	 * Returns the amount of time that a type of customer must wait to be
	 * services.  The actual time that it takes to service a customer will
	 * vary but at most will be the amount given by the user.
	 * @return int - the amount of time the customer will wait at the service
	 * station
	 */
	public int serviceWait()
	{
		int wait = (int) ((Math.random() * 1000) % serviceTime);
		if(wait < 1)
		{
			wait = 1;
		}
		
		return wait * 1000;
	}

	/**
	 * Checks for equality of two Customer objects.
	 * @return boolean - true if the two Customers are the same
	 */
	public boolean equals(Object other)
    {
        if(this == other)
    		return true;
    	if(other instanceof Customer)
    	{
    		if(this.getId() == ((Customer)other).getId())
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else
    	{
    		return false;
    	}
    }
}
