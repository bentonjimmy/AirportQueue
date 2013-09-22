package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Customer;
import domain.Queue;

public class QueueTest {
	
	Queue q1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ArrayList<String> types = new ArrayList<String>();
		types.add("priority1");
		types.add("priority2");
		types.add("priority3");
		q1 = new Queue();
		q1.setTypes(types);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCustomers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetCustomers() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testEnqueue() {
		Customer cp1 = new Customer("priority1", 0);
		Customer cp2 = new Customer("priority2", 0);
		Customer cp3 = new Customer("priority3", 0);
		Customer cn = null;
		assertTrue("Queue is empty", q1.getLength() == 0);
		q1.enqueue(cp1);
		q1.enqueue(cp2);
		assertTrue("Added two Customers", q1.getLength() == 2);
		q1.enqueue(cn);
		assertTrue("Null Customer check", q1.getLength() == 2);
		q1.enqueue(cp3);
		assertTrue("Null Customer check", q1.getLength() == 3);
	}

	@Test
	public void testTopCustomer() {
		Customer cp1 = new Customer("priority1", 0);
		Customer cp2 = new Customer("priority2", 0);
		Customer cp3 = new Customer("priority3", 0);
		String cp1ID = cp1.getId();
		assertTrue("Test empty queue", q1.getLength() == 0);
		q1.enqueue(cp1);
		assertTrue("Test one customer", cp1ID == q1.topCustomer("priority1").getId());
		assertTrue("Test size with one", q1.getLength() == 1);
		assertNull("Priority 2 doesn't exist", q1.topCustomer("priority2"));
		String cp2ID = cp2.getId();
		q1.enqueue(cp2);
		assertTrue("Test second customer", cp2ID == q1.topCustomer("priority2").getId());
		assertTrue("Test one customer", cp1ID == q1.topCustomer("priority1").getId());
		String cp3ID = cp3.getId();
		q1.enqueue(cp3);
		assertTrue("Test second customer", cp2ID == q1.topCustomer("priority2").getId());
		assertTrue("Test one customer", cp1ID == q1.topCustomer("priority1").getId());
		assertTrue("Test third customer", cp3ID == q1.topCustomer("priority3").getId());
		assertFalse("Test one customer", cp1ID == q1.topCustomer("priority3").getId());
		Customer cp4 = new Customer("priority1", 0);
		Customer cp5 = new Customer("priority1", 0);
		String cp5ID = cp5.getId();
		q1.enqueue(cp4);
		q1.enqueue(cp5);
		assertTrue("5 Customers", q1.getLength() == 5);
		assertFalse("Test fifth customer", cp5ID == q1.topCustomer("priority1").getId());
		assertTrue("Test third customer", cp3ID == q1.topCustomer("priority3").getId());
		assertTrue("Test second customer", cp2ID == q1.topCustomer("priority2").getId());
		assertTrue("Test one customer", cp1ID == q1.topCustomer("priority1").getId());
	}

	@Test
	public void testDequeueCustomer() {
		Customer cp1 = new Customer("priority1", 0);
		Customer cp2 = new Customer("priority2", 0);
		Customer cp3 = new Customer("priority3", 0);
		Customer cp4 = new Customer("priority1", 0);
		Customer cp5 = new Customer("priority1", 0);
		String cp1ID = cp1.getId();
		String cp2ID = cp2.getId();
		String cp3ID = cp3.getId();
		String cp4ID = cp4.getId();
		String cp5ID = cp5.getId();
		q1.enqueue(cp1);
		q1.enqueue(cp2);
		q1.enqueue(cp3);
		q1.dequeue(cp1);
		assertTrue("Dequeue cp1 - size", q1.getLength() == 2);
		assertTrue("Test second customer", cp2ID == q1.topCustomer("priority2").getId());
		assertTrue("Test third customer", cp3ID == q1.topCustomer("priority3").getId());
		assertNull("Test one customer", q1.topCustomer("priority1"));
		q1.enqueue(cp1);
		assertTrue("Check size is 3", q1.getLength() == 3);
		q1.enqueue(cp4);
		q1.enqueue(cp5);
		assertTrue("Check size is 5", q1.getLength() == 5);
		q1.dequeue(cp4);
		assertTrue("Check size is 4", q1.getLength() == 4);
		assertTrue("Test second customer", cp2ID == q1.topCustomer("priority2").getId());
		assertTrue("Test third customer", cp3ID == q1.topCustomer("priority3").getId());
		assertTrue("Test one customer", cp1ID == q1.topCustomer("priority1").getId());
		q1.dequeue(cp1);
		assertTrue("Test fifth customer", cp5ID == q1.topCustomer("priority1").getId());
	}

	@Test
	public void testDequeueString() {
		Customer cp1 = new Customer("priority1", 0);
		Customer cp2 = new Customer("priority2", 0);
		Customer cp3 = new Customer("priority3", 0);
		Customer cp4 = new Customer("priority1", 0);
		Customer cp5 = new Customer("priority1", 0);
		String cp1ID = cp1.getId();
		String cp2ID = cp2.getId();
		String cp3ID = cp3.getId();
		String cp4ID = cp4.getId();
		String cp5ID = cp5.getId();
		q1.enqueue(cp1);
		q1.enqueue(cp2);
		q1.enqueue(cp3);
		q1.enqueue(cp4);
		q1.enqueue(cp5);
		assertTrue("Test first customer", cp1ID == q1.topCustomer("priority1").getId());
		Customer c = q1.dequeue("priority1");
		assertTrue("Test it returned cp1", c == cp1);
		assertTrue("Test fourth customer", cp4ID == q1.topCustomer("priority1").getId());
		c = q1.dequeue("priority3");
		assertTrue("Test it returned cp3", c == cp3);
		assertNull("Test third customer is null", q1.topCustomer("priority3"));
		c = q1.dequeue("priority1");
		assertTrue("Test it returned cp4", c == cp4);
		assertTrue("Test fifth customer", cp5ID == q1.topCustomer("priority1").getId());
	}

}
