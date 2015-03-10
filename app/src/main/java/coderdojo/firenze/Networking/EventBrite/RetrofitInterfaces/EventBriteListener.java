package coderdojo.firenze.Networking.EventBrite.RetrofitInterfaces;


import coderdojo.firenze.Networking.EventBrite.Response.EventBriteEvents;


public interface EventBriteListener {
    public abstract void onEventListRecived(EventBriteEvents response);
}
