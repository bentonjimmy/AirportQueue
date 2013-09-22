package domain;

import java.util.*;

/**
 *  Abstract Class Dispatcher
 *  Base class for all dispatchers
 */
public abstract class Dispatcher implements Runnable {
    private float arrivalRate;
    private ArrayList<Queue> queues;
    private Hashtable<String, CustomerType> customerTypes;
    private long sleepTime = 2000;
    private boolean running = true;
    private long tick = 0;
    private long maxTicks = 30;
    private Observer observer;

    public float getArrivalRate() {
        return arrivalRate;
    }

    public void setArrivalRate(float arrivalRate) {
        this.arrivalRate = arrivalRate;
    }

    public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public ArrayList<Queue> getQueues() {
        return this.queues;
    }

    public void setQueues(ArrayList<Queue> queues) {
        this.queues = queues;
    }

    public void addQueue(Queue queue) {
        if(this.queues != null) {
            this.queues.add(queue);
        }
    }

    public Hashtable<String, CustomerType> getCustomerTypes() {
        return this.customerTypes;
    }
    
    public void setCustomerTypes(Hashtable<String, CustomerType> customerTypes)
    {
    	this.customerTypes = customerTypes;
    }

    public long getMaxTicks() {
        return maxTicks;
    }

    public void setMaxTicks(long maxTicks) {
        this.maxTicks = maxTicks;
    }

    public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}
	
	/*
     * Thread start and run
     */

	public void start() {
        (new Thread(this)).start();
    }

    public void run() {
        try {
            while(running) {
                //create customers based on the time
                Customer[] customers = this.dispatch((this.tick * this.sleepTime));
              //increase counter
                this.tick++;
                //assign customers to queues
                if (customers != null && customers.length > 0) {
                    assignCustomers(customers);
                }

                //if no more customers or out of time limit, exit thread
                if(!this.hasCustomers() || this.tick >= this.getMaxTicks())
                {
                    this.signalExit();
                    /*Remove below*/
                    if(!this.hasCustomers())
                    	System.out.println("No more customers");
                    else
                    	System.out.println("Exceeded Ticks");
                }
                //sleep for next tick
                Thread.sleep(this.sleepTime);
            }

        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void signalExit() {
        this.running = false;
    }
    /*
     *  Helper functions
     */

    public void assignCustomers(Customer[] customers) {
        for(int i=0;i<customers.length;i++) {
            getQueue(customers[i].getType()).enqueue(customers[i]);
        }
    }

    public Queue getQueue(String type) {
        /*
         * The logic here
         * get queue with the least amount of customers of type
         * if all the same we pick the first one
         */
        Queue temp = null;
        Iterator<Queue> iter = this.queues.iterator();
        while(iter.hasNext()) {
            Queue q = iter.next();
            if(q.hasType(type) && (temp == null  || q.getLength()<temp.getLength())) {
                temp = q;
            }
        }
        return temp;
    }

    public boolean hasCustomers() {
        /*
         * dispatcher should send end signal
         * when there is no more customers to spawn
         */
        String type = null;
        CustomerType customerType;
        Enumeration keys = this.getCustomerTypes().keys();
        while(keys.hasMoreElements()) {
            type = keys.nextElement().toString();
            customerType = this.getCustomerTypes().get(type);
            if(customerType.getTotalCustomers() > 0 ) {
                return true;
            }
        }
        return false;
    }

    abstract public Customer[] dispatch(Long time);
}
