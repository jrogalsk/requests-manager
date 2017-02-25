package com.jrogalsk.requestsmanager.core.business.adapters.request;

import com.jrogalsk.requestsmanager.core.business.domain.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class VolatileRequestsRepositoryTest {
    private VolatileRequestsRepository volatileRequestsRepository;

    @Before
    public void setUp() {
        this.setVolatileRequestsRepository(new VolatileRequestsRepository());
    }

    @Test
    public void whenInitializedHasNoRequests() {
        assertTrue(new VolatileRequestsRepository().allRequests().isEmpty());
    }

    @Test
    public void addRequest() {
        this.volatileRequestsRepository().add(mock(Request.class));

        assertThat(this.volatileRequestsRepository().allRequests().size(), is(1));
    }

    @Test
    public void setUniqueIdOnAddedRequest() {
        Request first = new Request("moo", "boo");
        Request second = new Request("doo", "foo");

        this.volatileRequestsRepository().add(first);
        this.volatileRequestsRepository().add(second);

        assertThat(first.getId(), is("0"));
        assertThat(second.getId(), is("1"));
    }

    @Test
    public void findById() {
        Request expected = new Request("moo", "boo");
        this.volatileRequestsRepository().add(mock(Request.class));
        this.volatileRequestsRepository().add(mock(Request.class));
        this.volatileRequestsRepository().add(expected);

        assertThat(this.volatileRequestsRepository().findWithId("2").get(), is(expected));
    }

    @Test
    public void doesNotFailWhenRequestNotFound() {
        this.volatileRequestsRepository().add(new Request("moo", "boo"));

        this.volatileRequestsRepository().findWithId("10");
    }

    private void setVolatileRequestsRepository(VolatileRequestsRepository aVolatileRequestsRepository) {
        this.volatileRequestsRepository = aVolatileRequestsRepository;
    }

    private VolatileRequestsRepository volatileRequestsRepository() {
        return this.volatileRequestsRepository;
    }
}
