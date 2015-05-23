package com.oracle.demo;

import java.io.File;
import java.util.concurrent.CompletionException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomCompletionExceptionMapper implements ExceptionMapper<CompletionException> {

    public Response toResponse(CompletionException bex) {
        return Response.status(Status.BAD_REQUEST)
                .entity(new File("eerror.png")).type("image/png").build(); 
    }

}
