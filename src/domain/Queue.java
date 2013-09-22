package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 *
 */
public class Queue {
    private String id;
    private ArrayList<Customer> customers;
    private ArrayList<String> customerTypes;
    private Observer observer;

    public Queue() {
        this(UUID.randomUUID().toString(), new ArrayList<String>());
    }

    public Queue(String id, ArrayList<String> types) {
        this.setId(id);
        this.setTypes(types);
        this.setCustomers(new ArrayList<Customer>());
    }

    public Observer getObserver() {
		return observer;
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getType() {
        return customerTypes;
    }

    public void setTypes(ArrayList<String> types) {
        this.customerTypes = types;
    }

    public void addType(String t) {
        if (t != null) {
            customerTypes.add(t);
        }
    }

    public boolean hasType(String t) {
        if(customerTypes.contains(t)) {
            return true;
        }
        return false;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> c) {
        this.customers = c;
    }

    public int getLength() {
        return customers.size();
    }

    public void enqueue(Customer customer) {
        if (customer != null) {
            customers.add(customer);
            observer.queueMessage(this.getId(), this.getCustomers().size());
            System.out.println("Enqueue Customer Arrival:"+customer.getArrivalTime()+", Priority:" +
            		customer.getType() + ", ID:"+customer.getId()+", Queue:"+this.getId());
        }
    }

       /*
     * Returns the first customer in the queue with a certain CustomerType
     * but does not remove them from the queue
     */
   public Customer topCustomer(String type)
   {
       if (type != null) {
           Iterator<Customer> custIter = customers.iterator();
           while (custIter.hasNext()) {
               Customer c = custIter.next();
               String t = c.getType();
               if (type.equals(t))
               {
                   return c;   
               }
           }
       }
       return null;
   }
   
   /*
    * Returns the first customer in the list regardless of type
    */
   public Customer topCustomer()
   {
	   if(this.getLength() != 0)
	   {
		   return customers.get(0);
	   }
	   else
	   {
		   return null;
	   }
   }

   /*
    * Returns the first customer in the queue with a certain CustomerType
    * and will remove them from the queue
    */
   public Customer dequeue(Customer c)
   {
	   if(c != null)
	   {
		   customers.remove(c);
	   }
	   return c;
   }
   
   public Customer dequeue(String type) {
	   Customer c = this.topCustomer(type);
	   return this.dequeue(c);
    }
}
