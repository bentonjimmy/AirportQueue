package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Simulation;

public class SimulationTest {

	Simulation sim = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		sim = null;
	}

	/*
	@Test
	public void testSimulation() {
		assertNull("check sim is null", sim);
		sim = new Simulation("src/config.xml");
		assertTrue("CustomerTypesBuilder check", sim.getCustomerTypesBuilder().size() == 0);
		assertTrue("getQueueIDs check", sim.getQueueIDs().size() == 0);
		assertTrue("SsIDs check", sim.getSsIDs().size() == 0);
		assertTrue("SsQueues check", sim.getSsQueues().size() == 0);
		assertTrue("SsTypes check", sim.getSsTypes().size() == 0);
	}
	*/

	@Test
	public void testBuild() {
		sim = new Simulation("src/config.xml");
		/*
		sim.getCustomerTypesBuilder().add(createCustTypeTable("priority1", "Priority 1 Customers", "5", "50"));
		sim.getCustomerTypesBuilder().add(createCustTypeTable("priority2", "Priority 2 Customers", "5", "50"));
		sim.getCustomerTypesBuilder().add(createCustTypeTable("priority3", "Priority 3 Customers", "5", "50"));
		assertTrue("Test CustomerTypeBuilder",sim.getCustomerTypesBuilder().size() == 3);
		
		sim.getQueueIDs().add("queueA");
		sim.getQueueIDs().add("queueB");
		sim.getQueueIDs().add("queueC");
		ArrayList<String> qtypes = new ArrayList<String>();
		qtypes.add("priority1");
		qtypes.add("priority2");
		qtypes.add("priority3");
		sim.getQueueTypes().put("queueA", qtypes);
		sim.getQueueTypes().put("queueB", qtypes);
		sim.getQueueTypes().put("queueC", qtypes);
		assertTrue("Test QueueID", sim.getQueueIDs().size() == 3);
		assertTrue("Test QueueTypes", sim.getQueueTypes().size() == 3);
		
		ArrayList<String> ssQueues = new ArrayList<String>();
		ssQueues.add("queueA");
		ssQueues.add("queueB");
		ssQueues.add("queueC");
		sim.getSsIDs().add("SS");
		sim.getSsQueues().put("SS", ssQueues);
		ArrayList<String> ct = new ArrayList<String>();
		ct.add("priority1");
		ct.add("priority2");
		ct.add("priority3");
		sim.getSsTypes().put("SS", ct);
		*/
		
		sim.build();
		sim.start();
	}
	/*
	private Hashtable<String, String> createCustTypeTable(String name, String description, String serviceTime, String totalCustomers)
	{
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put("name", name);
		ht.put("description", description);
		ht.put("serviceTime", serviceTime);
		ht.put("totalCustomers", totalCustomers);
		return ht;
	}
	*/

}
