package loaiza.andres.it.coderdojo_firenze.Helpers;


import java.util.ArrayList;
import java.util.List;

import loaiza.andres.it.coderdojo_firenze.CustomControls.EventBrite.BriteListItem;
import loaiza.andres.it.coderdojo_firenze.Networking.EventBrite.Response.EventBriteEvent;
import loaiza.andres.it.coderdojo_firenze.Networking.EventBrite.Response.EventBriteEvents;

public class EventBriteHelper {

    public static List<BriteListItem> getListItems(EventBriteEvents response) {
        List<BriteListItem> items = new ArrayList<BriteListItem>();
        for (EventBriteEvent event : response.getEvents())
            items.add(new BriteListItem(event));
        return items;
    }
}
