package loaiza.andres.it.coderdojo_firenze.Networking.EventBrite.RetrofitInterfaces;


import loaiza.andres.it.coderdojo_firenze.Networking.EventBrite.Response.EventBriteEvents;

/**
 * Created by Garu on 29/12/2014.
 */
public interface EventBriteListener {
    public abstract void onEventListRecived(EventBriteEvents response);
}
