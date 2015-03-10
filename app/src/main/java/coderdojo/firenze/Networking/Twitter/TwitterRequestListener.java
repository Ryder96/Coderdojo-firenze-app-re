package coderdojo.firenze.Networking.Twitter;

import twitter4j.ResponseList;
import twitter4j.Status;


public interface TwitterRequestListener {
    public abstract void onRequestDone(ResponseList<Status> posts);
}
