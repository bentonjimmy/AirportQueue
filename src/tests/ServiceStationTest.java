package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Customer;
import domain.PriorityServiceStation;
import domain.Queue;

public class ServiceStationTest {
	
	PriorityServiceStation ss;
	Queue q1, q2, q3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ArrayList<String> customerTypes = new ArrayList<String>();
		customerTypes.add("priority1");
		customerTypes.add("priority2");
		customerTypes.add("priority3");
		ss = new PriorityServiceStation();
		ss.setTypes(customerTypes);
		
		//Set up Queues
		ArrayList<String> q1Types = new ArrayList<String>();
		q1Types.add("priority1");
		q1Types.add("priority2");
		q1Types.add("priority3");
		q1 = new Queue();
		q1.setTypes(q1Types);
		
		ArrayList<String> q2Types = new ArrayList<String>();
		q2Types.add("priority1");
		q2Types.add("priority2");
		q2Types.add("priority3");
		q2 = new Queue();
		q2.setTypes(q2Types);
		
		ArrayList<String> q3Types = new ArrayList<String>();
		q3Types.add("priority1");
		q3Types.add("priority2");
		q3Types.add("priority3");
		q3 = new Queue();
		q3.setTypes(q3Types);
		
		ArrayList<Queue> queues = new ArrayList<Queue>();
		queues.add(q1);
		queues.add(q2);
		queues.add(q3);
		
		
		ss.setQueues(queues);
	}

	@After
	public void tearDown() throws Exception {
		ss = null;
	}
	
	public void addCustomer(Queue q, int t)
	{
		Customer cp1 = new Customer("priority1", t);
		Customer cp2 = new Customer("priority2", t+=5);
		Customer cp3 = new Customer("priority3", t+=5);
		Customer cp4 = new Customer("priority1", t+=5);
		Customer cp5 = new Customer("priority1", t+=5);
		q.enqueue(cp1);
		q.enqueue(cp2);
		q.enqueue(cp3);
		q.enqueue(cp4);
		q.enqueue(cp5);
	}
	
	public void simpleTest()
	{
		addCustomer(q1, 0);
		addCustomer(q2, 1);
		addCustomer(q3, 2);
		ss.getNextCustomer();
		assertTrue("Test customer 0", ss.getCustomer().getArrivalTime() == 0);
		ss.getNextCustomer();
		assertTrue("Test customer 1", ss.getCustomer().getArrivalTime() == 1);
		ss.getNextCustomer();
		assertTrue("Test customer 2", ss.getCustomer().getArrivalTime() == 2);
		ss.getNextCustomer();
		assertTrue("Test customer 3", ss.getCustomer().getArrivalTime() == 5);
		ss.getNextCustomer();
		assertTrue("Test customer 4", ss.getCustomer().getArrivalTime() == 6);
		ss.getNextCustomer();
		assertTrue("Test customer 5", ss.getCustomer().getArrivalTime() == 7);
		ss.getNextCustomer();
		assertTrue("Test customer 6", ss.getCustomer().getArrivalTime() == 10);
		ss.getNextCustomer();
		assertTrue("Test customer 7", ss.getCustomer().getArrivalTime() == 11);
		ss.getNextCustomer();
		assertTrue("Test customer 8", ss.getCustomer().getArrivalTime() == 12);
		ss.getNextCustomer();
		assertTrue("Test customer 9", ss.getCustomer().getArrivalTime() == 15);
		ss.getNextCustomer();
		assertTrue("Test customer 10", ss.getCustomer().getArrivalTime() == 16);
		ss.getNextCustomer();
		assertTrue("Test customer 11", ss.getCustomer().getArrivalTime() == 17);
		ss.getNextCustomer();
		assertTrue("Test customer 12", ss.getCustomer().getArrivalTime() == 20);
		ss.getNextCustomer();
		assertTrue("Test customer 13", ss.getCustomer().getArrivalTime() == 21);
		ss.getNextCustomer();
		assertTrue("Test customer 14", ss.getCustomer().getArrivalTime() == 22);
	}
	
	public void randomTestSetup()
	{
		int t = 0;
		for(int i=1; i<=15; i++)
		{
			int priority = ((int) (Math.random()*100)) % 3 + 1;
			int queueNum = ((int)(Math.random() * 100))% 3 + 1;
			Customer c = new Customer("priority"+Integer.toString(priority), t);
			System.out.print("Customer: Priority " + Integer.toString(priority) + ", time " + Integer.toString(t));
			System.out.print(", added to Queue " + Integer.toString(queueNum) + "\n");
			if(queueNum == 1)
			{
				q1.enqueue(c);
			}
			else if(queueNum == 2)
			{
				q2.enqueue(c);
			}
			else
			{
				q3.enqueue(c);
			}
			t++;
		}
	}
	
	public void randomTest()
	{
		for(int i=1; i<=15; i++)
		{
			ss.getNextCustomer();
			Customer c = ss.getCustomer();
			System.out.println("Customer arrival time "+c.getArrivalTime());
		}
	}
	
	public void emptyQueues()
	{
		ArrayList<Queue> q = ss.getQueues();
		Iterator <Queue> qi = q.iterator();
		int i = 1;
		while(qi.hasNext())
		{
			Queue test = qi.next();
			assertTrue("Test queue is empty: "+Integer.toString(i), test.getLength() == 0);
			i++;
		}
	}

	@Test
	public void testGetTypes() {
		ArrayList<String> t = ss.getTypes();
		assertTrue("Size of Types",t.size() == 3);
		Iterator<String> typeIter = t.iterator();
		int i = 1;
		while(typeIter.hasNext())
		{
			String type = typeIter.next();
			String p = "priority" + Integer.toString(i);
			assertTrue("Test types: priority" + Integer.toString(i), type.equals(p));
			i++;
		}
	}

	@Test
	public void testGetNextCustomer() {
		//emptyQueues();
		//simpleTest();
		//emptyQueues();
		randomTestSetup();
		randomTest();
		emptyQueues();
	}

	@Test
	public void testRun() {
		ss.setOpen(true);
		ss.setSleepTime(1000);
		emptyQueues();
		randomTestSetup();
		//new Thread(ss).start();
		ss.start();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss.setOpen(false);
		emptyQueues();
	}

}
