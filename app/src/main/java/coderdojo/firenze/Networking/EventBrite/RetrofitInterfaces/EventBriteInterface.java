package coderdojo.firenze.Networking.EventBrite.RetrofitInterfaces;


import coderdojo.firenze.Networking.EventBrite.Response.EventBriteEvent;
import coderdojo.firenze.Networking.EventBrite.Response.EventBriteEvents;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


public interface EventBriteInterface {

    @GET(value = "/{eventID}/")
    EventBriteEvent getEvent(@Path("eventID") String eventID, @Query("token") String token);

    @GET("/search/")
    EventBriteEvents getEventsByOrganizer(@Query("organizer.id") String organizerId, @Query("token") String token);

}
