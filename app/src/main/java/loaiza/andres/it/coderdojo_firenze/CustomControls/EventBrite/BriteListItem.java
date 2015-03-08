package loaiza.andres.it.coderdojo_firenze.CustomControls.EventBrite;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.Date;

import loaiza.andres.it.coderdojo_firenze.Helpers.DateTimeHelper;
import loaiza.andres.it.coderdojo_firenze.Networking.EventBrite.Response.EventBriteEvent;
import loaiza.andres.it.coderdojo_firenze.R;


public class BriteListItem {

    private int icon;
    private Date startDate, endDate;
    private String eventDate = "";
    private String eventDescription;
    private String eventLocation;
    private String title;
    private LatLng latLng;
    private String URL;

    public BriteListItem(EventBriteEvent event) {
        this.icon = R.drawable.ic_ying; // TODO change drawable
        this.title = event.getName().getText();
        this.startDate = DateTimeHelper.getDateFromString(event.getStart().getLocal());
        this.endDate = DateTimeHelper.getDateFromString(event.getEnd().getLocal());
        this.eventDescription = event.getDescription().getText();
        this.eventLocation = event.getVenue().getName() + "\n" + event.getVenue().getAddress().getAddress_1();
        this.latLng = new LatLng(Double.valueOf(event.getVenue().getLatitude()), Double.valueOf(event.getVenue().getLongitude()));
        this.URL = event.getUrl();
    }

    public String getTitle() {
        return this.title;
    }

    public int getIcon() {
        return icon;
    }

    public String getLocation() {
        return eventLocation;
    }

    public String getEventDate() {
        if (eventDate.isEmpty()) {
            Calendar startCal = DateTimeHelper.getCalendar(startDate);
            Calendar endCal = DateTimeHelper.getCalendar(endDate);
            String startHour = DateTimeHelper.formatHour(startCal.get(Calendar.HOUR_OF_DAY)) + ":" + DateTimeHelper.formatHour(startCal.get(Calendar.MINUTE));
            String endHour = DateTimeHelper.formatHour(endCal.get(Calendar.HOUR_OF_DAY)) + ":" + DateTimeHelper.formatHour(endCal.get(Calendar.MINUTE));
            String newDate = DateTimeHelper.getDayName(startCal.get(Calendar.DAY_OF_WEEK)) + " " + (startCal.get(Calendar.DAY_OF_MONTH)) + " " + DateTimeHelper.getMonthName(startCal.get(Calendar.MONTH));
            eventDate = newDate + "\nOre " + startHour + " - " + endHour;
        }
        return eventDate;
    }

    public String getDescription() {
        return eventDescription;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getURL() {
        return URL;
    }
}
