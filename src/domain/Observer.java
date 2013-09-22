package domain;

import java.util.Calendar;

public class Observer 
{
	//Stats to collect
	private long totalCustomers = 0;
	private long maxWaitTime = 0; 
	private long avgWaitTime = 0;
	private long minWaitTIme = 1000000;
	private int longestQueueSize = 0;
	private String longestQueue;
	private int numberOfServiceStations = 0;
	private long simulationStartTime = 0;
	private long simulationEndTime = 0; 
	private long totalTicks = 0;
	
	//Variables
	private long sleepTime;
	private Report report;
	
	public Observer(long sleepTime)
	{
		this.sleepTime = sleepTime;
		this.setSimulationStartTime(getCurrentTime());
		report = new Report(this);
	}
	
	public String getLongestQueue()
	{
		return this.longestQueue;
	}
	
	public void setLongestQueue(String longestQueue) {
		this.longestQueue = longestQueue;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public long getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public long getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(long maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public long getAvgWaitTime() {
		return avgWaitTime;
	}

	public void setAvgWaitTime(long avgWaitTime) {
		this.avgWaitTime = avgWaitTime;
	}

	public long getMinWaitTime() {
		return minWaitTIme;
	}

	public void setMinWaitTime(long minWaitTIme) {
		this.minWaitTIme = minWaitTIme;
	}

	public int getLongestQueueSize() {
		return longestQueueSize;
	}

	public void setLongestQueueSize(int longestQueue) {
		this.longestQueueSize = longestQueue;
	}

	public int getNumberOfServiceStations() {
		return numberOfServiceStations;
	}

	public void setNumberOfServiceStations(int numberOfServiceStations) {
		this.numberOfServiceStations = numberOfServiceStations;
	}

	public long getSimulationStartTime() {
		return simulationStartTime;
	}

	public void setSimulationStartTime(long simulationStartTime) {
		this.simulationStartTime = simulationStartTime;
	}

	public long getSimulationEndTime() {
		return simulationEndTime;
	}

	public void setSimulationEndTime(long simulationEndTime) {
		this.simulationEndTime = simulationEndTime;
	}

	public long getTotalTicks() {
		return totalTicks;
	}

	public void setTotalTicks(long totalTicks) {
		this.totalTicks = totalTicks;
	}
	
	public void queueMessage(String queueID, int length)
	{
		if(length > this.getLongestQueueSize())
		{
			this.setLongestQueue(queueID);
			this.setLongestQueueSize(length);
		}
	}
	
	public void serviceMessage(long arrivalTime)
	{
		this.totalCustomers++;
		checkCustomerTime(arrivalTime);
	}
	
	public void checkCustomerTime(long arrivalTime)
	{
		/*
		 *Calculate the time it took to server the customer based off of the simulation
		 *start time and the time they were dispatched. 
		 */
		long serveTime = getCurrentTime() - (this.getSimulationStartTime() + arrivalTime);
		if(serveTime > this.getMaxWaitTime())
		{
			this.setMaxWaitTime(serveTime);
		}
		if(serveTime < this.getMinWaitTime())
		{
			this.setMinWaitTime(serveTime);
		}
		avgWaitTime = ((this.avgWaitTime * (this.totalCustomers-1)) + serveTime) / (this.totalCustomers);
	}
	
	private long getCurrentTime()
	{
		Calendar calendar = Calendar.getInstance();
		return calendar.getTimeInMillis();
	}
	
	public void writeReport()
	{
		this.setSimulationEndTime(getCurrentTime());
		//calculate total ticks based off of how much time passed in the simulation
		this.setTotalTicks((this.getSimulationEndTime() - this.getSimulationStartTime()) / this.getSleepTime());
		report.writeResult("src/Output.txt"); //Hard coded into this...
	}
}
