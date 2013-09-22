package domain;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * An object that represent the resulting report
 */
public class Report {

    private DateFormat formatter;
    private Observer observer;

    public Report(Observer observer) {
    	this.observer = observer;
        formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
    }

    public void writeResult(String outputFile) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), "utf-8"));
            writer.write("Simulation starts: " + formatter.format(observer.getSimulationStartTime()) + "\r\n");
            writer.write("----------------------------------------------------------------\r\n");
            writer.write("The LONGEST time spent for a customer to get serviced: "+ ((float)observer.getMaxWaitTime())/1000 + "\r\n");
            writer.write("The SHORTEST time spent for a customer to get serviced: "+ ((float)observer.getMinWaitTime())/1000 + "\r\n");
            writer.write("The AVERAGE time spent for a customer to get serviced: "+ ((float)observer.getAvgWaitTime())/1000 + "\r\n");
            writer.write("The LONGEST queue during simulation: "+ observer.getLongestQueue() + "\r\n");
            writer.write("----------------------------------------------------------------\r\n");
            writer.write("Total customers serviced by " + observer.getNumberOfServiceStations() + " service station(s): " + observer.getTotalCustomers() + "\r\n");
            writer.write("Total ticks: " + observer.getTotalTicks() +"\r\n");
            writer.write("----------------------------------------------------------------\r\n");
            writer.write("Simulation ends: " + this.formatter.format(observer.getSimulationEndTime()) + "\r\n");

        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            try {writer.close();} catch (Exception ex) {}
        }
    }
}
