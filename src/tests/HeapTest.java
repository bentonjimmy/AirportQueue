package tests;

import java.util.Vector;

import domain.Customer;
import domain.Heap;
import junit.framework.TestCase;

public class HeapTest extends TestCase {

	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRemoveCustomer() {
		Heap h = new Heap();
		Vector<Customer> v = h.getVector();
		v.add(new Customer("Coach", 4, 1));
		v.add(new Customer("Coach", 5, 1));
		v.add(new Customer("Coach", 6, 1));
		v.add(new Customer("Coach", 15, 1));
		v.add(new Customer("Coach", 9, 1));
		v.add(new Customer("Coach", 7, 1));
		v.add(new Customer("Coach", 20, 1));
		v.add(new Customer("Coach", 16, 1));
		v.add(new Customer("Coach", 25, 1));
		v.add(new Customer("Coach", 14, 1));
		v.add(new Customer("Coach", 12, 1));
		v.add(new Customer("Coach", 11, 1));
		v.add(new Customer("Coach", 13, 1));
		
		h.setVector(v);
		Customer result = h.removeCustomer();
		assertTrue("First removal", result.getArrivalTime() == 4);
		v = h.getVector();
		assertTrue("Root after one removal", v.get(0).getArrivalTime() == 5);
		assertTrue("Last node after one removal", v.lastElement().getArrivalTime() == 11);
		
	}

}
