package com.jrogalsk.requestsmanager.core.business.domain.request;

import java.util.List;
import java.util.Optional;

public interface RequestsRepository {
    Request add(Request aRequest);

    Optional<Request> findWithId(String id);

    List<Request> allRequests();
}
