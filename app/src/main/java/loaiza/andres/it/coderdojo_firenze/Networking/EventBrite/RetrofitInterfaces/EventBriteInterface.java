package loaiza.andres.it.coderdojo_firenze.Networking.EventBrite.RetrofitInterfaces;


import loaiza.andres.it.coderdojo_firenze.Networking.EventBrite.Response.EventBriteEvent;
import loaiza.andres.it.coderdojo_firenze.Networking.EventBrite.Response.EventBriteEvents;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


public interface EventBriteInterface {

    @GET(value = "/{eventID}/")
    EventBriteEvent getEvent(@Path("eventID") String eventID, @Query("token") String token);

    @GET("/search/")
    EventBriteEvents getEventsByOrganizer(@Query("organizer.id") String organizerId, @Query("token") String token);

}
