package ccd.rest.resource;

import static ccd.rest.validation.Validator.validateUUID;

import java.net.URI;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import ccd.exception.CustomerNotFoundException;
import ccd.repo.AddressInfoRepository;
import ccd.repo.CustomerInfoRepository;
import ccd.rest.api.ModifyCustomerRestService;
import io.swagger.annotations.ApiParam;
import model.Address;
import model.Customer;


/**
 * Use this class to modify some customer data like address, customer it self and so one
 * 
 * @author awiegant
 */
@RequestScoped
public class ModifyCustomerRestServiceImpl implements ModifyCustomerRestService {


    @Inject
    private EntityManager em;

    @Inject
    private AddressInfoRepository addressInfoRepo;
    @Inject
    private CustomerInfoRepository customerInfoRepo;


    // ********************** Customer ****************************************************************

    @Override
    public Response createCustomer(
            @ApiParam(value = "UUID v3-5 according to RFC4122, canonical form, 36 characters, lower case") @PathParam("id") String submissionId,
            Customer customer, 
            @Context UriInfo uriInfo) {

        // boolean ok = Authorization.checkReadPermission(securityContext, requestEntity.getPrincipa());
        // if (!ok) {
        // return Response.status(Response.Status.FORBIDDEN).entity(customerId).build();
        // }
        //
        // Use Validation-Api to check validity of input values
        // if (!customer.hasValidData()) {
        // return Response.status(Response.Status.BAD_REQUEST).entity(customerId).build();
        // }

        validateUUID(submissionId);

        // Valildate customer. If email already exists => customer invalid. Do that perhaps in Backend, if you add the customer to DB
        String customerId = null;
        try {
            // Wenn es ansynchron zu gehen soll, dann gibt es wahrscheinlich noch keine customerId
            // Ausser man verbig eine Temporäre oder legt eine an. Es kann aber passieren, das der 
            // Customer trotzdem nicht angelegt wrid!
            // Überlegungen machen ob eine asynchrone Kommunikation hier Sinn macht.
            // Für den ersten wurf wir sicherlich auch ein synchrone Kommunikation langen!
            customerId = customerInfoRepo.createCustomer(customer);
        } catch (Exception e) {
            // return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(customerId).build();
            throw new WebApplicationException("Could not create customer with customerId " + customerId, Status.INTERNAL_SERVER_ERROR);
        }

        URI location = uriInfo.getBaseUriBuilder() //
                .path("/") //
                .path("createCustomer/") //
                .resolveTemplate("customerId", customerId) //
                .build();

        return Response.created(location).build();
        // return Response.status(Response.Status.CREATED).entity(customerId).build();
    }


    @Override
    public Response updateCustomer(
            @PathParam("id") String customerId, // Warum steckt man die customerId nicht in den Customer-Objekt?
            Customer customer) {
        // boolean ok = Authorization.checkReadPermission(securityContext, requestEntity.getPrincipa());
        // if (!ok) {
        // return Response.status(Response.Status.FORBIDDEN).entity(customerId).build();
        // }

        // Use Validation-Api to check validity of input values
        // if (!customer.hasValidData()) {
        // return Response.status(Response.Status.BAD_REQUEST).entity(customerId).build();
        // }

        if (!customer.hasId(customerId)) {
            // return Response.status(Response.Status.NOT_FOUND).entity(customerId).build();

            throw new CustomerNotFoundException("Customer with customerId " + customerId + "does not exist!");
        }

        boolean successful = false;
        try {
            successful = customerInfoRepo.updateCustomer(customer);
        } catch (Exception e) {
            throw new WebApplicationException("Could not update customer with customerId " + customerId, Status.INTERNAL_SERVER_ERROR);
        }

        // TODO aw: hier noch mal nachdenke was man hier machen kann und welche Exception zu werfen ist
        if (!successful) {
            // return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(customerId).build();
            throw new WebApplicationException("Could not update customer with customerId " + customerId, Status.INTERNAL_SERVER_ERROR);
        }
        return Response.status(Response.Status.OK).entity(customerId).build();
    }


    // ********************** Shipping Address ****************************************************************


    @Override
    public Response createAddress(
            @ApiParam(value = "UUID v3-5 according to RFC4122, canonical form, 36 characters, lower case") @PathParam("submissionId") String submissionId,
            @PathParam("id") String customerId, Address address) {

        validateUUID(submissionId);

        // Use Validation-Api to check validity of input values
        // if (customerId == null || customerId.isEmpty()) {
        // return Response.status(Response.Status.NOT_FOUND).entity(customerId).build();
        // }


        // Ist vielleicht schon zu viel Logik die ins Backend gehört ???
        Optional<Customer> customer = customerInfoRepo.findCustomerById(customerId);
        if (!customer.isPresent()) {
            // return Response.status(Response.Status.NOT_FOUND).entity(customerId).build();

            throw new CustomerNotFoundException("Customer with customerId " + customerId + "does not exist!");
        }

        // TODO aw: Dies soll definitiv auf der Service-Ebene passieren und nicht im RestService
        Optional<Address> createdAddress = Optional.empty();
        try {
            createdAddress = addressInfoRepo.createAddress(address);
            if (!createdAddress.isPresent()) {
                // return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(customerId).build();
                throw new WebApplicationException("Could not create address for customer with customerId " + customerId, Status.INTERNAL_SERVER_ERROR);
            }

            customer.get().addAddress(createdAddress.get());

        } catch (Exception e) {
            // return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(customerId).build();
            throw new WebApplicationException("Could not create address for customer with customerId " + customerId, Status.INTERNAL_SERVER_ERROR);
        }

        return Response.status(Response.Status.CREATED).entity(customerId).build();
    }


    @Override
    public Response updateAddress(@PathParam("id") String customerId, Address oldAddress, Address newAddress) {

        // Should be validate with Validation Api
        // if (oldAddress == null) {
        // return Response.status(Response.Status.BAD_REQUEST).entity(oldAddress).build();
        // }
        //
        // if (newAddress == null) {
        // return Response.status(Response.Status.BAD_REQUEST).entity(newAddress).build();
        // }

        Optional<Customer> customer = customerInfoRepo.findCustomerById(customerId);
        if (!customer.isPresent()) {
            // return Response.status(Response.Status.NOT_FOUND).entity(customerId).build();

            throw new CustomerNotFoundException("Customer with customerId " + customerId + "does not exist!");
        }

        boolean successful = false;
        try {
            successful = addressInfoRepo.updateAddress(oldAddress, newAddress, customer.get());
        } catch (Exception e) {
            // return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(customerId).build();
            throw new WebApplicationException("Could not update address for customer with customerId " + customerId, Status.INTERNAL_SERVER_ERROR);
        }

        // TODO aw: hier noch mal nachdenke was man hier machen kann und welche Exception zu werfen ist
        if (!successful) {
            // return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(customerId).build();
            throw new WebApplicationException("Could not update address for customer with customerId " + customerId, Status.INTERNAL_SERVER_ERROR);
        }
        return Response.status(Response.Status.OK).entity(customerId).build();
    }

}
