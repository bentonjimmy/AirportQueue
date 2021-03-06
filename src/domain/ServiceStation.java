package domain;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 * Abstract class that represents a Service Station.
 * @author Jim Benton
 *
 */
public abstract class ServiceStation implements Runnable{
    private String id;
	private ArrayList<String> customerTypes;
	private Customer customer;
	private ArrayList<TypeHeap> queues;
	private long sleepTime;
    private boolean open = true;
    private int defaultSleep = 1000;
    private Observer observer;
	
	public ServiceStation()
	{
		this(UUID.randomUUID().toString(), new ArrayList<String>(), new ArrayList<TypeHeap>());
	}

    public ServiceStation(String id, ArrayList<String> customerTypes, ArrayList<TypeHeap> heaps) {
        this.setId(id);
        this.setTypes(customerTypes);
        this.setHeaps(heaps);
        this.setSleepTime(defaultSleep);
    }

    public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public boolean getOpen(){
		return open;
	}
	
	public void setOpen(boolean open){
		this.open = open;
	}

	public ArrayList<String> getTypes() {
		return customerTypes;
	}

	public void setTypes(ArrayList<String> types) {
		this.customerTypes = types;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<TypeHeap> getHeaps() {
		return queues;
	}

	public void setHeaps(ArrayList<TypeHeap> queues) {
		this.queues = queues;
	}

	public float getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
    
    public abstract void getNextCustomer();
    
    
    public void start(){
    	(new Thread(this)).start();
    }
    
    /**
     * This method is used to run the service station and is where
     * the service station will get it's next Customer to service
     */
    public void run() {
        try {
            while(open) {
                getNextCustomer();
                if(customer != null)
                {
                	//adjust sleepTime to specific customer Type
                	this.sleepTime =  customer.serviceWait();
                	observer.serviceMessage(customer.getArrivalTime());
                }
                else
                {
                	this.sleepTime = defaultSleep;
                }
                Thread.sleep(this.sleepTime);
                customer = null;
            }
        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
