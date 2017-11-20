package ccd.rest.api;

import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Address;
import model.Customer;

/**
 * Use this class to modify some customer data like address, customer it self and so one
 * 
 * @author awiegant
 */
@Path("/")
@Api(value = "/", description = "Endpoint for Customer specific operations")
@ValidateOnExecution
public interface ModifyCustomerRestService {


    // ********************** Customer ****************************************************************


    @POST // it is NOT idempotent
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/{id}/customers")
    @ApiOperation(value = "Customer creation request", notes = "Clients must pass a UUID (version 3-5) as unique identifier for a certain submission request."
            + " This enables clients to submit the same request repeatedly without side effects."
            + " The requested customer communication will be triggered at most once."
            + " While the random UUID (v4) is very easy to generate in most programming languages it burdens the client"
            + " to remember it at least until the request has been submitted successfully."
            + " The namespace+hash based UUIDs (v3, v5) require more thought in the first place which may pay off"
            + " through the benefit of a reproducible UUID solely based on existing data in the client's domain."
            + " The first successful submission will be answered with status 202 and processed asynchronously." + " Any further PUT to the same ID will"
            + " either yield 204, if the request is equal to one we already know,"
            + " or 409, if the request data differs from a previous one using the same ID.")
    @ApiResponses(value = { 
            @ApiResponse(code = 201, message = "successful added customer", response = Customer.class),
            // @ApiResponse(code = 400, message = "Given customer data are invalid"),
            @ApiResponse(code = 400, message = "input validation failed, details as plain text entity", response = String.class),
            @ApiResponse(code = 403, message = "operation not permitted")
    })
    public Response createCustomer(
            @ApiParam(value = "UUID v3-5 according to RFC4122, canonical form, 36 characters, lower case") 
            @NotNull @NotEmpty @PathParam("id") String submissionId,
            @NotNull Customer customer, 
            @Context UriInfo uriInfo);


    @PUT // it is idempotent
    @Path("/customers/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Update an existing customer")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "successful update customer", response = Customer.class),
            // @ApiResponse(code = 400, message = "given customer data are invalid"),
            @ApiResponse(code = 400, message = "input validation failed, details as plain text entity", response = String.class),
            @ApiResponse(code = 403, message = "operation not permitted"),
            @ApiResponse(code = 404, message = "customer with given id does not exist"),
            @ApiResponse(code = 500, message = "internal Server Error") })
    public Response updateCustomer(
            @NotNull @NotEmpty @PathParam("id") String customerId,
            @NotNull  Customer customer);


    // ********************** Shipping Address ****************************************************************


    @POST // is NOT idempotent
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/{submissionId}/customers/{id}/addresses")
    @ApiOperation(value = "Address creation for an existing customer", notes = "Clients must pass a UUID (version 3-5) as unique identifier for a certain submission request."
            + " This enables clients to submit the same request repeatedly without side effects."
            + " The requested customer communication will be triggered at most once."
            + " While the random UUID (v4) is very easy to generate in most programming languages it burdens the client"
            + " to remember it at least until the request has been submitted successfully."
            + " The namespace+hash based UUIDs (v3, v5) require more thought in the first place which may pay off"
            + " through the benefit of a reproducible UUID solely based on existing data in the client's domain."
            + " The first successful submission will be answered with status 202 and processed asynchronously." + " Any further PUT to the same ID will"
            + " either yield 204, if the request is equal to one we already know,"
            + " or 409, if the request data differs from a previous one using the same ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful added address", response = Address.class),
            @ApiResponse(code = 400, message = "input validation failed, details as plain text entity", response = String.class),
            @ApiResponse(code = 403, message = "operation not permitted"),
            @ApiResponse(code = 404, message = "customer with given id does not exist") })
    public Response createAddress(
            @ApiParam(value = "UUID v3-5 according to RFC4122, canonical form, 36 characters, lower case") 
            @NotNull @NotEmpty @PathParam("submissionId") String submissionId,
            @NotNull @NotEmpty @PathParam("id") String customerId,
            Address address);


    @PUT // is idempotent
    @Path("/customers/{id}/addresses")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Update an existing address", response = String.class)
    @ApiResponses(value = {
            // @ApiResponse(code = 400, message = "Invalid customer ID supplied"),
            // @ApiResponse(code = 400, message = "given customerId is null"),
            // @ApiResponse(code = 400, message = "given address is null"),
            @ApiResponse(code = 200, message = "successful update address", response = Address.class),
            @ApiResponse(code = 400, message = "input validation failed, details as plain text entity", response = String.class), // In diesem Fall ist entweder customerId o. Address nicht valide.
            @ApiResponse(code = 404, message = "no customerId or no address matching by given identifiers") }) // In diesem Fall sind customerId u. Address an sich valide. Stimme aber NICHT mit Daten in DB überein.
    public Response updateAddress(
            @PathParam("id") @NotNull @NotEmpty String customerId, 
            @NotNull @NotEmpty Address oldAddress, 
            @NotNull @NotEmpty Address newAddress);

}
