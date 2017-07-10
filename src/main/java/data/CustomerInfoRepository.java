package data;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
    }

    public String createCustomer(Customer customer) {

        Customer c = createTestCustomer(generateCustomerId(), "Andreas, Krause", "a.krause@google.com");
        this.customers.add(c);

        return this.findById(customerId.toString()).get().getId();
    }

    public Optional<String> updateCustomer(Customer customer) {

        Optional<Customer> customerToUpdate = findById(customer.getId());
        if (customerToUpdate.isPresent()) {
            customerToUpdate.get().setName(customer.getName());
            customerToUpdate.get().setEmail(customer.getEmail());
            return Optional.of(customerToUpdate.get().getId());
        }

        return Optional.empty();
    }

    public Optional<Customer> findById(String id) {

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
