package com.jrogalsk.requestsmanager.core.business.adapters.request;

import com.jrogalsk.requestsmanager.core.business.domain.request.Request;
import com.jrogalsk.requestsmanager.core.business.domain.request.RequestsRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class VolatileRequestsRepository implements RequestsRepository {
    private List<Request> allRequests = new ArrayList<>();

    @Override
    public Request add(Request aRequest) {
        this.allRequests().add(aRequest);
        String requestId = String.valueOf(this.allRequests().indexOf(aRequest));
        aRequest.setId(requestId);

        return aRequest;
    }

    @Override
    public Optional<Request> findWithId(String id) {
        return Optional.ofNullable(this.safelyGetRequestWithId(id));
    }

    private Request safelyGetRequestWithId(String id) {
        try {
            return this.allRequests().get(Integer.parseInt(id));
        }
        catch (IndexOutOfBoundsException | NumberFormatException e) {
            return null;
        }
    }

    @Override
    public List<Request> allRequests() {
        return this.allRequests;
    }
}
