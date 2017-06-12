package com.jrogalsk.requestsmanager.core.business.adapters.request;

import com.jrogalsk.requestsmanager.core.business.application.RequestStateTransitionService;
import com.jrogalsk.requestsmanager.core.business.domain.request.Request;
import com.jrogalsk.requestsmanager.core.business.domain.request.RequestsRepository;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.IllegalStateTransitionException;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionTrigger;

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
import java.util.List;

@Path("/request")
@Produces(MediaType.APPLICATION_JSON)
public class RequestResource {
    @Inject
    RequestsRepository requestsRepository;
    @Inject
    RequestStateTransitionService requestStateTransitionService;

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
    public Response fetchAllRequests() {
        return Response
                .ok(this.fetchExistingRequests())
                .build();
    }

    @GET
    @Path("{requestId}")
    public Response fetchRequestWithId(@PathParam("requestId") String aRequestId) {
        return Response
                .ok(this.fetchRequestFor(aRequestId))
                .build();
    }

    @POST
    @Path("{requestId}")
    public Response changeRequestState(@PathParam("requestId") String aRequestId,
                                       @FormParam("action") StateTransitionTrigger stateTransitionTrigger,
                                       @FormParam("actionJustification") String stateTransitionJustification) {
        try {
            Request request = this.fetchRequestFor(aRequestId);
            this.requestStateTransitionService()
                    .performTransition(request, stateTransitionTrigger, stateTransitionJustification);

            return Response.accepted().build();
        }
        catch (IllegalStateTransitionException exception) {
            throw new WebApplicationException(exception.getMessage(), 400);
        }
    }

    @GET
    @Path("{requestId}/history")
    public Response fetchRequestHistory(@PathParam("requestId") String aRequestId) {
        return Response
                .ok(this.fetchRequestFor(aRequestId).getStateTransitionHistory())
                .build();
    }

    private Request fetchRequestFor(String aRequestId) {
        return this.requestsRepository()
                .findWithId(aRequestId)
                .orElseThrow(() -> new WebApplicationException(404));
    }

    private RequestsRepository requestsRepository() {
        return this.requestsRepository;
    }

    private RequestStateTransitionService requestStateTransitionService() {
        return this.requestStateTransitionService;
    }

    private List<Request> fetchExistingRequests() {
        return this.requestsRepository().allRequests();
    }

    private Response createdResponse(UriInfo uriInfo, String aGeneratedId) {
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(aGeneratedId);

        return Response.created(builder.build()).build();
    }

}
