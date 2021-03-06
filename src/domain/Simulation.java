package domain;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.Hashtable;
/**
 *This represents the entire simulation.  It retrieves all the values provided by
 *the user from the Config object and creates all the objects needed to run the 
 *simulation.  
 * @author Jim Benton
 */
public class Simulation {
    
    
    private String configpath;
    private Dispatcher dispatcher;
    private Observer observer;
    private ArrayList<ServiceStation> stations;
    private ArrayList<TypeHeap> heaps;
    private Config config;
    private long sleepTime = 1000;
    
    private ArrayList<Hashtable<String, String>> customerTypesBuilder;
    private ArrayList<String> queueIDs;
    private Hashtable<String, ArrayList<String>> queueTypes;
    private ArrayList<String> ssIDs;
    private Hashtable<String, ArrayList<String>>  ssQueues;
    private Hashtable<String, ArrayList<String>> ssTypes;
    private Hashtable<String, String> settings;
    private Hashtable<String, CustomerType> customerTable;
    
    public Simulation(Config config) {
        
        try {  
            this.config = config;
            
            this.dispatcher = new SimpleDispatcher(0, 1);
            this.stations = new ArrayList<ServiceStation>();
            this.heaps = new ArrayList<TypeHeap>();
            //Holds values pulled from XML value that are used to create CustomerType objects
        	customerTypesBuilder = new ArrayList<Hashtable<String, String>>();
        	//Holds values pulled from XML value that are used to create Queue objects
        	queueIDs = new ArrayList<String>();
        	//Holds the customer types accepted by each queue
        	queueTypes = new Hashtable<String, ArrayList<String>>();
        	//ArrayList with service station IDs
        	ssIDs = new ArrayList<String>();
        	//Hashtable <String, ArrayList> with queues and customer types
        	ssQueues = new Hashtable<String, ArrayList<String>> ();
        	ssTypes = new Hashtable<String, ArrayList<String>>();
        	
        } catch (Exception e){
            
             System.out.println(e.getMessage());
        }
            
        
    }

    public String getConfigpath() {
		return configpath;
	}

	public void setConfigpath(String configpath) {
		this.configpath = configpath;
	}

	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public ArrayList<ServiceStation> getStations() {
		return stations;
	}

	public void setStations(ArrayList<ServiceStation> stations) {
		this.stations = stations;
	}

	public ArrayList<TypeHeap> getHeaps() {
		return heaps;
	}

	public void setHeaps(ArrayList<TypeHeap> queues) {
		this.heaps = queues;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public ArrayList<Hashtable<String, String>> getCustomerTypesBuilder() {
		return customerTypesBuilder;
	}

	public void setCustomerTypesBuilder(
			ArrayList<Hashtable<String, String>> customerTypesBuilder) {
		this.customerTypesBuilder = customerTypesBuilder;
	}

	public ArrayList<String> getQueueIDs() {
		return queueIDs;
	}

	public void setQueueIDs(ArrayList<String> queueBuilder) {
		this.queueIDs = queueBuilder;
	}

	public Hashtable<String, ArrayList<String>> getQueueTypes() {
		return queueTypes;
	}

	public void setQueueTypes(Hashtable<String, ArrayList<String>> queueTypes) {
		this.queueTypes = queueTypes;
	}

	public ArrayList<String> getSsIDs() {
		return ssIDs;
	}

	public void setSsIDs(ArrayList<String> ssIDs) {
		this.ssIDs = ssIDs;
	}

	public Hashtable<String, ArrayList<String>>  getSsQueues() {
		return ssQueues;
	}

	public void setSsQueues(Hashtable<String, ArrayList<String>>  ssQueues) {
		this.ssQueues = ssQueues;
	}

	public Hashtable<String, ArrayList<String>> getSsTypes() {
		return ssTypes;
	}

	public void setSsTypes(Hashtable<String, ArrayList<String>> ssTypes) {
		this.ssTypes = ssTypes;
	}

	public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}

	//Creates the Dispatcher
	private void createDispatcher()
	{
    	dispatcher.setCustomerTypes(customerTable);
    	dispatcher.setMaxTicks(config.getSimLength());
    	dispatcher.setHeaps(getHeaps());
    	dispatcher.setSleepTime(1000);
	}
	
	//Creates the CustomerType objects
	private void createCustomerTypes()
	{
    	//Holds the CustomerTypes build from XML file
    	customerTable = new Hashtable<String, CustomerType>();
    	
    	String class1 = "First Class";
    	String class2 = "Coach";
    	
    	customerTable.put(class1, new CustomerType(class1, "First Class Customers",
    			config.getFirstClassServiceTime(), config.getFirstClassArrivalRate()));
    	customerTable.put(class2, new CustomerType(class2, "Coach Class Customers",
    			config.getCoachClassServiceTime(), config.getCoachClassArrivalRate()));
	}
	
	//Creates the queues
	private void createQueues()
	{
    	heaps.add(new TypeHeap("First", "First Class"));
		heaps.add(new TypeHeap("Coach", "Coach"));
    	
		for(TypeHeap q: heaps)
    	{
    		q.setObserver(observer);
    	}
	}
	
	//Creates the Service Station
	private void createServiceStations()
	{
		//Add the heaps to the service station
		stations.add(new FirstClassServiceStation("FC1", heaps));
		stations.add(new FirstClassServiceStation("FC2", heaps));
		stations.add(new CoachServiceStation("CC1", heaps));
		stations.add(new CoachServiceStation("CC2", heaps));
		stations.add(new CoachServiceStation("CC3", heaps));
    		
    	for(ServiceStation s: stations)
    	{
    		s.setObserver(observer);
    	}
    	observer.setNumberOfServiceStations(stations.size());
	}
	
	//Creates the Observer
	private void createObserver()
	{
		observer = new Observer(this.getDispatcher().getSleepTime());
		dispatcher.setObserver(observer);
	}
	
	public void build() {
		createCustomerTypes();
		createDispatcher();
		createObserver();
		createQueues();
		createServiceStations();

    }
    
	/**
	 * Begins the simulation.  Prints a message to the terminal when
	 * the simulation starts and when it has ended.  It ends when the
	 * maximum running time has occurred and all the queues are empty.
	 */
    public void start(){
    		System.out.println("Simulation has started");
    		this.dispatcher.start();
    		Iterator<ServiceStation> statIter = stations.iterator();
    		while(statIter.hasNext())
    		{
    			ServiceStation pss = (ServiceStation) statIter.next();
    			pss.start();
    		}
             
    		while(dispatcher.isRunning() || checkQueues())
    		{
    			try {
					Thread.sleep(this.sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
    		System.out.println("Simulation has ended");
    		observer.writeReport();
    		return;
    } 
    
    /**
     * Check if all of the queues are empty or not.
     * @return boolean - true if the queues are not empty
     */
    private boolean checkQueues()
    {
    	for(TypeHeap q: heaps)
    	{
    		if(q.getSize() > 0)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
}
