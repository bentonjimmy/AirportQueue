package domain;

import java.util.UUID;
/*
 * Class Customer
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
