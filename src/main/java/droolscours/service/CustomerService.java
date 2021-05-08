package droolscours.service;

import java.util.ArrayList;
import java.util.List;

import droolscours.Customer;

@SuppressWarnings("javadoc")
public class CustomerService {

    /**
     * @return list of customers
     */
    public List<Customer> getListCustomer() {
        final List<Customer> result = new ArrayList<Customer>();
        result.add(new Customer("Héron", "Nicolas", "Fr"));
        result.add(new Customer("Héron", "James", "GB"));
        result.add(new Customer("Héron", "Nicolas", "GB"));
        return result;
    }

}