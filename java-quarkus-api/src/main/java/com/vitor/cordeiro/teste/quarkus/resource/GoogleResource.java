package com.vitor.cordeiro.teste.quarkus.resource;

import com.vitor.cordeiro.teste.quarkus.entity.BookResponseDto;
import com.vitor.cordeiro.teste.quarkus.exception.DataValidationException;
import com.vitor.cordeiro.teste.quarkus.exception.GoogleApiGenericException;
import com.vitor.cordeiro.teste.quarkus.service.BookService;
import io.smallrye.common.constraint.NotNull;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.temporal.ChronoUnit;

@Path("book")
@Consumes("application/json")
@Produces(MediaType.APPLICATION_JSON)
public class GoogleResource {

    @Inject
    BookService service;

    @GET
    @Timeout(unit = ChronoUnit.SECONDS, value = 10)
    @CircuitBreaker(
            requestVolumeThreshold = 20,
            failureRatio = 0.2,
            delay = 2,
            delayUnit = ChronoUnit.SECONDS,
            successThreshold = 2
    )
    public Response get(@NotNull @QueryParam("title") String title,
                        @DefaultValue("10") @QueryParam("limit") int limit,
                        @DefaultValue("0") @QueryParam("offset") int offset) {
        try {
            var data = service.getBook(title, limit, offset);
            return Response.ok()
                    .entity(new BookResponseDto(limit, offset, data, null)).build();

        } catch (GoogleApiGenericException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        } catch (DataValidationException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new BookResponseDto(limit, offset, null, e.getMessages())).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
