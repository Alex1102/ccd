package ccd.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class AddressNotFoundExceptionMapper implements ExceptionMapper<AddressNotFoundException> {

    @Override
    public Response toResponse(AddressNotFoundException exception) {
        return Response.status(Status.NOT_FOUND).entity(exception.getMessage()).build();
    }

}