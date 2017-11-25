package ccd.repo;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ccd.exception.CustomerNotFoundException;
import model.Customer;

@ApplicationScoped
public class CustomerInfoRepository {


    @Inject
    private EntityManager em;
    private List<Customer> customers = new ArrayList<>();


    private Integer customerId = 0;

    private String generateCustomerId() {
        customerId++;
        return customerId.toString();
    }

    public CustomerInfoRepository() {
        customers.add(createTestCustomer(generateCustomerId(), "Alex, Krause", "alex@gmx.com"));
        customers.add(createTestCustomer(generateCustomerId(), "Anna, Krause", "anna@web.com"));
        customers.add(createTestCustomer(generateCustomerId(), "Andreas, Krause", "a.krause@google.com"));
    }

    public String createCustomer(Customer customer) {
 
        String customerId = generateCustomerId();

        Customer c = createTestCustomer(customerId, customer.getName(), customer.getEmail());
        this.customers.add(c);

        return findCustomerById(customerId).get().getId();
    }

    public boolean updateCustomer(Customer customer) {

        Optional<Customer> customerToUpdate = findCustomerById(customer.getId());
        if (customerToUpdate.isPresent()) {
            customerToUpdate.get().setName(customer.getName());
            customerToUpdate.get().setEmail(customer.getEmail());
            return true;
        } else {
            new CustomerNotFoundException();
        }

        return false;
    }

    public Optional<Customer> findCustomerById(String id) {

        Optional<Customer> result = Optional.empty();

        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                result = Optional.of(customer);
            }
        }

        return result;
    }

    public List<Customer> findAll() {
        return customers;
    }

    private Customer createTestCustomer(String id, String name, String email) {

        Customer c = new Customer();
        c.setId(id);
        c.setName(name);
        c.setEmail(email);

        return c;
    }
}
