package rest.resource;

import static validator.Validator.hasValidUUID;

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

import data.AddressInfoRepository;
import data.CustomerInfoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Address;
import model.Customer;
import validator.Validator;

/**
 * <p/>
 * This class produces a RESTful service to read/write the contents of the customer core data.
 *
 * Dokumente/git-workspace/demo-restWS-spring-jersey-jpa2-hibernate/src/main/java/org/codingpedia/demo/rest/service
 */
@Path("/")
@RequestScoped
@Api(
        value = "/",
        description = "Endpoint for Customer specific operations")
public class CustomerResourceRESTService {


    // ********************** Shipping Address ****************************************************************

    @Inject
    private EntityManager em;

    @Inject
    private CustomerInfoRepository customerInfoRepo;

    @Inject
    private AddressInfoRepository addressInfoRepo;


    @PUT // is idempotent
    @Path("/customers/{id}/addresses")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Update an existing address", response = String.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid customer ID supplied"),
        @ApiResponse(code = 400, message = "Given customerId is null"),
        @ApiResponse(code = 400, message = "Given address is null"),
        @ApiResponse(code = 404, message = "Customer not found"),
        @ApiResponse(code = 404, message = "Address not found not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    public Response updateAddress(
            @PathParam("id") String customerId, 
            Address oldAddress, Address newAddress) 
    {

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
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(customerId).build();
    }

    @POST // is NOT idempotent
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/customers/{id}/addresses")  
    @ApiOperation(
            value = "Address creation for an existing customer",
            notes = "Clients must pass a UUID (version 3-5) as unique identifier for a certain submission request."
                    + " This enables clients to submit the same request repeatedly without side effects."
                    + " The requested customer communication will be triggered at most once."
                    + " While the random UUID (v4) is very easy to generate in most programming languages it burdens the client"
                    + " to remember it at least until the request has been submitted successfully."
                    + " The namespace+hash based UUIDs (v3, v5) require more thought in the first place which may pay off"
                    + " through the benefit of a reproducible UUID solely based on existing data in the client's domain."
                    + " The first successful submission will be answered with status 202 and processed asynchronously."
                    + " Any further PUT to the same ID will" + " either yield 204, if the request is equal to one we already know,"
                    + " or 409, if the request data differs from a previous one using the same ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input") })
    public Response createAddress(
            @ApiParam(value = "UUID v3-5 according to RFC4122, canonical form, 36 characters, lower case") 
            @PathParam("id") String submissionId,
            @PathParam("customerId") String customerId, Address address) 
    {
         Validator.hasValidUUID(submissionId);

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
    @ApiOperation(value = "Find address by customer ID", 
        notes = "Returns a address or nonintegers will simulate API error conditions", 
        response = Address.class)
    @ApiResponses(value = { 
            @ApiResponse(code = 400, message = "Invalid customer ID supplied"),
            @ApiResponse(code = 404, message = "Addres not found") })
    public Response getAddressesByCustomerId(
            @PathParam("id") String customerId) 
    {
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
    @ApiOperation(
            value = "Finds all customers", notes = "Some customers", response = Customer.class, responseContainer = "List")
    @ApiResponses(
            value = { @ApiResponse(code = 401, message = "No customres found") })
    public Response allCustomers() 
    {
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
    public Response getCustomerById(
            @ApiParam(value = "id") 
            @PathParam("id") String id) 
    {
        Optional<Customer> customer = customerInfoRepo.findById(String.valueOf(id));
        if (!customer.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response.status(Response.Status.OK).entity(customer.get()).build();
    }

    @POST // it is NOT idempotent
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/{id}/customers")
    @ApiOperation(
            value = "Customer creation request",
            notes = "Clients must pass a UUID (version 3-5) as unique identifier for a certain submission request."
                    + " This enables clients to submit the same request repeatedly without side effects."
                    + " The requested customer communication will be triggered at most once."
                    + " While the random UUID (v4) is very easy to generate in most programming languages it burdens the client"
                    + " to remember it at least until the request has been submitted successfully."
                    + " The namespace+hash based UUIDs (v3, v5) require more thought in the first place which may pay off"
                    + " through the benefit of a reproducible UUID solely based on existing data in the client's domain."
                    + " The first successful submission will be answered with status 202 and processed asynchronously."
                    + " Any further PUT to the same ID will" + " either yield 204, if the request is equal to one we already know,"
                    + " or 409, if the request data differs from a previous one using the same ID.")
    @ApiResponses(value = { 
            @ApiResponse(code = 405, message = "Invalid input") })
    public Response createCustomer(
            @ApiParam(value = "UUID v3-5 according to RFC4122, canonical form, 36 characters, lower case") 
            @PathParam("id") String submissionId, 
            Customer customer) 
    {
        if (!hasValidUUID(submissionId)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(submissionId).build();
        }

        // Valildate customer. If email already exists => customer invalid
        String customerId = customerInfoRepo.createCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(customerId).build();
    }

    @PUT // it is idempotent
    @Path("/customers/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Update an existing customer")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid customer ID supplied"),
        @ApiResponse(code = 404, message = "Customer not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    public Response updateCustomer(
            @PathParam("id") String customerId, 
            Customer customer) 
    {
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
