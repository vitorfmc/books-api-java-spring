package com.vitor.cordeiro.teste.quarkus.client;

import com.vitor.cordeiro.teste.quarkus.client.model.GoogleBooksResponse;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@RegisterRestClient
@RegisterProvider(value = GoogleBooksClientMapper.class)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GoogleBooksClient {

    @GET
    @Path("books/v1/volumes")
    GoogleBooksResponse getBook(
            @QueryParam("q") String q,
            @QueryParam("maxResults") Integer maxResults,
            @QueryParam("startIndex") Integer startIndex,
            @QueryParam("langRestrict") String langRestrict
    );

}
