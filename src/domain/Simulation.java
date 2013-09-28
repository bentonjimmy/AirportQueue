/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.Hashtable;
/**
 *
 * @author Cody
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
    
    public Simulation(String config) {
        
        try {  
            this.configpath = config;
            this.config = new Config(this.configpath);
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

	private void createDispatcher()
	{
		settings = config.settings();
    	dispatcher.setCustomerTypes(customerTable);
    	dispatcher.setMaxTicks(Long.parseLong(settings.get("timeout")));
    	dispatcher.setHeaps(getHeaps());
    	dispatcher.setSleepTime((long) (Float.parseFloat(settings.get("tick"))*1000));
	}
	
	private void createCustomerTypes()
	{
		/*Build the CustomerTypes from XML file*/
		customerTypesBuilder = config.customerTypes();
    	//Holds the CustomerTypes build from XML file
    	customerTable = new Hashtable<String, CustomerType>();
    	
    	String class1 = "First Class";
    	String class2 = "Coach";
    	
    	customerTable.put(class1, new CustomerType(class1, "First Class Customers",
    			FirstServiceTime, 1000));
    	customerTable.put(class2, new CustomerType(class2, "Coach Class Customers",
    			CoachServiceTime, 1000));
	}
	
	private void createQueues()
	{
    	heaps.add(new TypeHeap("First", "First Class"));
		heaps.add(new TypeHeap("Coach", "Coach"));
    	
		for(TypeHeap q: heaps)
    	{
    		q.setObserver(observer);
    	}
	}
	
	private void createServiceStations()
	{
		config.stations(ssIDs, ssQueues, ssTypes);
    	Iterator<String> ssIDIter = ssIDs.iterator();
    	while(ssIDIter.hasNext())
    	{
    		String ssID = ssIDIter.next();
    		ArrayList<TypeHeap> queuesToAdd = new ArrayList<TypeHeap>();
    		ArrayList<String> alq = ssQueues.get(ssID);
    		Iterator<String> alqIter = alq.iterator();
    		while(alqIter.hasNext())
    		{
    			String ssq = alqIter.next();
    			Iterator<TypeHeap> queuesIter = heaps.iterator();
    			while(queuesIter.hasNext())
    			{
    				TypeHeap qTemp = queuesIter.next();
    				if(qTemp.getId().equals(ssq))
    				{
    					queuesToAdd.add(qTemp);
    				}
    			}
    		}
    		stations.add(new FirstClassServiceStation("FC1", queuesToAdd));
    		stations.add(new CoachServiceStation("CC1", queuesToAdd));
    		stations.add(new CoachServiceStation("CC2", queuesToAdd));
    		stations.add(new CoachServiceStation("CC3", queuesToAdd));
    	}
    	for(ServiceStation s: stations)
    	{
    		s.setObserver(observer);
    	}
    	observer.setNumberOfServiceStations(stations.size());
	}
	
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
    
    public void start(){
    		System.out.println("Simulation has started");
    		this.dispatcher.start();
    		Iterator<ServiceStation> statIter = stations.iterator();
    		while(statIter.hasNext())
    		{
    			ServiceStation pss = (ServiceStation) statIter.next();
    			pss.start();
    		}
             
    		while(dispatcher.isRunning())
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
    
    /*
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
    */
    
}
