package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Config;

public class ConfigTest {

	public Config config;
	
	/*
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Config config = new Config("config.xml");
	}
	*/
	
	
	@Before
	public void setUp() throws Exception {
		config = new Config("src/config.xml");
	}
	
	@After
	public void tearDown() throws Exception {
		config = null;
	}

	@Test
	public void testCustomerTypes() {
		System.out.println("\nReading CustomerTypes...");
		ArrayList<Hashtable<String, String>> al = config.customerTypes();
		Iterator<Hashtable<String, String>> iter = al.iterator();
		while(iter.hasNext())
		{
			Hashtable<String, String> ht =iter.next();
			Enumeration<String> keys = ht.keys();
			while(keys.hasMoreElements())
			{
				String type = keys.nextElement().toString();
				String value = (String) ht.get(type);
				System.out.println(type + ": " + value);
			}
		}
	}

	
	@Test
	public void testQueues() {
		ArrayList<String> qIDs = new ArrayList<String>();
		Hashtable<String, ArrayList<String>> qTs = new Hashtable<String, ArrayList<String>>();
		System.out.println("\nReading queues...");
		config.queues(qIDs, qTs);
		Iterator<String> qIter = qIDs.iterator();
		while(qIter.hasNext())
		{
			String name = qIter.next();
			ArrayList<String> types = qTs.get(name);
			Iterator<String> tIter = types.iterator();
			while(tIter.hasNext())
			{
				String prop = tIter.next();
				System.out.println(name + " - " + prop);
			}
		}
	}

	@Test
	public void testStations() {
		ArrayList<String> ssIDs = new ArrayList<String>();  
		Hashtable<String, ArrayList<String>> ssQueues = new Hashtable<String, ArrayList<String>>(); 
		Hashtable<String, ArrayList<String>> ssTypes = new Hashtable<String, ArrayList<String>>();
		System.out.println("\nReading stations...");
		config.stations(ssIDs, ssQueues, ssTypes);
		Iterator<String> qIter = ssIDs.iterator();
		while(qIter.hasNext())
		{
			String name = qIter.next();
			ArrayList<String> types = ssQueues.get(name);
			Iterator<String> tIter = types.iterator();
			while(tIter.hasNext())
			{
				String prop = tIter.next();
				System.out.println(name + " - " + prop);
			}
			ArrayList<String> types2 = ssTypes.get(name);
			Iterator<String> tIter2 = types2.iterator();
			while(tIter2.hasNext())
			{
				String prop = tIter2.next();
				System.out.println(name + " - " + prop);
			}
		}
	}
	
	

	@Test
	public void testSettings() {
		System.out.println("\nReading Settings...");
		Hashtable ht = config.getSettings("settings");
		Enumeration<String> keys = ht.keys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			System.out.println(key + ": " + ht.get(key));
		}
	}

}
