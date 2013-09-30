package domain;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
/**
 * An object that represents the report created for the simulation
 */
public class Report {

    private DateFormat formatter;
    private Observer observer;

    public Report(Observer observer) {
    	this.observer = observer;
        formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
    }
   
    /**
     * Prints the report to a JTextPane
     * @param tpDisplay - The JTextPane that will display the report
     */
    public void writeResult(JTextPane tpDisplay) {
    	
    	Document doc = tpDisplay.getDocument();
    	try {
			doc.insertString(0, "Simulation starts: " + formatter.format(observer.getSimulationStartTime()) + "\r\n", null);
	    	doc.insertString(doc.getLength(), "----------------------------------------------------------------\r\n", null);
	    	doc.insertString(doc.getLength(), "The LONGEST time spent for a customer to get serviced: "+ ((float)observer.getMaxWaitTime())/1000 + "\r\n", null);
	    	doc.insertString(doc.getLength(), "The SHORTEST time spent for a customer to get serviced: "+ ((float)observer.getMinWaitTime())/1000 + "\r\n", null);
	    	doc.insertString(doc.getLength(), "The AVERAGE time spent for a customer to get serviced: "+ ((float)observer.getAvgWaitTime())/1000 + "\r\n", null);
	    	doc.insertString(doc.getLength(), "The LONGEST queue during simulation: "+ observer.getLongestQueue() + "\r\n", null);
	    	doc.insertString(doc.getLength(), "----------------------------------------------------------------\r\n", null);
	    	doc.insertString(doc.getLength(), "Total customers serviced by " + observer.getNumberOfServiceStations() + " service station(s): " + observer.getTotalCustomers() + "\r\n", null);
	    	doc.insertString(doc.getLength(), "Total ticks: " + observer.getTotalTicks() +"\r\n", null);
	    	doc.insertString(doc.getLength(), "----------------------------------------------------------------\r\n", null);
	    	doc.insertString(doc.getLength(), "Simulation ends: " + this.formatter.format(observer.getSimulationEndTime()) + "\r\n", null);
	    	
    	} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
