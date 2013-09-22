package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.CustomerType;
import domain.PriorityServiceStation;
import domain.Queue;
import domain.SimpleDispatcher;

public class SimpleDispatcherTest {
	
	SimpleDispatcher sd;
	PriorityServiceStation ss;
	Queue q1, q2, q3;
	ArrayList<Queue> queues;
	Hashtable<String, CustomerType> ht;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ArrayList<String> q1Types = new ArrayList<String>();
		q1Types.add("priority1");
		q1Types.add("priority2");
		q1Types.add("priority3");
		q1 = new Queue();
		q1.setTypes(q1Types);
		q1.setId("q1");
		
		ArrayList<String> q2Types = new ArrayList<String>();
		q2Types.add("priority1");
		q2Types.add("priority2");
		q2Types.add("priority3");
		q2 = new Queue();
		q2.setTypes(q2Types);
		q2.setId("q2");
		
		ArrayList<String> q3Types = new ArrayList<String>();
		q3Types.add("priority1");
		q3Types.add("priority2");
		q3Types.add("priority3");
		q3 = new Queue();
		q3.setTypes(q3Types);
		q3.setId("q3");
		
		queues = new ArrayList<Queue>();
		queues.add(q1);
		queues.add(q2);
		queues.add(q3);
		
		//Set up CustomerTypes
		CustomerType ct1 = new CustomerType("priority1", "Priority 1", 2000, 10);
		CustomerType ct2 = new CustomerType("priority2", "Priority 2", 2000, 10);
		CustomerType ct3 = new CustomerType("priority3", "Priority 3", 2000, 10);
		ht = new Hashtable<String, CustomerType>();
		ht.put("priority1", ct1);
		ht.put("priority2", ct2);
		ht.put("priority3", ct3);
		
		//Set up service station
		ss = new PriorityServiceStation();
		ss.setTypes(q1Types);
		ss.setQueues(queues);
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	@Test
	public void testDispatch() {
		sd = new SimpleDispatcher(10, 15);
		sd.setQueues(queues);
		sd.setCustomerTypes(ht);
		Customer[] c = sd.dispatch((long) 0);
		assertTrue("Check returned Customer array", c.length >= 3);
		System.out.println("Size of c: "+c.length);
		for(int i=0; i<c.length; i++)
		{
			System.out.println("Customer "+ i +": "+c[i].getType());
		}
	}
	*/

	@Test
	public void testRun() {
		sd = new SimpleDispatcher(1, 2);
		sd.setQueues(queues);
		sd.setCustomerTypes(ht);
		sd.start();
		ss.start();
		//DEBUG
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Queue> qI = sd.getQueues().iterator();
		while(qI.hasNext())
		{
			assertTrue("Test Queue Length",qI.next().getLength() == 0);
		}
	}

	/*
	@Test
	public void testAssignCustomers() {
		int size = 7;
		sd = new SimpleDispatcher(1, 3);
		sd.setQueues(queues);
		Customer[] customers  = new Customer[size];
		for(int i=0; i<size; i++)
		{
			customers[i] = new Customer("priority1", i);
		}
		sd.assignCustomers(customers);
		ArrayList<Queue> qc = sd.getQueues();
		Iterator<Queue> qIter = qc.iterator();
		while(qIter.hasNext())
		{
			Queue queue = qIter.next();
			if(queue == q1)
			{
				assertTrue("Test size of q1", queue.getLength() == 3);
			}
			else
			{
				assertTrue("Test size of q2 and q3", queue.getLength() == 2);
			}
		}
	}
	*/

}
