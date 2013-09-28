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
	
    public float getFirstClassArrivalRate() {
		return firstClassArrivalRate;
	}
	public void setFirstClassArrivalRate(float firstClassArrivalRate) {
		this.firstClassArrivalRate = firstClassArrivalRate;
	}
	public float getCoachClassArrivalRate() {
		return coachClassArrivalRate;
	}
	public void setCoachClassArrivalRate(float coachClassArrivalRate) {
		this.coachClassArrivalRate = coachClassArrivalRate;
	}
	public float getFirstClassServiceTime() {
		return firstClassServiceTime;
	}
	public void setFirstClassServiceTime(float firstClassServiceTime) {
		this.firstClassServiceTime = firstClassServiceTime;
	}
	public float getCoachClassServiceTime() {
		return coachClassServiceTime;
	}
	public void setCoachClassServiceTime(float coachClassServiceTime) {
		this.coachClassServiceTime = coachClassServiceTime;
	}
	public float getSimLength() {
		return simLength;
	}
	public void setSimLength(float simLength) {
		this.simLength = simLength;
	}

	private float firstClassArrivalRate;
    private float coachClassArrivalRate;
    private float firstClassServiceTime;
    private float coachClassServiceTime;
    private float simLength;
}
