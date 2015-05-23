package com.oracle.demo;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("future.jpg")
public class Future {
    @GET
    @Produces("image/jpeg")
    public Response getImage() {
        return Response.ok(new File("future.jpg"), "image/jpeg").build();
    }
}
