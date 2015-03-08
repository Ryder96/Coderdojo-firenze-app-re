package loaiza.andres.it.coderdojo_firenze.Helpers;

import java.util.ArrayList;

import loaiza.andres.it.coderdojo_firenze.CustomControls.Twitter.TwitterListElement;
import twitter4j.ResponseList;
import twitter4j.Status;


public class TwitterApiHelper {
    public static ArrayList<Status> getArray(ResponseList<Status> array) {
        ArrayList<Status> convArray = new ArrayList<Status>();
        Object[] objects = array.toArray();
        for (Object obj : objects)
            convArray.add((Status) obj);
        return convArray;
    }

    public static ArrayList<TwitterListElement> getItemArray(ResponseList<Status> array) {
        ArrayList<TwitterListElement> convArray = new ArrayList<TwitterListElement>();
        Object[] objects = array.toArray();
        for (Object obj : objects)
            convArray.add(new TwitterListElement((Status) obj));
        return convArray;
    }

}
