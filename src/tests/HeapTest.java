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

	public void testAddCustomer()
	{
		Heap h = new Heap();
		h.addCustomer(new Customer("Coach", 4, 1));
		Vector<Customer> v = h.getVector();
		assertTrue("Check first node", v.firstElement().getArrivalTime() == 4);
		h.addCustomer(new Customer("Coach", 5, 1));
		v = h.getVector();
		assertTrue("Check first node - part two", v.firstElement().getArrivalTime() == 4);
		assertTrue("Check last node - part two", v.lastElement().getArrivalTime() == 5);
		
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
		v.add(new Customer("Coach", 8, 1));
		h.setVector(v);
		
		h.addCustomer(new Customer("Coach", 2, 1));
		v = h.getVector();
		assertTrue("Check first node - part three", v.firstElement().getArrivalTime() == 2);
		assertTrue("Check last node - part three", v.lastElement().getArrivalTime() == 20);
		for(int i=0; i<v.size(); i++)
		{
			System.out.print(v.get(i).getArrivalTime()+" ");
		}
		System.out.println();
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
		for(int i=0; i<v.size(); i++)
		{
			System.out.print(v.get(i).getArrivalTime()+" ");
		}
		System.out.println();
		
		Heap h2 = new Heap();
		v = h2.getVector();
		v.add(new Customer("Coach", 4, 1));
		v.add(new Customer("Coach", 5, 1));
		v.add(new Customer("Coach", 13, 1));
		h2.setVector(v);
		result = h2.removeCustomer();
		assertTrue("Second Removal", result.getArrivalTime() == 4);
		assertTrue("Root after second removal", v.get(0).getArrivalTime() == 5);
		assertTrue("Last node after second removal", v.lastElement().getArrivalTime() == 13);
		result = h2.removeCustomer();
		assertTrue("Second Removal - Part 2", result.getArrivalTime() == 5);
		assertTrue("Root after second removal - Part 2", v.get(0).getArrivalTime() == 13);
		assertTrue("Last node after second removal - Part 2", v.lastElement().getArrivalTime() == 13);
	}

}
