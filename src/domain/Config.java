/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.net.URL;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import java.io.File;


/**
 *
 * @author Cody
 */
public class Config {
    
  
    private Document config;
    
    
    public Config(String url) throws Exception {
	    File fXmlFile = new File(url);
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		this.config = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	this.config.getDocumentElement().normalize();
        
    }
    
    public ArrayList<Hashtable<String, String>> customerTypes(){   
            return this.getCustomerTypes("customer_type");
    }
    
    public void stations(ArrayList<String> ssIDs, Hashtable<String, ArrayList<String>> ssQueues,
    		Hashtable<String, ArrayList<String>> ssTypes){   
    	NodeList nList = this.config.getElementsByTagName("service_station");
    	
    	for (int temp = 0; temp < nList.getLength(); temp++) {
    		Node nNode = nList.item(temp);
    		ArrayList<String> queues = new ArrayList<String>();
    		ArrayList<String> types = new ArrayList<String>();
    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

    			Element eElement = (Element) nNode;
    			String name = eElement.getAttribute("id");
    			ssIDs.add(name);
    			
    			NodeList aqList = (NodeList) eElement.getElementsByTagName("assigned_queue");
    			for (int temp2 = 0; temp2 < aqList.getLength(); temp2++) {

    				Node pNode = aqList.item(temp2);

    				Element pElement = (Element) pNode;

    				String value = pElement.getTextContent();    
    				queues.add(value);
    			}    
    			
    			NodeList acList = (NodeList) eElement.getElementsByTagName("allowed_customer_type");
    			for (int temp2 = 0; temp2 < acList.getLength(); temp2++) {

    				Node pNode = acList.item(temp2);

    				Element pElement = (Element) pNode;

    				String value = pElement.getTextContent();    
    				types.add(value);
    			}  
    			ssQueues.put(name, queues);
    			ssTypes.put(name, types);
    		}
    	}
    	
    }
      
    public Hashtable<String, String> settings(){   
            return this.getSettings("settings");
    }
      
    public void queues(ArrayList<String> queueIDs, Hashtable<String, ArrayList<String>> queueTypes){  
    	NodeList nList = this.config.getElementsByTagName("queue");  
    	
    	for (int temp = 0; temp < nList.getLength(); temp++) {
    		Node nNode = nList.item(temp);
    		ArrayList<String> custTypes = new ArrayList<String>();
    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

    			Element eElement = (Element) nNode;
    			String name = eElement.getAttribute("id");
    			queueIDs.add(name);
    			
    			NodeList pList = (NodeList) eElement.getElementsByTagName("customer_types");
    			for (int temp2 = 0; temp2 < pList.getLength(); temp2++) {

    				Node pNode = pList.item(temp2);

    				Element pElement = (Element) pNode;

    				String value = pElement.getTextContent();    
    				custTypes.add(value);
    			}     
    			queueTypes.put(name, custTypes);
    		}
    	}

    }
    
    public ArrayList<Hashtable<String, String>> getCustomerTypes(String type_name){

    	NodeList nList = this.config.getElementsByTagName(type_name);  
    	ArrayList<Hashtable<String, String>> custTypesList = new ArrayList<Hashtable<String, String>>();
    	
    	for (int temp = 0; temp < nList.getLength(); temp++) {
    		Hashtable<String, String> types = new Hashtable<String, String>(); 
    		Node nNode = nList.item(temp);

    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

    			Element eElement = (Element) nNode;
    			String name = eElement.getAttribute("id");
    			types.put("name", name);
    			
    			NodeList pList = (NodeList) eElement.getElementsByTagName("property");
    			for (int temp2 = 0; temp2 < pList.getLength(); temp2++) {

    				Node pNode = pList.item(temp2);

    				Element pElement = (Element) pNode;

    				String label = pElement.getAttribute("id");
    				String value = pElement.getTextContent();

    				types.put(label, value);    

    			}                         
    		}
    		custTypesList.add(types);
    	}

    	return custTypesList;
    }
    
    public Hashtable<String, String> getSettings(String type_name){

    	NodeList nList = this.config.getElementsByTagName(type_name);
    	Hashtable<String, String> types = new Hashtable<String, String>();   

    	for (int temp = 0; temp < nList.getLength(); temp++) {

    		Node nNode = nList.item(temp);

    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

    			Element eElement = (Element) nNode;
    			NodeList pList = (NodeList) eElement.getElementsByTagName("property");    

    			if ( eElement.getAttribute("id").toString() != "") {
    				String label1 = eElement.getAttribute("id").toString() + "." + "id";
    				String value2 = eElement.getAttribute("id").toString();
    				types.put(label1, value2); 
    			}

    			if ( eElement.getAttribute("type").toString() != "") {
    				String label1 = eElement.getAttribute("id").toString() + "." + "type";
    				String value2 = eElement.getAttribute("type").toString();
    				types.put(label1, value2); 
    			}
    			for (int temp2 = 0; temp2 < pList.getLength(); temp2++) {
    				Node pNode = pList.item(temp2);
    				Element pElement = (Element) pNode;
    				String label = pElement.getAttribute("id");
    				String value = pElement.getTextContent();
    				types.put(label, value);    

    			}                              
    		}
    	}

    	return types;
    }
    
    
    
    
    public Hashtable getStations(){

    	NodeList nList = this.config.getElementsByTagName("service_station");
    	Hashtable<String, String> types = new Hashtable<String, String>();   

    	for (int temp = 0; temp < nList.getLength(); temp++) {

    		Node nNode = nList.item(temp);

    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

    			Element eElement = (Element) nNode;
    			NodeList qList = (NodeList) eElement.getElementsByTagName("assigned_queue");    
    			NodeList cList = (NodeList) eElement.getElementsByTagName("allowed_customer_type");

    			if ( eElement.getAttribute("id").toString() != "") {
    				String label1 = eElement.getAttribute("id").toString() + "." + "id";
    				String value2 = eElement.getAttribute("id").toString();
    				types.put(label1, value2); 
    			}

    			if ( eElement.getAttribute("type").toString() != "") {
    				String label1 = eElement.getAttribute("id").toString() + "." + "type";
    				String value2 = eElement.getAttribute("type").toString();
    				types.put(label1, value2); 
    			}
    			for (int temp2 = 0; temp2 < cList.getLength(); temp2++) {
    				Node pNode = cList.item(temp2);
    				Element pElement = (Element) pNode;
    				String label = eElement.getAttribute("id").toString() +  "." + pElement.getNodeName().toString()  +  "." + pElement.getAttribute("id").toString();
    				String value = pElement.getTextContent();
    				types.put(label, value);    
    			}   

    			for (int temp2 = 0; temp2 < qList.getLength(); temp2++) {
    				Node qNode = qList.item(temp2);
    				Element qElement = (Element) qNode;
    				String label = eElement.getAttribute("id").toString() +  "." + qElement.getNodeName().toString()  +  "." + qElement.getAttribute("id").toString();
    				String value = qElement.getTextContent();
    				types.put(label, value);    
    			}
    		}
    	}

    	return types;
    }
    
}
