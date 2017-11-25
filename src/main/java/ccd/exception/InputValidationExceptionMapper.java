package ccd.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InputValidationExceptionMapper implements ExceptionMapper<InputValidationException> {

    @Override
    public Response toResponse(InputValidationException exception) {
        return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }

}