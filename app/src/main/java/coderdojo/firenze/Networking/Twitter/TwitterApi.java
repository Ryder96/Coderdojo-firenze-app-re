package coderdojo.firenze.Networking.Twitter;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterApi {


    private String consumerKey = "DCRHQ0c1cqQ0FqTJiD6CkDss5";
    private String consumerSecret = "7d12ipSpVJ6HwUvykFO9mkINjNNd9TzgFnPUoe7SXYqkap7PoO";
    private String accessToken = "508919637-L7QWiOf1M4vM9R6DAdzUZyvxy0w3Wm32ZeTx1MrN";
    private String accessTokenSecret = "JWmgFzDaj1KPn83QtkC6jwbTJTljRX7bohEmnETjVGzyi";


    private Twitter twitter;

    private ResponseList<Status> _lastRequest;


    public TwitterApi(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
        this.twitter = this.authenticate();
    }

    /**
     * Test Constructor
     */
    public TwitterApi() {
        this.twitter = this.authenticate();
    }

    private Twitter authenticate() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

    public ResponseList<Status> getTimeLine() {
        try {
            ResponseList<Status> userTimeline = twitter.getUserTimeline();
            set_lastRequest(userTimeline);
            return userTimeline;
        } catch (TwitterException e) {
            return null;
        }
    }

    public ResponseList<Status> getLastRequest() {
        return _lastRequest;
    }

    public void set_lastRequest(ResponseList<Status> _lastRequest) {
        this._lastRequest = _lastRequest;
    }
}
