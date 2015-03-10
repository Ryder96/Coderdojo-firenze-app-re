package coderdojo.firenze.Networking.Twitter;

import android.os.AsyncTask;

import twitter4j.ResponseList;
import twitter4j.Status;


public class AsyncTwitterRequest extends AsyncTask<String, String, ResponseList<Status>> {
    private TwitterRequestListener listener;
    private TwitterApi api;

    public AsyncTwitterRequest(TwitterRequestListener listener, TwitterApi api) {
        this.listener = listener;
        this.api = api;
    }

    @Override
    protected ResponseList<twitter4j.Status> doInBackground(String... params) {
        return this.api.getTimeLine();
    }

    @Override
    protected void onPostExecute(ResponseList<twitter4j.Status> posts) {
        super.onPostExecute(posts);
        listener.onRequestDone(posts);
    }
}
