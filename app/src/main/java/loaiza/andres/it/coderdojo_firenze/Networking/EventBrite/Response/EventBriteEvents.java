package loaiza.andres.it.coderdojo_firenze.Networking.EventBrite.Response;

public class EventBriteEvents {
    private EventBriteEvent[] events;

    private Pagination pagination;

    public EventBriteEvent[] getEvents() {
        return events;
    }

    public void setEvents(EventBriteEvent[] events) {
        this.events = events;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}

		