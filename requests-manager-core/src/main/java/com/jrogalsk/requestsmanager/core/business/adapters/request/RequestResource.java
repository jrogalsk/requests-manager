package com.jrogalsk.requestsmanager.core.business.adapters.request;

import com.jrogalsk.requestsmanager.core.business.domain.request.Request;
import com.jrogalsk.requestsmanager.core.business.domain.request.RequestsRepository;

import javax.inject.Inject;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/request")
@Produces(MediaType.APPLICATION_JSON)
public class RequestResource {

    @Inject
    RequestsRepository requestsRepository;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addNewRequestWith(@Size(min = 1) @FormParam("title") String aTitle,
                                      @Size(min = 1) @FormParam("content") String aContent,
                                      @Context UriInfo uriInfo) {
        String generatedId = this.requestsRepository()
                .add(new Request(aTitle, aContent))
                .getId();

        return this.createdResponse(uriInfo, generatedId);
    }

    @GET
    @Path("{requestId}")
    public Request fetchRequestWithId(@PathParam("requestId") String id) {
        return this.requestsRepository()
                .findWithId(id)
                .orElseThrow(() -> new WebApplicationException(404));
    }

    private RequestsRepository requestsRepository() {
        return this.requestsRepository;
    }

    private Response createdResponse(UriInfo uriInfo, String aGeneratedId) {
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(aGeneratedId);

        return Response.created(builder.build()).build();
    }

}
