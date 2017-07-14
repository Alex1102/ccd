package rest;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.*;

import data.AddressInfoRepository;
import data.CustomerInfoRepository;
import model.Address;
import model.Customer;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 * 
 * 
 * 
 * 
 * Dokumente/git-workspace/demo-restWS-spring-jersey-jpa2-hibernate/src/main/java/org/codingpedia/demo/rest/service
 */
@Path("/")
@RequestScoped
@Api(value = "users", description = "Endpoint for User specific operations")
public class CustomerResourceRESTService {


    // ********************** Shipping Address ****************************************************************

    @Inject
    private EntityManager em;

    @Inject
    private CustomerInfoRepository customerInfoRepo;

    @Inject
    private AddressInfoRepository addressInfoRepo;


    @PUT // it is idempotent
    @Path("/customers/{id}/addresses")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateAddress(@PathParam("id") String customerId, Address oldAddress, Address newAddress) {

        // Valildate customer. If email already exists => customer invalid

        if (oldAddress == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(oldAddress).build();
        }

        if (newAddress == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(newAddress).build();
        }

        Optional<Customer> customer = customerInfoRepo.findById(customerId);
        if (!customer.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).entity(customerId).build();
        }

        boolean successful = addressInfoRepo.updateAddress(oldAddress, newAddress, customer.get());
        if (successful) {
            return Response.status(Response.Status.OK).entity(customerId).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(customerId).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/customers/{id}/addresses")
    public Response createAddress(@PathParam("id") String customerId, Address address) {
        // Valildate customer. If email already exists => customer invalid

        // Ist vielleicht schon zu viel Logik die ins Backend gehört ???

        Optional<Customer> customer = customerInfoRepo.findById(customerId);
        if (!customer.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).entity(customerId).build();
        }

        Optional<Address> createdAddress = addressInfoRepo.createAddress(address);
        if (!createdAddress.isPresent()) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(customerId).build();
        }

        customer.get().addAddress(createdAddress.get());

        return Response.status(Response.Status.CREATED).entity(customerId).build();
    }

    @GET
    @Path("/customers/{id}/shipping-address")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAddressesByCustomerId(@PathParam("id") String customerId) {

        Optional<Customer> customer = customerInfoRepo.findById(customerId);
        if (!customer.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).entity(customerId).build();
        }

        List<Address> addresses = addressInfoRepo.findByCustomer(customer.get());
        if (addresses.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response.status(Response.Status.OK).entity(addresses).build();
    }




    // ********************** Customer ****************************************************************

    @GET
    @Path("/customers")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response allCustomers() {

        List<Customer> customers = customerInfoRepo.findAll();
        if (customers.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response.status(Response.Status.OK).entity(customers.toString()).build();
    }


    // @Path("/{id:[1-9][0-9]*}")
    @GET
    @Path("/customers/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Returns customers details", notes = "Returns customer details.", response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of customer entity", response = Customer.class),
            @ApiResponse(code = 404, message = "Customer with given id does not exist"),
            @ApiResponse(code = 500, message = "Internal server error") }
        )
    public Response getCustomerById(@ApiParam(value = "id") @PathParam("id") String id) {

        Optional<Customer> customer = customerInfoRepo.findById(String.valueOf(id));
        if (!customer.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response.status(Response.Status.OK).entity(customer.get()).build();
    }

    @POST // it is NOT idempotent
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/customers")
    public Response createCustomer(Customer customer) {

        // Valildate customer. If email already exists => customer invalid
        String customerId = customerInfoRepo.createCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(customerId).build();
    }

    @PUT // it is idempotent
    @Path("/customers/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateCustomer(@PathParam("id") String customerId, Customer customer) {

        // Valildate customer. If email already exists => customer invalid

        if (!customer.getId().equals(customerId)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(customerId).build();
        }

        boolean successful = customerInfoRepo.updateCustomer(customer);
        if (successful) {
            return Response.status(Response.Status.OK).entity(customerId).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(customerId).build();
    }

}
