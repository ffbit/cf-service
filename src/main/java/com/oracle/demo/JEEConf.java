package com.oracle.demo;

import java.util.concurrent.TimeoutException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import static java.util.concurrent.TimeUnit.SECONDS;

@Path("jeeconf")
public class JEEConf {
	@Inject
	DataService dataService;
	//@ManagedAsync -- NOT NEEDED
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void asyncGet(@Suspended final AsyncResponse asyncResponse) {
    	dataService
    		.findAudience()
    		.thenCombine(dataService.findImpression(), (a, b) -> a + b)
    		.thenApply(asyncResponse::resume)
            .exceptionally(asyncResponse::resume);
        
    	// way #2
    	asyncResponse.setTimeout(1, SECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(new TimeoutException()));
    }
}
