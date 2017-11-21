package ccd.rest;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ccd.exception.AddressNotFoundException;
import ccd.exception.CustomerNotFoundException;
import ccd.repo.AddressInfoRepository;
import ccd.repo.CustomerInfoRepository;
import ccd.rest.api.ReadCustomerRestService;
import io.swagger.annotations.ApiParam;
import model.Address;
import model.Customer;


/**
 * Use this class to read only some customer data like address, customer it self and so one
 * 
 * @author awiegant
 */
@Path("/")
@RequestScoped
public class ReadCustomerRestServiceImpl implements ReadCustomerRestService {


    @Inject
    private EntityManager em;

    @Inject
    private AddressInfoRepository addressInfoRepo;
    @Inject
    private CustomerInfoRepository customerInfoRepo;



    // ********************** Customer ****************************************************************

    @Override
    public Response allCustomers() 
    {
//      boolean ok = Authorization.checkReadPermission(securityContext, requestEntity.getPrincipa());
//      if (!ok) {
//          return Response.status(Response.Status.FORBIDDEN).entity(customerId).build();
//      }

        List<Customer> customers = customerInfoRepo.findAll();
        if (customers.isEmpty()) {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
            throw new CustomerNotFoundException("No customers found!");
        }

        return Response.status(Response.Status.OK).entity(customers.toString()).build();
    }


    @Override
    public Response getCustomerById(
            @ApiParam(value = "id") 
            @PathParam("id") String customerId) 
    {
//      boolean ok = Authorization.checkReadPermission(securityContext, requestEntity.getPrincipa());
//      if (!ok) {
//          return Response.status(Response.Status.FORBIDDEN).entity(customerId).build();
//      }

        Optional<Customer> customer = customerInfoRepo.findCustomerById(String.valueOf(customerId));
        if (!customer.isPresent()) {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
            throw new CustomerNotFoundException("Customer with customerId " + customerId + "does not exist!");
        }

        return Response.status(Response.Status.OK).entity(customer.get()).build();
    }



    // ********************** Shipping Address ****************************************************************


    @Override
    public Response getAddressesByCustomerId(
            @PathParam("id") String customerId)
    {

//        boolean ok = Authorization.checkReadPermission(securityContext, requestEntity.getPrincipa());
//        if (!ok) {
//            return Response.status(Response.Status.FORBIDDEN).entity(customerId).build();
//        }

        Optional<Customer> customer = customerInfoRepo.findCustomerById(customerId);
        if (!customer.isPresent()) {
//            return Response.status(Response.Status.NOT_FOUND).entity(customerId).build();
            throw new CustomerNotFoundException("Customer with customerId " + customerId + "does not exist!");
        }

        List<Address> addresses = addressInfoRepo.findByCustomer(customer.get());
        if (addresses.isEmpty()) {
//            return Response.status(Response.Status.NOT_FOUND).entity(addresses).build();
//            throw new WebApplicationException(Response.Status.NOT_FOUND);

            throw new AddressNotFoundException("Don't found address for customer with customerId " + customerId + "!");
        }

        return Response.status(Response.Status.OK).entity(addresses.toString()).build();
    }
}
