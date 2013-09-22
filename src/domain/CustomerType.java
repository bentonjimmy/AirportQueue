package domain;

/*
 * Class CustomerType                *
 * Represent a type that can be assigned to Customer, Queue, and ServiceStation
 *
 * name: short string that uniquely identifies this type
 * description: display name for this type
 * serviceTime: time used to service customer of this type in the appropriate service station
 * totalCustomer: the maximum number of Customers a type can have in a spawn
 */
public class CustomerType {
    private String name;
    private String description;
    private int serviceTime;
    private int totalCustomers;
    private float dispatchingStake;

    public CustomerType(String name, String description, int serviceTime, int totalCustomers) {
        this.setName(name);
        this.setDescription(description);
        this.setServiceTime(serviceTime);
        this.setTotalCustomers(totalCustomers);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public float getDispatchingStake() {
        return dispatchingStake;
    }

    public void setDispatchingStake(float dispatchingStake) {
        this.dispatchingStake = dispatchingStake;
    }

    public Customer[] spawn(int number, long arrivalTime) {
        //if there is less customers left
        if(this.getTotalCustomers() < number) {
            number = this.getTotalCustomers();
        }
        Customer[] customers = new Customer[number];
        for(int i=0;i<number;i++) {
            customers[i] = new Customer(this.getName(), arrivalTime, serviceTime);
        }
        this.setTotalCustomers(this.getTotalCustomers()-number);
        return customers;
    }

    public boolean equals(Object o) {
        if(o instanceof CustomerType) {
            return (this.getName() == ((CustomerType)o).getName());
        }
        return false;
    }
}
