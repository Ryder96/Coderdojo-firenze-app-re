package coderdojo.firenze.Networking.EventBrite;

import javax.net.ssl.SSLException;

import coderdojo.firenze.Networking.EventBrite.Response.EventBriteEvent;
import coderdojo.firenze.Networking.EventBrite.Response.EventBriteEvents;
import coderdojo.firenze.Networking.EventBrite.RetrofitInterfaces.EventBriteInterface;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class EventBriteRequestManager {

    public static final String CODERDOJO_ID = "2079433853";
    public static final String TESTGARU_ID = "7840238166";
    public static final String s = "7840587595";
    private final String PUBLIC_TOKEN = "FXJN23TCHVRM73KUQLEP"; // <- Use this
    private final String PERSONAL_TOKEN = "BKIYA2K56NSZUDVG6LWR";
    private final String apiEndPoint = "https://www.eventbriteapi.com/v3/events/";
    private EventBriteInterface api;

    private EventBriteEvents _lastEvents;

    public EventBriteRequestManager() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(apiEndPoint)
                .build();
        this.api = restAdapter.create(EventBriteInterface.class);
    }

    public EventBriteEvent getEvent(String eventID) throws RetrofitError {
        return api.getEvent(eventID, PUBLIC_TOKEN);
    }

    public EventBriteEvents getEventsByOrganizer(String organizerId) throws RetrofitError, SSLException {
        EventBriteEvents eventsByOrganizer = this.api.getEventsByOrganizer(organizerId, PUBLIC_TOKEN);
        this._lastEvents = eventsByOrganizer;
        return eventsByOrganizer;
    }

    public EventBriteEvents getLastEvents() {
        return _lastEvents;
    }
}
