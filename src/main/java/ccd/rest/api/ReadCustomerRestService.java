package ccd.rest.api;

import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Address;
import model.Customer;


/**
 * Use this class to read only some customer data like address, customer it self and so one
 *
 */
@Path("/")
@Api( value = "/",
        description = "Endpoint for Customer specific operations")
@ValidateOnExecution
public interface ReadCustomerRestService {


    // ********************** Customer ****************************************************************


    @GET
    @Path("/customers")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(
            value = "Finds all customers", notes = "Some customers", response = Customer.class, responseContainer = "List")
    @ApiResponses( value = { 
            @ApiResponse(code = 200, message = "successful retrieval customers", response = String.class),
            @ApiResponse(code = 403, message = "operation not permitted"),
            @ApiResponse(code = 404, message = "no customres found")}
    )
    public Response allCustomers();



    // @Path("/{id:[1-9][0-9]*}")
    @GET
    @Path("/customers/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Returns customers details", notes = "Returns customer details.", response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful retrieval customer", response = Customer.class),
            @ApiResponse(code = 403, message = "operation not permitted"),
            @ApiResponse(code = 404, message = "customer with given id does not exist")}
    )
    public Response getCustomerById(
            @ApiParam(value = "id") 
            @NotNull @NotEmpty @PathParam("id") String customerId);



    // ********************** Shipping Address ****************************************************************


    @GET
    @Path("/customers/{id}/address")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @ApiOperation(value = "Find address by customer ID",
        notes = "Returns a address or nonintegers will simulate API error conditions",
        response = Address.class)
    @ApiResponses(value = {
//            @ApiResponse(code = 404, message = "customer with given ID not found"),
//            @ApiResponse(code = 404, message = "address not found"),
            @ApiResponse(code = 200, message = "successful retrieval customer shipping addresses", response = String.class),
            @ApiResponse(code = 403, message = "operation not permitted"),
            @ApiResponse(code = 404, message = "no customer or addresses matching given identifier")} // Wie sieht die richtige Lösung! Hier können beide Ids falsch sein Customer
    )
    public Response getAddressesByCustomerId(
            @NotNull @NotEmpty @PathParam("id") String customerId);

}

