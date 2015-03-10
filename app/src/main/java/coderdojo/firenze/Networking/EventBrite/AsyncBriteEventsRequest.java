package coderdojo.firenze.Networking.EventBrite;

import android.os.AsyncTask;

import javax.net.ssl.SSLException;

import coderdojo.firenze.Networking.EventBrite.Response.EventBriteEvents;
import coderdojo.firenze.Networking.EventBrite.RetrofitInterfaces.EventBriteListener;
import retrofit.RetrofitError;

public class AsyncBriteEventsRequest extends AsyncTask<String, String, EventBriteEvents> {

    private EventBriteListener listener;
    private EventBriteRequestManager requestManager;

    public AsyncBriteEventsRequest(EventBriteListener listener, EventBriteRequestManager requestManager) {
        this.listener = listener;
        this.requestManager = requestManager;
    }

    @Override
    protected EventBriteEvents doInBackground(String... params) {

        EventBriteEvents eventsByOrganizer = null;
        try {
            eventsByOrganizer = requestManager.getEventsByOrganizer(params[0]);
        } catch (SSLException e) {
            return null;
        } catch (RetrofitError error) {
            return null;
        }
        return eventsByOrganizer;
    }

    @Override
    protected void onPostExecute(EventBriteEvents eventBriteEvents) {
        super.onPostExecute(eventBriteEvents);
        listener.onEventListRecived(eventBriteEvents);
    }
}
