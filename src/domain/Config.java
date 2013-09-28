/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

public class Config 
{
	public Config()
	{
		this.setCoachClassArrivalRate(0);
		this.setCoachClassServiceTime(0);
		this.setFirstClassArrivalRate(0);
		this.setFirstClassServiceTime(0);
		this.setSimLength(0);
	}
	
    public int getFirstClassArrivalRate() {
		return firstClassArrivalRate;
	}
	public void setFirstClassArrivalRate(int firstClassArrivalRate) {
		this.firstClassArrivalRate = firstClassArrivalRate;
	}
	public int getCoachClassArrivalRate() {
		return coachClassArrivalRate;
	}
	public void setCoachClassArrivalRate(int coachClassArrivalRate) {
		this.coachClassArrivalRate = coachClassArrivalRate;
	}
	public int getFirstClassServiceTime() {
		return firstClassServiceTime;
	}
	public void setFirstClassServiceTime(int firstClassServiceTime) {
		this.firstClassServiceTime = firstClassServiceTime;
	}
	public int getCoachClassServiceTime() {
		return coachClassServiceTime;
	}
	public void setCoachClassServiceTime(int coachClassServiceTime) {
		this.coachClassServiceTime = coachClassServiceTime;
	}
	public int getSimLength() {
		return simLength;
	}
	public void setSimLength(int simLength) {
		this.simLength = simLength;
	}

	private int firstClassArrivalRate;
    private int coachClassArrivalRate;
    private int firstClassServiceTime;
    private int coachClassServiceTime;
    private int simLength;
}
