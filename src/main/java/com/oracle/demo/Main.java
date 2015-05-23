package com.oracle.demo;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/jee";

    public static void main(String[] args) throws IOException {
    	ResourceConfig rc = new ResourceConfig().packages("com.oracle.demo");
    	HttpServer server = GrizzlyHttpServerFactory
    	        .createHttpServer(URI.create(BASE_URI), rc);
        System.out.println(String.format("Jersey app started " 
    	        + "with WADL available at %sapplication.wadl\n"
    	        + "Hit enter to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }
}

